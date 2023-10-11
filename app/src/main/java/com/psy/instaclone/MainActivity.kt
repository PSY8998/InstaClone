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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
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
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.psy.instaclone.ui.theme.InstaCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstaCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        Toolbar()
                        Story()
                        Post()
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


@Preview
@Composable
fun Story() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .width(intrinsicSize = IntrinsicSize.Min)
    ) {
        Image(
            painter = rememberImagePainter(data = "https://randomuser.me/api/portraits/men/1.jpg"),
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
            text = "Story story name very lare",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun Post() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PostHeader()
        PostImage()
        PostFooter()
    }
}

@Composable
fun PostImage() {
    Image(
        painter = rememberImagePainter(data = "https://source.unsplash.com/random/400x300?1"),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4 / 3f)
    )
}

@Preview
@Composable
fun PostHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = "https://randomuser.me/api/portraits/men/1.jpg"),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
                .clip(shape = CircleShape)
        )

        Text(
            text = "ninja",
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

@Preview
@Composable
fun PostFooter() {
    Column {
        Row {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
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
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.ic_filled_bookmark),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }

        Text(
            text = "100 likes",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Text(
            text = "View all 20 comments",
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Text(
            text = "1 hour ago",
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }
}