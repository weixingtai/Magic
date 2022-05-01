package com.suromo.magic.ui.setting

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suromo.magic.R
import com.suromo.magic.ui.theme.MagicTheme

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/30
 * desc   :
 */
@Composable
fun SettingScreens(
   isExpandedScreen: Boolean,
   openDrawer: () -> Unit,
   scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.setting),
                        modifier = Modifier.fillMaxWidth().padding(0.dp,0.dp,60.dp,0.dp),
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
        }
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

@Preview("setting screen nav rail", device = Devices.PIXEL_C)
@Preview("setting screen nav rail (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("setting screen nav rail (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
fun PreviewSettingScreenNavRail() {
    MagicTheme {
//        val disclaimer = getFakeDisclaimer()
//        val (_, _) = rememberSaveable {
//            mutableStateOf(disclaimer.content)
//        }

        SettingScreens(
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