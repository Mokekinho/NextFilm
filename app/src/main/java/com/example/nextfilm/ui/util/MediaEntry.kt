package com.example.nextfilm.ui.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nextfilm.data.models.MediaListEntry
import com.example.nextfilm.navigation.MovieDetailsNav

import com.example.nextfilm.util.Constants.BASE_IMAGE_URL

@Composable
fun MovieEntry(
    entry: MediaListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,

    ){
    Card(
        modifier = modifier
            .padding(5.dp)
            .clickable(
                onClick = {
                    navController.navigate(
                        MovieDetailsNav(
                            entry.id
                        )
                    )
                }
            )
        ,

        ) {
        Box(
            modifier = Modifier
            ,
            contentAlignment = Alignment.Center
        ){
            AsyncImage(
                model = BASE_IMAGE_URL + entry.imageUrl,
                contentDescription = "Poster of movie: ${entry.movieName}",
                modifier = Modifier
            ) // pra colocar mensagem de loading tem que usar a função SubcomposeAsyncImage
        }

    }
}