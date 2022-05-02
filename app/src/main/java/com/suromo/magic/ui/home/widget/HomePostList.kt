package com.suromo.magic.ui.home.widget

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suromo.magic.data.source.local.impl.posts
import com.suromo.magic.ui.bean.PostsFeed
import com.suromo.magic.ui.theme.MagicTheme

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/05/2022/5/2
 * desc   : 首页列表条目
 */
@Composable
fun HomePostList(
    postsFeed: PostsFeed,
    showExpandedSearch: Boolean,
    onArticleTapped: (postId: String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    state: LazyListState = rememberLazyListState(),
    searchInput: String = "",
    onSearchInputChanged: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        state = state
    ) {
        if (showExpandedSearch) {
            item {
                HomeSearchBar(
                    Modifier.padding(horizontal = 16.dp),
                    searchInput = searchInput,
                    onSearchInputChanged = onSearchInputChanged,
                )
            }
        }
        item {
            Column {
                postsFeed.allPosts.forEach { post ->
                    PostCard(
                        post = post,
                        modifier = Modifier.clickable(onClick = { onArticleTapped(post.id) })
                    )
                    PostListDivider()
                }
            }
        }
    }
}

/**
 * 分割线
 */
@Composable
private fun PostListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Preview("Home PostList")
@Preview("Home PostList(Dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Home PostList(Big Font)", fontScale = 1.5f)
@Composable
fun PreviewHomePostList() {
    HomePostListTemplate()
}

@Composable
fun HomePostListTemplate() {

    MagicTheme {
        Surface {
            HomePostList(
                postsFeed = posts,
                false,
                onArticleTapped = {},
                modifier = Modifier,
                contentPadding = PaddingValues(0.dp),
                state = rememberLazyListState(),"",{})
        }
    }
}