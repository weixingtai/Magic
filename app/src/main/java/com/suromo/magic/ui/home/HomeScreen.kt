package com.suromo.magic.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suromo.common.log.MLog
import com.suromo.magic.data.repository.mock.impl.BlockingFakePostsRepository
import com.suromo.magic.ui.bean.Result
import com.suromo.magic.ui.home.widget.HomePostList
import com.suromo.magic.ui.rememberContentPaddingForScreen
import com.suromo.magic.ui.theme.MagicTheme
import kotlinx.coroutines.runBlocking

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/29
 * desc   : 首页界面显示
 */
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    showTopBar: Boolean,
    onSelectPost: (String) -> Unit,
    onRefreshPosts: () -> Unit,
    onErrorDismiss: (Long) -> Unit,
    openDrawer: () -> Unit,
    homeListLazyListState: LazyListState,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    searchInput: String = "",
    onSearchInputChanged: (String) -> Unit,
) {
    HomeListScreen(
        uiState = uiState,
        showTopBar = showTopBar,
        onRefreshPosts = onRefreshPosts,
        onErrorDismiss = onErrorDismiss,
        openDrawer = openDrawer,
        homeListLazyListState = homeListLazyListState,
        scaffoldState = scaffoldState,
        modifier = modifier
    ) { hasPostsUiState, contentModifier ->
        HomePostList(
            postsFeed = hasPostsUiState.postsFeed,
            showExpandedSearch = !showTopBar,
            onArticleTapped = onSelectPost,
            contentPadding = rememberContentPaddingForScreen(
                additionalTop = if (showTopBar) 0.dp else 8.dp
            ),
            modifier = contentModifier,
            state = homeListLazyListState,
            searchInput = searchInput,
            onSearchInputChanged = onSearchInputChanged
        )
        MLog.d("here is home screen")
    }
}

/**
 * 首页界面预览
 */
@Preview("Home Screen")
@Preview("Home Screen(Dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Home Screen(Big Font)", fontScale = 1.5f)
@Composable
fun PreviewHomeScreen() {
    val postsFeed = runBlocking {
        (BlockingFakePostsRepository().getPostsFeed() as Result.Success).data
    }
    MagicTheme {
        HomeScreen(
            uiState = HomeUiState.HasPosts(
                postsFeed = postsFeed,
                selectedPost = postsFeed.allPosts[0],
                isArticleOpen = false,
                favorites = emptySet(),
                isLoading = false,
                errorMessages = emptyList(),
                searchInput = ""
            ),
            showTopBar = true,
            onSelectPost = {},
            onRefreshPosts = {},
            onErrorDismiss = {},
            openDrawer = {},
            homeListLazyListState = rememberLazyListState(),
            scaffoldState = rememberScaffoldState(),
            onSearchInputChanged = {}
        )
    }
}