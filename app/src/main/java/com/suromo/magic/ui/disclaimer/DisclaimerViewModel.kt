package com.suromo.magic.ui.disclaimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.suromo.magic.R
import com.suromo.magic.data.repository.DisclaimerRepository
import com.suromo.magic.data.repository.PostsRepository
import com.suromo.magic.ui.bean.*
import com.suromo.magic.ui.home.HomeUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/30
 * desc   :
 */
sealed interface DisclaimerUiState {

    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>

    /**
     * There are no posts to render.
     *
     * This could either be because they are still loading or they failed to load, and we are
     * waiting to reload them.
     */
    data class NoDisclaimer(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
    ) : DisclaimerUiState

    /**
     * There are posts to render, as contained in [postsFeed].
     *
     * There is guaranteed to be a [selectedPost], which is one of the posts from [postsFeed].
     */
    data class HasDisclaimer(
        val disclaimer: Disclaimer,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>,
    ) : DisclaimerUiState
}

/**
 * An internal representation of the Home route state, in a raw form
 */
private data class DisclaimerViewModelState(
    val disclaimer: Disclaimer? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList()
) {

    /**
     * Converts this [DisclaimerViewModelState] into a more strongly typed [DisclaimerUiState] for driving
     * the ui.
     */
    fun toUiState(): DisclaimerUiState =
        if (disclaimer == null) {
            DisclaimerUiState.NoDisclaimer(
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        } else {
            DisclaimerUiState.HasDisclaimer(
                disclaimer = disclaimer,
                isLoading = isLoading,
                errorMessages = errorMessages
            )
        }
}

/**
 * ViewModel that handles the business logic of the Home screen
 */
class DisclaimerViewModel(
    private val disclaimerRepository: DisclaimerRepository
) : ViewModel() {

    private val viewModelState = MutableStateFlow(DisclaimerViewModelState(isLoading = true))

    // UI state exposed to the UI
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        refreshDisclaimer()
    }

    /**
     * Refresh posts and update the UI state accordingly
     */
    fun refreshDisclaimer() {
        // Ui state is refreshing
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = disclaimerRepository.getDisclaimer()
            viewModelState.update {
                when (result) {
                    is Result.Success -> it.copy(disclaimer = result.data, isLoading = false)
                    is Result.Error -> {
                        val errorMessages = it.errorMessages + ErrorMessage(
                            id = UUID.randomUUID().mostSignificantBits,
                            messageId = R.string.cant_update_latest_news
                        )
                        it.copy(errorMessages = errorMessages, isLoading = false)
                    }
                }
            }
        }
    }


    /**
     * Factory for HomeViewModel that takes PostsRepository as a dependency
     */
    companion object {
        fun provideFactory(
            disclaimerRepository: DisclaimerRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DisclaimerViewModel(disclaimerRepository) as T
            }
        }
    }
}