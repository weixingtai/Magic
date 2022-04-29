/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.suromo.magic.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suromo.magic.data.repository.AppContainer

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
//            val homeViewModel: HomeViewModel = viewModel(
//                factory = HomeViewModel.provideFactory(appContainer.postsRepository)
//            )
//            HomeRoute(
//                homeViewModel = homeViewModel,
//                isExpandedScreen = isExpandedScreen,
//                openDrawer = openDrawer
//            )
        }
        composable(MainDestinations.ABOUT_ROUTE) {
//            val aboutViewModel: AboutViewModel = viewModel(
//                factory = AboutViewModel.provideFactory(appContainer.aboutRepository)
//            )
//            AboutRoute(
//                interestsViewModel = aboutViewModel,
//                isExpandedScreen = isExpandedScreen,
//                openDrawer = openDrawer
//            )
        }
        composable(MainDestinations.MESSAGE_ROUTE) {
//            val aboutViewModel: AboutViewModel = viewModel(
//                factory = AboutViewModel.provideFactory(appContainer.aboutRepository)
//            )
//            AboutRoute(
//                interestsViewModel = aboutViewModel,
//                isExpandedScreen = isExpandedScreen,
//                openDrawer = openDrawer
//            )
        }
        composable(MainDestinations.COLLECT_ROUTE) {
//            val aboutViewModel: AboutViewModel = viewModel(
//                factory = AboutViewModel.provideFactory(appContainer.aboutRepository)
//            )
//            AboutRoute(
//                interestsViewModel = aboutViewModel,
//                isExpandedScreen = isExpandedScreen,
//                openDrawer = openDrawer
//            )
        }
        composable(MainDestinations.DISCLAIMER_ROUTE) {
//            val aboutViewModel: AboutViewModel = viewModel(
//                factory = AboutViewModel.provideFactory(appContainer.aboutRepository)
//            )
//            AboutRoute(
//                interestsViewModel = aboutViewModel,
//                isExpandedScreen = isExpandedScreen,
//                openDrawer = openDrawer
//            )
        }
        composable(MainDestinations.SETTING_ROUTE) {
//            val aboutViewModel: AboutViewModel = viewModel(
//                factory = AboutViewModel.provideFactory(appContainer.aboutRepository)
//            )
//            AboutRoute(
//                interestsViewModel = aboutViewModel,
//                isExpandedScreen = isExpandedScreen,
//                openDrawer = openDrawer
//            )
        }
    }
}
