package com.example.nextfilm.ui.elements

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.nextfilm.data.models.MoviesListEntry
import com.example.nextfilm.ui.state.MovieListFromTopicViewModel
import com.example.nextfilm.ui.util.MovieEntry

@Composable
fun MovieListFromTopic(
    topic: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MovieListFromTopicViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val error = state.error
    val movieList = state.movieList
    val isLoading = state.isLoading
    val endReached = state.endReached

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = topic,
            style = MaterialTheme.typography.displaySmall
        )

        LazyVerticalGrid(
            GridCells.Fixed(2),
            //contentPadding = PaddingValues(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalArrangement = Arrangement.SpaceBetween,
            ) {
            itemsIndexed(movieList) { index, movie ->

                //  Pagina quando tiver chegando perto do final da lista
                //Log.d("Arquivo da Internet Ex:", "$movie")
                if (
                    index >= movieList.size - 4 && !isLoading && !endReached
                ) {
                    viewModel.loadMoviePaginated()
                }
                MovieEntry(
                    movie,
                    navController

                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            if(state.isLoading){
                CircularProgressIndicator()
            }
            else if(error != null){
                RetryLoad(
                    error,
                    onRetry = {
                        viewModel.loadMoviePaginated()
                    }
                )
            }
        }

    }
}

@Composable
fun RetryLoad(
    errorMessage: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Error: $errorMessage",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier
            .height(10.dp)
        )
        Button(
            onClick = {
                onRetry()
            }
        ) {
            Text("Retry")
        }
    }
}