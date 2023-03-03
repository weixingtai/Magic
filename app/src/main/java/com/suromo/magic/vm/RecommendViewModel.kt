package com.suromo.magic.vm

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.map
import com.suromo.magic.R
import com.suromo.magic.db.AppDatabase
import com.suromo.magic.db.dao.HistoryDao
import com.suromo.magic.db.entity.History
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.repo.LotteryRepository
import com.suromo.magic.state.HomeUiState
import com.suromo.magic.strategy.MissSevenStrategy
import com.suromo.magic.ui.bean.ErrorMessage
import com.suromo.magic.ui.bean.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val repository: LotteryRepository,
    private val historyDao: HistoryDao
) : ViewModel() {

    private val _text = MutableLiveData<Lottery>()

    val text: LiveData<Lottery> = _text

    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState())

    init {
        refreshLotteries()
    }

    fun refreshLotteries(){
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {

            val today = SimpleDateFormat("yyyy年MM月dd日").format(Date())

            val source1 = async { repository.getLotteries() }
            val source2 = async { repository.getHistoryByDate(today) }
            val result = source1.await()
            val result2 = source2.await()

            when(result2) {
                is RequestResult.Success ->{
                    val history: History = result2.data
                    Log.d("wxt", "history:$history")
                }
                is RequestResult.Error -> {
                    val errorMessage = ErrorMessage(
                        id = UUID.randomUUID().mostSignificantBits,
                        messageId = R.string.load_error
                    )
                    Log.d("wxt","errorMessage:"+errorMessage.messageId)
                }
            }

            viewModelState.update {
                when(result) {
                    is RequestResult.Success ->{

                        val lotteries: List<Lottery> = result.data
                        Log.d("wxt", "lotteries:$lotteries")
                        val strategy = MissSevenStrategy()
                        strategy.initHistory(lotteries)
                        Log.d("wxt","第${strategy.getNextRecommend().longperiod}期心水推荐：")
                        Log.d("wxt","策略一(取上期开奖结果)选号：${strategy.strategy1}")
                        Log.d("wxt","策略二(上期开出的幸运号码)选号：${strategy.strategy2}")
                        Log.d("wxt","策略三(小码连续来两期)选号：${strategy.strategy3}")
                        Log.d("wxt","策略四(上期跳号)选号：${strategy.strategy4}")
                        Log.d("wxt","策略五(上期与上上期跳号)选号：${strategy.strategy5}")
                        Log.d("wxt","策略六(上期特尾数乘二)选号：${strategy.strategy6}")
                        Log.d("wxt","策略七(上期最小两个号码相乘)选号：${strategy.strategy7}")
                        Log.d("wxt","所有策略选号推荐：${strategy.generateNumList}")
                        Log.d("wxt","本期七不中推荐：${strategy.getNextRecommend().num}")

                        val history = History(
                            longperiod = strategy.getNextRecommend().longperiod,
                            numbers = strategy.getNextRecommend().num,
                            date = SimpleDateFormat("yyyy年MM月dd日").format(Date()),
                            bet = 500,
                            win = true
                        )

                        historyDao.insert(history)



                        it.copy(lotteries = result.data, isLoading = false)
                    }
                    is RequestResult.Error -> {
                        val errorMessage = it.errorMessages + ErrorMessage(
                            id = UUID.randomUUID().mostSignificantBits,
                            messageId = R.string.load_error
                        )
                        it.copy(errorMessages = errorMessage, isLoading = false)
                    }
                }
            }

        }
    }

    fun errorShown(errorId: Long) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }

    private data class HomeViewModelState(
        val lotteries: List<Lottery>? = null,
        val isLoading: Boolean = false,
        val errorMessages: List<ErrorMessage> = emptyList()
    ) {
        fun toUiState(): HomeUiState =
            if (lotteries == null) {
                HomeUiState.NoLotteries(
                    isLoading = isLoading,
                    errorMessages = errorMessages
                )
            } else {
                HomeUiState.HasLotteries(
                    lotteries = lotteries,
                    isLoading = isLoading,
                    errorMessages = errorMessages
                )
            }
    }

}