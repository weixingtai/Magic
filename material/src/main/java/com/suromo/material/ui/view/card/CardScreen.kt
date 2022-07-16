package com.suromo.material.ui.view.card

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScreen() {
    Column {
        Card {
            Text(text = "这是一张卡片")
            Text(text = "这是一张卡片")
            Text(text = "这是一张卡片")
            Text(text = "这是一张卡片")
            Text(text = "这是一张卡片")
        }
        ElevatedCard{
            Text(text = "这是一张卡片")
            Text(text = "这是一张卡片")
            Text(text = "这是一张卡片")
            Text(text = "这是一张卡片")
            Text(text = "这是一张卡片")
        }
        OutlinedCard{
            Text(text = "这是一张卡片")
            Text(text = "这是一张卡片")
            Text(text = "这是一张卡片")
            Text(text = "这是一张卡片")
            Text(text = "这是一张卡片")
        }
    }
}