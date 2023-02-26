package com.suromo.magic.ui.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
    modifier: Modifier
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text(text = "第20230226期心水推荐：")
        Text(text = "特码：02")
        Text(text = "金额: 50 元")
        Text(text = "")
        Text(text = "单双：双 金额 50 元")
        Text(text = "三中三：03-04-05 金额 50 元")
    }


}