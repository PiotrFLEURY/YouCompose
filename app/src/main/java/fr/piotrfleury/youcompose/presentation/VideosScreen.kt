package fr.piotrfleury.youcompose.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import fr.piotrfleury.youcompose.R
import fr.piotrfleury.youcompose.data.models.Snippet
import fr.piotrfleury.youcompose.data.models.Thumbnail
import fr.piotrfleury.youcompose.data.models.Thumbnails
import fr.piotrfleury.youcompose.data.models.Video
import fr.piotrfleury.youcompose.repositories.VideosRepository
import fr.piotrfleury.youcompose.theme.YouComposeTheme
import java.util.*


@Composable
fun VideosScreen(navController: NavHostController) {
    val videos = remember {
        mutableStateListOf<Video>()
    }
    val (error, setError) = remember { mutableStateOf("") }
    val context = LocalContext.current
    VideosRepository.listVideos(
        key = context.getString(R.string.YOUTUBE_API_KEY),
        onSuccess = {
            videos.clear()
            videos.addAll(it)
        },
        onError = {
            setError(it.message ?: "")
        }
    )
    if (error.isEmpty()) {
        VideoList(videos = videos) {
            navController.navigate("videos/${it.id}")
        }
    } else {
        Text(error)
    }
}


@Composable
fun VideoList(videos: List<Video>, onItemClicked: (Video) -> Unit) {
    LazyColumn {
        items(videos, key = { video -> video.id!! }) { video ->
            VideoItem(video, true) {
                onItemClicked(video)
            }
        }
    }
}

@Composable
fun VideoItem(video: Video, collapsed: Boolean = false, onItemClicked: (Video) -> Unit) {

    Column {
        VideoThumbnail(video = video, onItemClicked)
        Text(
            text = video.snippet?.channelTitle ?: "NO CHANNEL TITLE",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = video.snippet?.title ?: "NO TITLE",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )

        if (!collapsed) {
            val description = video.snippet?.description ?: "NO DESCRIPTION"
            Text(
                text = description,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun VideoThumbnail(video: Video, onClick: (Video) -> Unit) {
    BoxWithConstraints {
        val painter =
            rememberImagePainter(data = video.snippet?.thumbnails?.high?.url)
        Image(
            painter = painter,
            contentDescription = video.snippet?.title,
            modifier = Modifier
                .size(this.maxWidth)
                .clickable { onClick(video) },
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    YouComposeTheme {
        VideoList(
            videos = listOf(
                Video(
                    id = "42",
                    kind = "youtube#video",
                    snippet = Snippet(
                        channelId = "42",
                        channelTitle = "H2G2",
                        description = "Great movie",
                        publishedAt = Date(),
                        title = "My video",
                        thumbnails = Thumbnails(
                            default = Thumbnail(
                                url = "https://wpimg.pixelied.com/blog/wp-content/uploads/2020/08/28175258/YoutubeThumbnailSize-1-1280x720.jpg",
                                height = 720,
                                width = 1200,
                            )
                        )
                    )
                )
            ),
            onItemClicked = {}
        )
    }
}