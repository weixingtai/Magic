package com.suromo.magic.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.suromo.magic.R
import com.suromo.magic.data.source.local.impl.post4
import com.suromo.magic.data.source.local.impl.posts
import com.suromo.magic.ui.bean.Post
import com.suromo.magic.ui.theme.*
/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/29
 * desc   : 首页卡片展示列表
 */
@Composable
fun PostCardTop(post: Post, modifier: Modifier = Modifier) {
    val typography = MaterialTheme.typography
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val imageModifier = Modifier
            .heightIn(min = 180.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
        Box{
            Image(
                painter = painterResource(post.imageId),
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            if (post.hot){
                Row(modifier = Modifier.padding(5.dp).clip(shape = MaterialTheme.shapes.medium).background(Red400), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.padding(start = 5.dp, bottom = 2.dp, end = 0.dp, top = 2.dp).width(15.dp).height(15.dp),
                        imageVector = Icons.Filled.LocalFireDepartment,
                        contentDescription = stringResource(id = R.string.hot_upper_case),
                        contentScale = ContentScale.Inside,
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text(
                        text = stringResource(id = R.string.hot_upper_case),
                        style = typography.subtitle2,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 0.dp, bottom = 2.dp, end = 7.dp, top = 2.dp)

                    )
                }
            }


        }
        
        Spacer(Modifier.height(16.dp))

        Text(
            text = post.price,
            style = typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = post.space,
            style = typography.subtitle2,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = post.location,
                style = typography.subtitle2,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}

@Preview("Default colors")
@Preview("Dark theme", uiMode = UI_MODE_NIGHT_YES)
@Preview("Font scaling 1.5", fontScale = 1.5f)
@Composable
fun TutorialPreview() {
    TutorialPreviewTemplate()
}

@Composable
fun TutorialPreviewTemplate() {
    val post = post4

    MagicTheme {
        Surface {
            PostCardTop(post)
        }
    }
}
