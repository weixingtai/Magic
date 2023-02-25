package com.suromo.magic.ui.view.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.suromo.magic.R
import com.suromo.magic.ui.widget.MagicSnackbarHost

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/25
 * desc   :
 */
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    showTopAppBar: Boolean,
    onRefreshRecommend: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { MagicSnackbarHost(hostState = it) },
        topBar = {
            if (showTopAppBar) {
                HomeTopBarScreen(
                    openDrawer = openDrawer,
                    elevation = 4.dp
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)
        HomeLoadingScreen(
            uiState = uiState,
            onRefreshRecommend = onRefreshRecommend,
            modifier = contentModifier
        ) { hasRecommendUiState, modifier ->
            HomeContentScreen(
                recommend = hasRecommendUiState.recommend,
                modifier = modifier)
        }
    }

    if (uiState.errorMessages.isNotEmpty()) {
        val errorMessage = remember(uiState) { uiState.errorMessages[0] }
        val errorMessageText: String = stringResource(errorMessage.messageId)
        val retryMessageText = stringResource(id = R.string.retry)

        val onRefreshRecommendState by rememberUpdatedState(onRefreshRecommend)
        val onErrorDismissState by rememberUpdatedState(onErrorDismiss)

        LaunchedEffect(errorMessageText, retryMessageText, scaffoldState) {
            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessageText,
                actionLabel = retryMessageText
            )
            if (snackbarResult == SnackbarResult.ActionPerformed) {
                onRefreshRecommendState()
            }
            onErrorDismissState(errorMessage.id)
        }
    }
}