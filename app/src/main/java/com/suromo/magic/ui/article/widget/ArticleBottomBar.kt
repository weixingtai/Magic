package com.suromo.magic.ui.article.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suromo.magic.ui.theme.BookmarkButton
import com.suromo.magic.ui.theme.MagicTheme
import com.suromo.magic.ui.theme.ShareButton

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/2
 * desc   : 文章详情界面底部栏
 */
@Composable
fun ArticleBottomBar(
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onSharePost: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(elevation = 8.dp, modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Vertical))
                .height(56.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            BookmarkButton(isBookmarked = isFavorite, onClick = onToggleFavorite)
            ShareButton(onClick = onSharePost)
        }
    }
}

@Preview("Article BottomBar")
@Preview("Article BottomBar(Dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Article BottomBar(Big Font)", fontScale = 1.5f)
@Composable
fun PreviewArticleBottomBar() {
    ArticleBottomBarTemplate()
}

@Composable
fun ArticleBottomBarTemplate() {
    MagicTheme {
        Surface {
            ArticleBottomBar(
                isFavorite = false,
                onToggleFavorite = {},
                onSharePost = {},
                modifier = Modifier
            )
        }
    }
}