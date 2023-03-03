package com.suromo.magic.state

import com.suromo.magic.db.entity.Lottery
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

    data class NoLotteries(
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>
    ) : HomeUiState

    data class HasLotteries(
        val lotteries: List<Lottery>?,
        override val isLoading: Boolean,
        override val errorMessages: List<ErrorMessage>
    ) :HomeUiState

}