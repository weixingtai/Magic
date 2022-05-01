package com.suromo.magic.ui.message

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.suromo.magic.data.source.local.impl.disclaimer
import com.suromo.magic.ui.about.AboutViewModel
import com.suromo.magic.ui.disclaimer.DisclaimerScreens

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/30
 * desc   :
 */
@Composable
fun MessageRoute(
    messageViewModel: MessageViewModel,
    isExpandedScreen: Boolean,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    MessageScreens(
        isExpandedScreen = isExpandedScreen,
        openDrawer = openDrawer,
        scaffoldState = scaffoldState
    )
}