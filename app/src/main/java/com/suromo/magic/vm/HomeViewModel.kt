package com.suromo.magic.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.suromo.magic.R
import com.suromo.magic.repo.LotteryRepository
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
    }
//    val result = repository.getLotteries().asLiveData()
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

    companion object {
        fun provideFactory(
            homeRepository: LotteryRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(homeRepository) as T
            }
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