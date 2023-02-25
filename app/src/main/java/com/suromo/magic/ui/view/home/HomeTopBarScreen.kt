package com.suromo.magic.ui.view.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.suromo.magic.R

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/25
 * desc   :
 */
@Composable
fun HomeTopBarScreen(
    elevation: Dp,
    openDrawer: () -> Unit
) {
    val title = stringResource(id = R.string.app_name)
    TopAppBar(
        title = {
            Icon(
                painter = painterResource(R.drawable.ic_title),
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 2.dp, top = 5.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    painter = painterResource(R.drawable.ic_drawer),
                    contentDescription = stringResource(R.string.open_navigation_drawer),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Open search */ }) {

            }
        },
        backgroundColor = MaterialTheme.colorScheme.surface,
        elevation = elevation
    )
}