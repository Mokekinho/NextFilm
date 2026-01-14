package com.example.nextfilm.ui.elements.details

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nextfilm.ui.state.MovieDetailsViewModel
import com.example.nextfilm.util.Constants.BASE_IMAGE_URL
import com.example.nextfilm.util.Constants.IMAGE_FORMAT_ORIGINAL

//TODO, a API deles não retorna tudo de uma fez, então o ideal mais pra frente vai ser ir vendo outros endpoints pra retornar mais coisas uteis, ver tudo que eu quero rpa fazer tudo numa requisição
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

    val context = LocalContext.current

    if(movieDetails == null &&  !isLoading){
        if(error == null){
            Box(
                modifier = modifier
                    .fillMaxSize()
            ){
                CircularProgressIndicator()
            }
            viewModel.loadDetails(id)
        }
        else{
            ErrorMessage(
                error,
                onRetry = {
                    viewModel.loadDetails(id)
                },
                modifier = modifier
                    .fillMaxSize()
            )
        }


    }
    else if(movieDetails != null){
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            ImageBox(
                movieDetails.backdropUrl,
                movieDetails.name
            )

            Column(
                modifier = modifier
                    .fillMaxSize()

            ) {
                DefaultVerticalSpacer()

                Text(
                    text = movieDetails.releaseYear,
                    style = MaterialTheme.typography.bodyLarge
                )

                DefaultVerticalSpacer()

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {

                    }
                ) {
                    Text(
                        text = "Watch Trailer",
                        fontSize = 17.sp
                    )
                }

                DefaultVerticalSpacer()

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                movieDetails.homePageUrl.toUri()
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(
                        text = "Go To Home Page",
                        fontSize = 17.sp
                    )
                }

                DefaultVerticalSpacer()

                Text(
                    text = movieDetails.overview,
                    style = MaterialTheme.typography.bodyLarge
                )
                DefaultVerticalSpacer()

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    movieDetails.genres.forEach() {
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.bodyMedium,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .clickable(
                                    onClick = {

                                    }
                                )
                        )

                        Text(
                            text = ", ",
                            style = MaterialTheme.typography.bodyMedium,
                        )

                    }
                }

            }
        }
    }
}
