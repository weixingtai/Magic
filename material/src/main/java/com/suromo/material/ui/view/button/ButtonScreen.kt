package com.suromo.material.ui.view.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/7/9
 * desc   :
 */
@Preview
@Composable
fun ButtonScreen(isExpandedScreen : Boolean = false) {
    Column {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Confirm")
        }
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(text = "Confirm")
        }
        FilledTonalButton(onClick = { /*TODO*/ }) {
            Text(text = "Confirm")
        }
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "Confirm")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Confirm")
        }
    }

}