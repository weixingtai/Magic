package com.suromo.material.ui.view.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.suromo.common.log.MLog
import com.suromo.material.ui.navigation.MainDestination
import com.suromo.material.ui.navigation.MainNavAction

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/7/9
 * desc   :
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    isExpandedScreen : Boolean = false,
    showTopAppBar: Boolean,
    openDrawer: () -> Unit,
    navHostController: NavHostController
) {
    val navigatorAction = remember(navHostController) {
        MainNavAction(navHostController)
    }

    Scaffold(
        topBar = {
//            if (showTopAppBar){
//                 HomeTopAppBar(openDrawer)
//            }
        }
    ) { innerPadding ->
        MLog.d(innerPadding)
        Column {
            Button(onClick = navigatorAction.navToButton) {
                Text(text = "按钮", modifier = Modifier.fillMaxWidth())
            }
            Button(onClick = navigatorAction.navToCard) {
                Text(text = "卡片", modifier = Modifier.fillMaxWidth())
            }
            Button(onClick = navigatorAction.navToSwitch) {
                Text(text = "开关", modifier = Modifier.fillMaxWidth())
            }
            Button(onClick = navigatorAction.navToChip) {
                Text(text = "筛选", modifier = Modifier.fillMaxWidth())
            }
        }

    }
}


@Composable
private fun HomeTopAppBar(
    openDrawer: () -> Unit
) {
    val title = "app"
    MediumTopAppBar(
        title = {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp, top = 10.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Open search */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = ""
                )
            }
        }
    )
}