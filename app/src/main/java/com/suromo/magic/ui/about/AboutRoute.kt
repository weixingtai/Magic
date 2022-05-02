package com.suromo.magic.ui.about

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.suromo.magic.data.source.local.impl.about
import com.suromo.magic.ui.home.HomeViewModel
import com.suromo.magic.ui.message.MessageScreens

/**
 * aut
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/29
 * desc   :
 */
@Composable
fun AboutRoute(
    aboutViewModel: AboutViewModel,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    AboutScreens(
        about = about,
        isExpandedScreen = isExpandedScreen,
        openDrawer = openDrawer,
        scaffoldState = scaffoldState
    )
}