package fr.piotrfleury.youcompose.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import fr.piotrfleury.youcompose.repositories.VideosRepository


@Composable
fun VideoScreen(videoId: String) {
    val video = VideosRepository.videos.firstOrNull { it.id == videoId }

    if (video != null) {
        val context = LocalContext.current
        val intentWatchVideo = remember {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=${video.id}")
            )
        }
        VideoItem(video = video, collapsed = false, onItemClicked = { context.startActivity(intentWatchVideo) })
    } else {
        Text("Video not found")
    }

}