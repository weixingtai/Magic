package com.suromo.magic.ui.about

import android.content.res.Configuration
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suromo.magic.R
import com.suromo.magic.data.source.local.impl.about
import com.suromo.magic.ui.bean.About
import com.suromo.magic.ui.theme.MagicTheme

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/1
 * desc   :
 */
@Composable
fun AboutCards(about: About, modifier: Modifier = Modifier){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            item {
                val imageModifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(shape = RoundedCornerShape(120.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(about.imageId),
                        contentDescription = null,
                        modifier = imageModifier,
                        contentScale = ContentScale.Crop,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text(text = about.name,style = typography.h4)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = about.occupation,style = typography.h6)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = about.introduction,style = typography.body1)
                Spacer(modifier = Modifier.height(60.dp))
                Text(text = "Contact me:",style = typography.subtitle1)
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_email),
                        modifier = Modifier.padding(start = 0.dp, bottom = 2.dp, end = 10.dp, top = 2.dp).width(15.dp).height(15.dp),
                        contentDescription = stringResource(id = R.string.hot_upper_case),
                        contentScale = ContentScale.Inside,
                    )
                    Text(text = about.email,style = typography.body1)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Follow my channel:",style = typography.subtitle1)
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_youtube),
                        modifier = Modifier.padding(start = 0.dp, bottom = 2.dp, end = 10.dp, top = 2.dp).width(15.dp).height(15.dp),
                        contentDescription = stringResource(id = R.string.hot_upper_case),
                        contentScale = ContentScale.Inside,
                    )
                    Text(text = about.youtube,style = typography.body1)
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_instagram),
                        modifier = Modifier.padding(start = 0.dp, bottom = 2.dp, end = 10.dp, top = 2.dp).width(15.dp).height(15.dp),
                        contentDescription = stringResource(id = R.string.hot_upper_case),
                        contentScale = ContentScale.Inside,
                    )
                    Text(text = about.instagram,style = typography.body1)
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_tiktok),
                        modifier = Modifier.padding(start = 0.dp, bottom = 2.dp, end = 10.dp, top = 2.dp).width(15.dp).height(15.dp),
                        contentDescription = stringResource(id = R.string.hot_upper_case),
                        contentScale = ContentScale.Inside,
                    )
                    Text(text = about.tiktok,style = typography.body1)
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        modifier = Modifier.padding(start = 0.dp, bottom = 2.dp, end = 10.dp, top = 2.dp).width(15.dp).height(15.dp),
                        contentDescription = stringResource(id = R.string.hot_upper_case),
                        contentScale = ContentScale.Inside,
                    )
                    Text(text = about.facebook,style = typography.body1)
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_twitter),
                        modifier = Modifier.padding(start = 0.dp, bottom = 2.dp, end = 10.dp, top = 2.dp).width(15.dp).height(15.dp),
                        contentDescription = stringResource(id = R.string.hot_upper_case),
                        contentScale = ContentScale.Inside,
                    )
                    Text(text = about.twitter,style = typography.body1)
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_wechat),
                        modifier = Modifier.padding(start = 0.dp, bottom = 2.dp, end = 10.dp, top = 2.dp).width(15.dp).height(15.dp),
                        contentDescription = stringResource(id = R.string.hot_upper_case),
                        contentScale = ContentScale.Inside,
                    )
                    Text(text = about.wechat,style = typography.body1)
                }
            }
        }

    }
}

// Preview section

@Preview("Default colors")
@Preview("Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Font scaling 1.5", fontScale = 1.5f)
@Composable
fun TutorialPreview() {
    TutorialPreviewTemplate()
}

@Composable
fun TutorialPreviewTemplate() {
    val about = about

    MagicTheme {
        Surface {
            AboutCards(about)
        }
    }
}