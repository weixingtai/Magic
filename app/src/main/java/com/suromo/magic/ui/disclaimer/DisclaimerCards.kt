package com.suromo.magic.ui.disclaimer

import android.content.res.Configuration
import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suromo.magic.data.source.local.impl.disclaimer
import com.suromo.magic.ui.bean.Disclaimer
import com.suromo.magic.ui.theme.MagicTheme

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/30
 * desc   :
 */
@Composable
fun DisclaimerCards(disclaimer: Disclaimer, modifier: Modifier = Modifier){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            item {
                Text(text = Html.fromHtml(disclaimer.content,FROM_HTML_MODE_LEGACY).toString())
            }
        }

    }
}

// Preview section

@Preview("Default colors")
@Composable
fun TutorialPreview() {
    TutorialPreviewTemplate()
}

@Preview("Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TutorialPreviewDark() {
    TutorialPreviewTemplate()
}

@Preview("Font scaling 1.5", fontScale = 1.5f)
@Composable
fun TutorialPreviewFontScale() {
    TutorialPreviewTemplate()
}

@Composable
fun TutorialPreviewTemplate() {
    val disclaimer = disclaimer

    MagicTheme {
        Surface {
            DisclaimerCards(disclaimer)
        }
    }
}