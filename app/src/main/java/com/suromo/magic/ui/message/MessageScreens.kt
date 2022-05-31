package com.suromo.magic.ui.message

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.suromo.magic.R
import com.suromo.magic.ui.theme.MagicTheme

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/30
 * desc   :
 */
@Composable
fun MessageScreens(
    isExpandedScreen: Boolean,
    bottomBarContent: @Composable () -> Unit = { },
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.leave_me_message),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 60.dp, 0.dp),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = if (!isExpandedScreen) {
                    {
                        IconButton(onClick = openDrawer) {
                            Icon(
                                painter = painterResource(R.drawable.ic_magic_logo),
                                contentDescription = stringResource(R.string.cd_open_navigation_drawer),
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                } else {
                    null
                },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            )
        },
        bottomBar = if (!isExpandedScreen) {
            {
                BottomBar(
                    onSubmitMessage = {}
                )
            }
        } else {
            { }
        },
    ) { innerPadding ->
        val screenModifier = Modifier.padding(innerPadding)
//        DisclaimerScreenContent(disclaimer, isExpandedScreen, screenModifier)
    }
}

//@Composable
//private fun DisclaimerScreenContent(
//    disclaimer: Disclaimer,
//    isExpandedScreen: Boolean,
//    modifier: Modifier = Modifier
//) {
//
//    Column(modifier) {
//        DisclaimerCards(disclaimer, modifier)
//    }
//}

@Preview("message screen nav rail", device = Devices.PIXEL_C)
@Preview("message screen nav rail (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("message screen nav rail (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
fun PreviewMessageScreenNavRail() {
    MagicTheme {
//        val disclaimer = getFakeDisclaimer()
//        val (_, _) = rememberSaveable {
//            mutableStateOf(disclaimer.content)
//        }

        MessageScreens(
            isExpandedScreen = false,
            openDrawer = { },
            scaffoldState = rememberScaffoldState()
        )
    }
}



//private fun getFakeDisclaimer(): Disclaimer {
//    val disclaimer = runBlocking {
//        (FakeDisclaimerRepository().getDisclaimer() as Result.Success).data
//    }
//    return disclaimer
//}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
            messageInput: String = "",
    onSubmitMessage: (String) -> Unit,
) {
    Surface(elevation = 8.dp, modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Vertical))
                .height(56.dp)
                .fillMaxWidth()
        ) {
            MessageField(Modifier.padding(horizontal = 16.dp),
                messageInput = messageInput,
                onSubmitMessage = onSubmitMessage,)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun MessageField(
    modifier: Modifier = Modifier,
    messageInput: String = "",
    onSubmitMessage: (String) -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(Dp.Hairline, MaterialTheme.colors.onSurface.copy(alpha = .6f)),
        elevation = 4.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                val context = LocalContext.current
                val focusManager = LocalFocusManager.current
                val keyboardController = LocalSoftwareKeyboardController.current
                TextField(
                    value = messageInput,
                    onValueChange = { onSubmitMessage(it) },
                    placeholder = { Text(stringResource(R.string.home_search)) },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ), // keyboardOptions change the newline key to a search key on the soft keyboard
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    // keyboardActions submits the search query when the search key is pressed
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            submitMessage(onSubmitMessage, context)
                            keyboardController?.hide()
                        }
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Functionality not supported yet */ }) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = stringResource(R.string.cd_more_actions)
                    )
                }
            }
        }
    }
}

/**
 * Stub helper function to submit a user's search query
 */
private fun submitMessage(
    onSubmitMessage: (String) -> Unit,
    context: Context
) {
    onSubmitMessage("")
    Toast.makeText(
        context,
        "Search is not yet implemented",
        Toast.LENGTH_SHORT
    ).show()
}