package com.suromo.material.ui.view.chip

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
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChipScreen(isExpandedScreen : Boolean = false) {

    Column {
        AssistChip(onClick = {}, label = { Text(text = "AssistChip")})
        ElevatedAssistChip(onClick = {}, label = { Text(text = "ElevatedAssistChip")})
        FilterChip(onClick = {}, label = { Text(text = "FilterChip")}, selected = false)
        ElevatedFilterChip(onClick = {}, label = { Text(text = "ElevatedFilterChip")}, selected = false)
        InputChip(onClick = {}, label = { Text(text = "InputChip")}, selected = false)
        SuggestionChip(onClick = {}, label = { Text(text = "SuggestionChip")})
        ElevatedSuggestionChip(onClick = {}, label = { Text(text = "ElevatedSuggestionChip")})

    }
}