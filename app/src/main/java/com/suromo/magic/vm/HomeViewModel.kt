package com.suromo.magic.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suromo.magic.R
import com.suromo.magic.repo.LotteryRepository
import com.suromo.magic.strategy.RecommendStrategy
import com.suromo.magic.ui.bean.ErrorMessage
import com.suromo.magic.ui.bean.RequestResult
import com.suromo.magic.ui.view.home.HomeUiState
import com.suromo.magic.ui.view.home.Recommend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/25
 * desc   :
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: LotteryRepository
) : ViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(viewModelScope,
        SharingStarted.Eagerly,
        viewModelState.value.toUiState())

    init {
        refreshRecommend()
        refreshAll()
    }
//    val result = repository.getLotteries().asLiveData()

    private fun refreshAll() {

        viewModelScope.launch {
            val lotteries = repository.getLotteries()
//            Log.d("wxt", "lotteries:${lotteries}")
//            val strategy : ILotteryStrategy = AllOddStrategy()
//            strategy.initHistory(lotteries)
            val strategy = RecommendStrategy()
            strategy.initStrategy(lotteries)
        }
    }

    fun refreshRecommend(){
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = repository.getRecommend()

            viewModelState.update {
                when(result) {
                    is RequestResult.Success ->{
                        it.copy(recommend = result.data, isLoading = false)
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
}

private data class HomeViewModelState(
    val recommend: Recommend? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList()
) {
    fun toUiState(): HomeUiState =
        if (recommend == null) {
            HomeUiState.NoRecommend(
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        } else {
            HomeUiState.HasRecommend(
                recommend = recommend,
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        }
}