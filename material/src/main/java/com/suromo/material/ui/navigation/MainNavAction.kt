package com.suromo.material.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/7/9
 * desc   :
 */
class MainNavAction(navHostController: NavHostController) {
    val navToHome: () -> Unit = {
        navHostController.navigate(MainDestination.ROUTER_HOME) {
            popUpTo(navHostController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navToUser: () -> Unit = {
        navHostController.navigate(MainDestination.ROUTER_USER) {
            popUpTo(navHostController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navToButton: () -> Unit = {
        navHostController.navigate(MainDestination.ROUTER_BUTTON)
        {
            popUpTo(navHostController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navToCard: () -> Unit = {
        navHostController.navigate(MainDestination.ROUTER_CARD)
        {
            popUpTo(navHostController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navToSwitch: () -> Unit = {
        navHostController.navigate(MainDestination.ROUTER_SWITCH)
        {
            popUpTo(navHostController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navToChip: () -> Unit = {
        navHostController.navigate(MainDestination.ROUTER_CHIP)
        {
            popUpTo(navHostController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}