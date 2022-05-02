package com.suromo.magic.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suromo.magic.data.repository.AppContainer
import com.suromo.magic.ui.about.AboutRoute
import com.suromo.magic.ui.about.AboutViewModel
import com.suromo.magic.ui.collection.CollectionRoute
import com.suromo.magic.ui.collection.CollectionViewModel
import com.suromo.magic.ui.disclaimer.DisclaimerRoute
import com.suromo.magic.ui.disclaimer.DisclaimerViewModel
import com.suromo.magic.ui.home.HomeRoute
import com.suromo.magic.ui.home.HomeViewModel
import com.suromo.magic.ui.message.MessageRoute
import com.suromo.magic.ui.message.MessageViewModel
import com.suromo.magic.ui.setting.SettingRoute
import com.suromo.magic.ui.setting.SettingViewModel

@Composable
fun MainNavGraph(
    appContainer: AppContainer,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {},
    startDestination: String = MainDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(MainDestinations.HOME_ROUTE) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory(appContainer.postsRepository)
            )
            HomeRoute(
                homeViewModel = homeViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
        composable(MainDestinations.ABOUT_ROUTE) {
            val aboutViewModel: AboutViewModel = viewModel(
                factory = AboutViewModel.provideFactory(appContainer.aboutRepository)
            )
            AboutRoute(
                aboutViewModel = aboutViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
        composable(MainDestinations.MESSAGE_ROUTE) {
            val messageViewModel: MessageViewModel = viewModel(
                factory = MessageViewModel.provideFactory(appContainer.messageRepository)
            )
            MessageRoute(
                messageViewModel = messageViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
        composable(MainDestinations.COLLECT_ROUTE) {
            val collectionViewModel: CollectionViewModel = viewModel(
                factory = CollectionViewModel.provideFactory(appContainer.postsRepository)
            )
            CollectionRoute(
                collectionViewModel = collectionViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
        composable(MainDestinations.DISCLAIMER_ROUTE) {
            val disclaimerViewModel: DisclaimerViewModel = viewModel(
                factory = DisclaimerViewModel.provideFactory(appContainer.disclaimerRepository)
            )
            DisclaimerRoute(
                disclaimerViewModel = disclaimerViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
        composable(MainDestinations.SETTING_ROUTE) {
            val settingViewModel: SettingViewModel = viewModel(
                factory = SettingViewModel.provideFactory(appContainer.settingRepository)
            )
            SettingRoute(
                settingViewModel = settingViewModel,
                isExpandedScreen = isExpandedScreen,
                openDrawer = openDrawer
            )
        }
    }
}
