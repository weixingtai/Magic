package com.suromo.material.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/7/13
 * desc   :
 */
@Preview
@Composable
fun MainNavBar(
    modifier: Modifier = Modifier,
    currentRouter: String = MainDestination.ROUTER_HOME,
    actionOne: ()->Unit = {},
    actionTwo: ()->Unit = {},
    actionThree: ()->Unit = {}
) {
    NavigationBar(
        modifier = modifier
    ) {
        //Content
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            NavigationBarItem(
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
            NavigationBarItem(
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