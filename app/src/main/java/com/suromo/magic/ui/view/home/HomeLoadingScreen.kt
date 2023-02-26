package com.suromo.magic.ui.view.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.suromo.magic.R

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/25
 * desc   :
 */
@Composable
fun HomeLoadingScreen(
    uiState: HomeUiState,
    onRefreshLotteries: () -> Unit,
    modifier: Modifier,
    hasLotteriesContent: @Composable (
        uiState: HomeUiState.HasLotteries,
        modifier: Modifier
    ) -> Unit
) {
    val empty = when(uiState) {
        is HomeUiState.HasLotteries -> false
        is HomeUiState.NoLotteries -> uiState.isLoading
    }
    if (empty) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }
    } else {
        SwipeRefresh(
            state = rememberSwipeRefreshState(uiState.isLoading),
            onRefresh = onRefreshLotteries,
            content = {
                when (uiState) {
                    is HomeUiState.HasLotteries -> {
                        hasLotteriesContent(uiState, modifier)
                    }
                    is HomeUiState.NoLotteries -> {
                        if (uiState.errorMessages.isEmpty()) {
                            TextButton(
                                onClick = onRefreshLotteries,
                                modifier.fillMaxSize()
                            ) {
                                Text(
                                    stringResource(id = R.string.tap_to_load_content),
                                    textAlign = TextAlign.Center
                                )
                            }
                        } else {
                            Box(modifier.fillMaxSize()) { /* empty screen */ }
                        }
                    }
                }
            }
        )
    }
}