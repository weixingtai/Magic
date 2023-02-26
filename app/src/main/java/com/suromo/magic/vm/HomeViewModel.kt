package com.suromo.magic.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suromo.magic.R
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.repo.LotteryRepository
import com.suromo.magic.ui.bean.ErrorMessage
import com.suromo.magic.ui.bean.RequestResult
import com.suromo.magic.ui.view.home.HomeUiState
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
        refreshLotteries()
    }

    fun refreshLotteries(){
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = repository.getLotteries()

            viewModelState.update {
                when(result) {
                    is RequestResult.Success ->{
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