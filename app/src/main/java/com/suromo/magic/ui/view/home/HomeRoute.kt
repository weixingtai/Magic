package com.suromo.magic.ui.view.home

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.suromo.magic.vm.HomeViewModel

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/25
 * desc   :
 */
@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        showTopAppBar = !isExpandedScreen,
        onRefreshLotteries = { viewModel.refreshLotteries() },
        onErrorDismiss = { viewModel.errorShown(it) },
        openDrawer = openDrawer,
        scaffoldState = scaffoldState
    )
}
