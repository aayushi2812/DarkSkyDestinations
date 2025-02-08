package com.example.darkskydestinations.Views

import android.annotation.SuppressLint
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.darkskydestinations.R
import com.example.darkskydestinations.ViewModels.ApodViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ApodScreen(viewModel: ApodViewModel) {

    //ApodScreen gets the picture of the day from NASA Api and displays it on the screen

    val apod = viewModel.apod.value

    Scaffold (
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.backgroundimage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column {
                if (apod == null) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp).verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = apod.title,
                            color = Color.White,
                            fontSize = 30.sp,
                            modifier = Modifier.fillMaxWidth().padding(30.dp),
                            textAlign = TextAlign.Center
                        )

                        if (apod.media_type == "video") {

                            val videoId = extractYouTubeVideoId(apod.url)
                            if (videoId != null) {
                                YouTubePlayerScreen(videoId = videoId)
                            } else {
                                Text("Invalid YouTube URL")
                            }

                        } else {
                            AsyncImage(
                                model = apod.url,
                                contentDescription = apod.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(16f / 9f),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.height(50.dp))
                        Text(
                            text = apod.explanation,
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth().padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}
//Extracting the youtube video
fun extractYouTubeVideoId(url: String): String? {
    val pattern = "(?<=embed/|v=|/v/|youtu.be/|/embed/|/v/|/e/|watch\\?v=|\\?v=)([^#\\&\\?]*)"
    val compiledPattern = Regex(pattern)
    return compiledPattern.find(url)?.value
}

//Showing the youtube video
@Composable
fun YouTubePlayerScreen(videoId: String) {
    AndroidView(
        factory = { context ->
            YouTubePlayerView(context).apply {
                // Initialize the player
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        // Load the video
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }
        },
        modifier = Modifier.fillMaxSize().height(150.dp)
    )
}