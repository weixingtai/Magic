package com.suromo.magic.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/29
 * desc   :
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val ABOUT_ROUTE = "about"
    const val MESSAGE_ROUTE = "message"
    const val COLLECT_ROUTE = "collect"
    const val DISCLAIMER_ROUTE = "disclaimer"
    const val SETTING_ROUTE = "setting"

}

/**
 * Models the navigation actions in the app.
 */
class MainNavigationActions(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(MainDestinations.HOME_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    val navigateToAbout: () -> Unit = {
        navController.navigate(MainDestinations.ABOUT_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToMessage: () -> Unit = {
        navController.navigate(MainDestinations.MESSAGE_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToCollect: () -> Unit = {
        navController.navigate(MainDestinations.COLLECT_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToDisclaimer: () -> Unit = {
        navController.navigate(MainDestinations.DISCLAIMER_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToSetting: () -> Unit = {
        navController.navigate(MainDestinations.SETTING_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}