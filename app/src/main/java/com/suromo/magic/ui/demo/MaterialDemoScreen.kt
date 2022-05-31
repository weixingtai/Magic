package com.suromo.magic.ui.demo

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/17
 * desc   :
 */

@Preview
@Composable
fun PreviewMaterialDemoScreen() {
    Column {
        ShowContent()
        ShowScaffold()
    }
}

@Composable
fun ShowContent(){

    Button(onClick = { },
    contentPadding = PaddingValues(
        start = 20.dp,
        top = 12.dp,
        end = 20.dp,
        bottom = 12.dp
    )) {
        Icon(Icons.Filled.Favorite,
        contentDescription = "Favorite",
        modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Like")
    }
}

@Composable
fun ShowScaffold(){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "这是标题") },
                navigationIcon = null,
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            )
        }
    ) { innerPadding ->
        val screenModifier = Modifier.padding(innerPadding)
    }

}