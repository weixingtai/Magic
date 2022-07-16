package com.suromo.material.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/7/9
 * desc   :
 */
@Preview
@Composable
fun MainNavRail(
    modifier: Modifier = Modifier,
    currentRouter: String = MainDestination.ROUTER_HOME,
    actionOne: ()->Unit = {},
    actionTwo: ()->Unit = {},
    actionThree: ()->Unit = {}
) {
    NavigationRail(
        modifier = modifier,
        //Header
        header = {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "显示完整菜单",
                modifier = Modifier.padding(top = 20.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    ) {
        //Content
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            NavigationRailItem(
                selected = currentRouter == MainDestination.ROUTER_HOME,
                onClick = actionOne,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "首页"
                    )
                },
                label = { Text(text = "首页") },
            )
            Spacer(modifier = Modifier.height(16.dp))
            NavigationRailItem(
                selected = currentRouter == MainDestination.ROUTER_USER,
                onClick = actionTwo,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "我的"
                    )
                },
                label = { Text(text = "我的") },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
