@file:OptIn(ExperimentalCoilApi::class)

package com.psy.instaclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.psy.instaclone.models.Post
import com.psy.instaclone.models.PostsProvider
import com.psy.instaclone.models.User
import com.psy.instaclone.ui.theme.InstaCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            InstaCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .systemBarsPadding()
                    ) {
                        val posts = PostsProvider.livePosts.collectAsState()

                        Toolbar()
                        LazyColumn {
                            item(
                                key = "user_stories"
                            ) {
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    contentPadding = PaddingValues(horizontal = 16.dp),
                                ) {
                                    items(
                                        items = PostsProvider.users(),
                                        key = { user ->
                                            user.id
                                        }
                                    ) { user ->
                                        Story(user = user)
                                    }
                                }
                            }
                            items(
                                items = posts.value,
                                key = { post ->
                                    post.id
                                }
                            ) { post ->
                                Post(post = post)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun Toolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                ImageVector.vectorResource(id = R.drawable.ic_instagram),
                contentDescription = ""
            )
        }
        Icon(
            ImageBitmap.imageResource(id = R.drawable.ic_dm),
            contentDescription = ""
        )
    }
}


@Composable
fun Story(
    user: User
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .width(intrinsicSize = IntrinsicSize.Min)
    ) {
        Image(
            painter = rememberImagePainter(data = user.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .border(
                    width = 2.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFd71069),
                            Color(0xFFe25d6a),
                            Color(0xFFe9ad55),
                        )
                    ),
                    shape = CircleShape
                )
                .padding(6.dp)
                .clip(shape = CircleShape)

        )
        Text(
            text = user.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun Post(
    post: Post
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PostHeader(
            userImageUrl = post.user.imageUrl,
            userName = post.user.name
        )
        PostImage(
            imageUrl = post.imageUrl
        )
        PostFooter(
            id = post.id,
            likesCount = post.likesCount,
            commentsCount = post.commentsCount,
            postedAt = post.postedAt,
            isBookmarked = post.isBookmarked,
            isLiked = post.isLiked
        )
    }
}

@Composable
fun PostImage(
    imageUrl: String
) {
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4 / 3f)
    )
}

@Composable
fun PostHeader(
    userImageUrl: String,
    userName: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = userImageUrl),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
                .clip(shape = CircleShape)
        )

        Text(
            text = userName,
            modifier = Modifier
                .weight(1f)
        )

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = null
            )
        }
    }
}


@Composable
fun PostFooter(
    id: Long,
    likesCount: Int,
    commentsCount: Int,
    postedAt: Long,
    isLiked: Boolean,
    isBookmarked: Boolean

) {
    Column {
        Row {
            IconButton(onClick = {
                if (isLiked)
                    PostsProvider.unlikePost(id)
                else
                    PostsProvider.likePost(id)
            }) {
                Icon(
                    painter = painterResource(
                        id = if (isLiked)
                            R.drawable.ic_filled_favorite
                        else
                            R.drawable.ic_outlined_favorite
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = Color.Unspecified

                    )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(R.drawable.ic_outlined_comment),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_dm),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            IconButton(onClick = {
                if (isBookmarked) PostsProvider.unBookmark(id)
                else PostsProvider.bookmark(id)
            }) {
                Icon(
                    painter = painterResource(if (isBookmarked) R.drawable.ic_filled_bookmark else R.drawable.ic_outlined_bookmark),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }

        Text(
            text = "$likesCount likes",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Text(
            text = "View all $commentsCount comments",
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Text(
            text = "$postedAt hour ago",
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }
}

@Preview
@Composable
fun Bottombar() {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        BottomNavigationItem(
            selected = true,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = null
                )
            }
        )

    }
}

private enum class HomeSection(
    val icon: Int,
    val selectedIcon: Int
) {
    Home(R.drawable.ic_outlined_home, R.drawable.ic_filled_home),
    Reels(R.drawable.ic_outlined_reels, R.drawable.ic_filled_reels),
    Add(R.drawable.ic_outlined_add, R.drawable.ic_outlined_add),
    Favorite(R.drawable.ic_outlined_favorite, R.drawable.ic_filled_favorite),
    Profile(R.drawable.ic_outlined_reels, R.drawable.ic_outlined_reels)
}

