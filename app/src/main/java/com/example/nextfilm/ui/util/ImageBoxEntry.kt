package com.example.nextfilm.ui.util

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nextfilm.data.models.MediaListEntry
import com.example.nextfilm.navigation.MovieDetailsNav
import com.example.nextfilm.navigation.TvDetailsNav
import com.example.nextfilm.util.Constants.BASE_IMAGE_URL
import com.example.nextfilm.util.Constants.IMAGE_FORMAT_ORIGINAL

@Composable
fun ImageBoxEntry(
    entry: MediaListEntry,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Log.d("ImageBoxEntry", "compondo a Imagem")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = {
                    when(entry.mediaType){
                        "tv" -> navController.navigate(
                            TvDetailsNav(
                                entry.id
                            )
                        )
                        "movie" -> navController.navigate(
                            MovieDetailsNav(
                                entry.id
                            )
                        )
                    }
                }
            )
    )
    {
        AsyncImage(
            model = BASE_IMAGE_URL + IMAGE_FORMAT_ORIGINAL + entry.imageUrl,
            contentDescription = "Poster of movie: ${entry.movieName}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
            //.height(500.dp)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.background
                        )
                        // ele por padrao ja começa no zero e vai ate o fim
                    ),
                )
        )
    }
}