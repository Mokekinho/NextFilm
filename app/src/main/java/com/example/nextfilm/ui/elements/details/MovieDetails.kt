package com.example.nextfilm.ui.elements.details

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nextfilm.ui.state.MovieDetailsViewModel
import com.example.nextfilm.util.Constants.BASE_IMAGE_URL

@Composable
fun MovieDetails(
    id: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val movieDetails = state.movieDetails
    val isLoading = state.isLoading
    val error = state.error

    if(movieDetails == null &&  !isLoading){
        viewModel.loadDetails(id)
        ErrorMessage(
            error,
            modifier = modifier
                .fillMaxSize()
        )
    }
    else if(movieDetails != null){
        val scrollState = rememberScrollState()
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            ImageBox(
                movieDetails.posterUrl,
                movieDetails.name
            )


        }
    }
}

@Composable
fun ErrorMessage(
    error: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
        ,
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
            Text(
                text = error?: "",
                color = MaterialTheme.colorScheme.error
            )
        }
    }

}

@Composable
fun ImageBox(
    posterUrl: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ){
        AsyncImage(
            model = BASE_IMAGE_URL + posterUrl,
            contentDescription = "Poster of movie: $name",
            modifier = Modifier
        )
        Text(
            text = name,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .align(
                    Alignment.BottomCenter
                )
        )
    }


}