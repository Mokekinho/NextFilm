package com.example.nextfilm.ui.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nextfilm.data.models.MoviesListEntry
import com.example.nextfilm.navigation.MovieNav
import com.example.nextfilm.ui.state.HomeViewModel
import com.example.nextfilm.util.Constants.BASE_IMAGE_URL

@Composable
fun MovieEntry(
    entry: MoviesListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
){
    Card(
        modifier = modifier
            .height(200.dp)
            .width(140.dp)
            .clickable(
                onClick = {
                    navController.navigate(
                        MovieNav(
                            entry.id
                        )
                    )
                }
            )
        ,

        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
            ,
            contentAlignment = Alignment.Center
        ){
            AsyncImage(
                model = BASE_IMAGE_URL + entry.imageUrl,
                contentDescription = "Poster of movie: ${entry.movieName}",
                modifier = Modifier
                    .fillMaxSize()
            ) // pra colocar mensagem de loading tem que usar a função SubcomposeAsyncImage
        }

    }
}