package com.suromo.magic.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/25
 * desc   :
 */
object MainDestinations {
    const val ROUTE_HOME = "home"
}

class MainNavigationAction(navController: NavController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(MainDestinations.ROUTE_HOME) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}