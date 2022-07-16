package com.suromo.material.ui.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.suromo.common.log.MLog
import com.suromo.material.ui.theme.MagicTheme
import kotlinx.coroutines.launch

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/7/11
 * desc   :
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    widthSizeClass: WindowWidthSizeClass
) {
    MagicTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val systemUiController = rememberSystemUiController()
            val darkIcons = !isSystemInDarkTheme()
            SideEffect {
                systemUiController.setSystemBarsColor(color = Color.Transparent, darkIcons = darkIcons)
            }

            val navHostController = rememberNavController()
            val navigatorAction = remember(navHostController) {
                MainNavAction(navHostController)
            }
            val coroutineScope = rememberCoroutineScope()
            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route ?: MainDestination.ROUTER_HOME

            val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded
            val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)

//            ModalNavigationDrawer(drawerContent = {
//                MainDrawer(
//                    currentRoute = currentRoute,
//                    navigateToHome = navigatorAction.navToHome,
//                    navigateToInterests = navigatorAction.navToUser,
//                    closeDrawer = { coroutineScope.launch { sizeAwareDrawerState.close() } },
//                    modifier = Modifier
//                        .statusBarsPadding()
//                        .navigationBarsPadding()
//                )
//            },
//                drawerState = sizeAwareDrawerState,
//                gesturesEnabled = !isExpandedScreen
//            ) {

                    if (isExpandedScreen) {
                        Row(
                            Modifier
                                .fillMaxSize()
                                .statusBarsPadding()
                                .windowInsetsPadding(
                                    WindowInsets
                                        .navigationBars
                                        .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
                                )
                        ) {
                            MainNavRail(
                                currentRouter = currentRoute,
                                actionOne = navigatorAction.navToHome,
                                actionTwo = navigatorAction.navToUser,
                            )
                            MainNavGraph(
                                isExpandedScreen = isExpandedScreen,
                                navHostController = navHostController,
                                openDrawer = { coroutineScope.launch { sizeAwareDrawerState.open() } },
                            )
                        }
                    } else {
                        Scaffold(
                            bottomBar = {
                                if(currentRoute == MainDestination.ROUTER_HOME || currentRoute == MainDestination.ROUTER_USER){
                                    MainNavBar(
                                        currentRouter = currentRoute,
                                        actionOne = navigatorAction.navToHome,
                                        actionTwo = navigatorAction.navToUser,
                                    )
                                }
                            }
                        ) { paddingValues ->
                            MLog.d(paddingValues)
                            MainNavGraph(
                                isExpandedScreen = isExpandedScreen,
                                navHostController = navHostController,
                                openDrawer = { coroutineScope.launch { sizeAwareDrawerState.open() } },
                            )
                        }
                    }
                }
//            }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    return if (!isExpandedScreen) {
        drawerState
    } else {
        DrawerState(DrawerValue.Closed)
    }
}