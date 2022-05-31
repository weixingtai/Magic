package com.suromo.magic.ui.article

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suromo.magic.R
import com.suromo.magic.data.repository.mock.impl.BlockingFakePostsRepository
import com.suromo.magic.data.source.local.impl.post3
import com.suromo.magic.ui.article.widget.ArticleBottomBar
import com.suromo.magic.ui.bean.Post
import com.suromo.magic.ui.bean.Result
import com.suromo.magic.ui.theme.MagicTheme
import com.suromo.magic.util.isScrolled
import kotlinx.coroutines.runBlocking

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/29
 * desc   : 文章详情界面
 */
@Composable
fun ArticleScreen(
    post: Post,
    isExpandedScreen: Boolean,
    onBack: () -> Unit,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState()
) {
    var showUnimplementedActionDialog by rememberSaveable { mutableStateOf(false) }
    if (showUnimplementedActionDialog) {
        FunctionalityNotAvailablePopup { showUnimplementedActionDialog = false }
    }

    Row(modifier.fillMaxSize()) {
        val context = LocalContext.current
        ArticleScreenContent(
            post = post,
            navigationIconContent = if (!isExpandedScreen) {
                {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_navigate_up),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
            } else {
                null
            },
            bottomBarContent = if (!isExpandedScreen) {
                {
                    ArticleBottomBar(
                        isFavorite = isFavorite,
                        onToggleFavorite = onToggleFavorite,
                        onSharePost = { sharePost(post, context) },
                    )
                }
            } else {
                { }
            },
            lazyListState = lazyListState
        )
    }
}

@Composable
private fun ArticleScreenContent(
    post: Post,
    navigationIconContent: @Composable (() -> Unit)? = null,
    bottomBarContent: @Composable () -> Unit = { },
    lazyListState: LazyListState = rememberLazyListState()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_article_background),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(36.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.published_in, post.publication?.name ?: ""),
                            style = MaterialTheme.typography.subtitle2,
                            color = LocalContentColor.current,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .weight(1.5f)
                        )
                    }
                },
                navigationIcon = navigationIconContent,
                elevation = if (!lazyListState.isScrolled) 0.dp else 4.dp,
                backgroundColor = MaterialTheme.colors.surface
            )
        },
        bottomBar = bottomBarContent
    ) { innerPadding ->
        PostContent(
            post = post,
            modifier = Modifier
                // innerPadding takes into account the top and bottom bar
                .padding(innerPadding),
            state = lazyListState,
        )
    }
}


/**
 * Display a popup explaining functionality not available.
 *
 * @param onDismiss (event) request the popup be dismissed
 */
@Composable
private fun FunctionalityNotAvailablePopup(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Text(
                text = stringResource(id = R.string.article_functionality_not_available),
                style = MaterialTheme.typography.body2
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.close))
            }
        }
    )
}

/**
 * Show a share sheet for a post
 *
 * @param post to share
 * @param context Android context to show the share sheet in
 */
fun sharePost(post: Post, context: Context) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TITLE, post.title)
        putExtra(Intent.EXTRA_TEXT, post.url)
    }
    context.startActivity(Intent.createChooser(intent, context.getString(R.string.article_share_post)))
}

@Preview("Article Screen")
@Preview("Article Screen(dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Article Screen(big font)", fontScale = 1.5f)
@Composable
fun PreviewArticleScreen() {
    MagicTheme {
        val post = runBlocking {
            (BlockingFakePostsRepository().getPost(post3.id) as Result.Success).data
        }
        ArticleScreen(post, false, {}, false, {})
    }
}