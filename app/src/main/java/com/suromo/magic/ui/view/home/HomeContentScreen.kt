package com.suromo.magic.ui.view.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/25
 * desc   :
 */
@Composable
fun HomeContentScreen(
    recommend: Recommend,
    modifier: Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

    Text(text = "${recommend.period}")

}