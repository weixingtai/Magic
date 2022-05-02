package com.suromo.magic.ui.about

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suromo.magic.R
import com.suromo.magic.data.repository.mock.impl.FakeAboutRepository
import com.suromo.magic.ui.bean.About
import com.suromo.magic.ui.bean.Result
import com.suromo.magic.ui.theme.MagicTheme
import kotlinx.coroutines.runBlocking

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/29
 * desc   :
 */
@Composable
fun AboutScreens(
    about: About,
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
                        text = stringResource(R.string.about_me),
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
        AboutScreenContent(about, isExpandedScreen, screenModifier)
    }
}

@Composable
private fun AboutScreenContent(
    about: About,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier
) {

    Column(modifier) {
        AboutCards(about, modifier)
    }
}

@Preview("about screen nav rail", device = Devices.PIXEL_C)
@Preview("about screen nav rail (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("about screen nav rail (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
fun PreviewAboutScreenNavRail() {
    MagicTheme {
        val about = getFakeAbout()
        val (_, _) = rememberSaveable {
            mutableStateOf(about)
        }

        AboutScreens(
            about = about,
            isExpandedScreen = false,
            openDrawer = { },
            scaffoldState = rememberScaffoldState()
        )
    }
}



private fun getFakeAbout(): About {
    val about = runBlocking {
        (FakeAboutRepository().getAbout() as Result.Success).data
    }
    return about
}