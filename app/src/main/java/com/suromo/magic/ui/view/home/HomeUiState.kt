package com.suromo.magic.ui.view.home

import com.suromo.magic.ui.bean.ErrorMessage

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/25
 * desc   :
 */
sealed interface HomeUiState {

    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>

    data class NoRecommend(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>
    ) : HomeUiState

    data class HasRecommend(
        val recommend: Recommend,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>
    ) :HomeUiState
}