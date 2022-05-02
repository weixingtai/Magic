package com.suromo.magic.ui.home.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.suromo.magic.R
import com.suromo.magic.ui.theme.MagicTheme

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/2
 * desc   : 首页顶部栏
 */
@Composable
fun HomeTopBar(
    elevation: Dp,
    openDrawer: () -> Unit
) {
    TopAppBar(
        title = {
            Icon(
                painter = painterResource(R.drawable.ic_magic_wordmark),
                contentDescription = stringResource(id = R.string.app_name),
                tint = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp, top = 10.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    painter = painterResource(R.drawable.ic_magic_logo),
                    contentDescription = stringResource(R.string.cd_open_navigation_drawer),
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Open search */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.cd_search)
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = elevation
    )
}

@Preview("Home PostList")
@Preview("Home PostList(Dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Home PostList(Big Font)", fontScale = 1.5f)
@Composable
fun PreviewHomeTopBar() {
    HomeTopBarTemplate()
}

@Composable
fun HomeTopBarTemplate() {

    MagicTheme {
        Surface {
            HomeTopBar(
                elevation = 4.dp,
                openDrawer = {}
                )
        }
    }
}