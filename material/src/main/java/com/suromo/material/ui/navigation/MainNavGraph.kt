package com.suromo.material.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suromo.material.ui.view.button.ButtonScreen
import com.suromo.material.ui.view.card.CardScreen
import com.suromo.material.ui.view.chip.ChipScreen
import com.suromo.material.ui.view.home.HomeScreen
import com.suromo.material.ui.view.switch.SwitchScreen
import com.suromo.material.ui.view.user.UserScreen

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/7/9
 * desc   : 创建NavHost
 */
@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean,
    navHostController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = MainDestination.ROUTER_HOME
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(MainDestination.ROUTER_HOME) {
            HomeScreen(isExpandedScreen = isExpandedScreen, showTopAppBar = !isExpandedScreen,openDrawer,navHostController)
        }
        composable(MainDestination.ROUTER_USER) {
            UserScreen(isExpandedScreen = isExpandedScreen)
        }
        composable(MainDestination.ROUTER_BUTTON) {
            ButtonScreen(isExpandedScreen = isExpandedScreen)
        }
        composable(MainDestination.ROUTER_CARD) {
            CardScreen()
        }
        composable(MainDestination.ROUTER_SWITCH) {
            SwitchScreen()
        }
        composable(MainDestination.ROUTER_CHIP) {
            ChipScreen()
        }
    }
}