package com.suromo.material.ui.view.switch

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/7/15
 * desc   :
 */
@Preview
@Composable
fun SwitchScreen(isExpandedScreen : Boolean = false) {

    Column {
        Switch(checked = true, onCheckedChange = {  })
        Switch(checked = false, onCheckedChange = {  })
    }

}