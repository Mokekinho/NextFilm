package com.example.nextfilm.ui.elements.details

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.nextfilm.ui.state.MovieDetailsViewModel

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
                ,
                contentAlignment = Alignment.Center
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                //.verticalScroll(scrollState)
        ) {
            item {


                ImageBox(
                    movieDetails.backdropUrl,
                    movieDetails.name
                )
            }
            item {
                Column(
                    modifier = modifier
                        .fillMaxSize()

                ) {
                    DefaultVerticalSpacer()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Text(
                            text = movieDetails.runtime,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        DefaultHorizontalSpacer()
                        Text(
                            text = movieDetails.releaseYear,
                            style = MaterialTheme.typography.bodyLarge
                        )

                        DefaultHorizontalSpacer()

                        Image(
                            painterResource(movieDetails.ageCertification),
                            contentDescription = "Movie Rating",
                            modifier = Modifier
                                .height(30.dp),
                            contentScale = ContentScale.Fit
                        )

                        DefaultHorizontalSpacer()

                        VoteAverageBox(
                            movieDetails.voteAverage,
                            modifier = Modifier
                                .size(30.dp)
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
                                    movieDetails.trailerUrl.toUri()
                                )
                            )

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
                    DefaultVerticalSpacer()
                    Text(
                        text = "Cast",
                        style = MaterialTheme.typography.headlineMedium
                    )

                }
            }

            item{
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(movieDetails.cast){
                        CastCard(
                            cast = it,
                            modifier = Modifier
                                .padding(5.dp)
                                .width(150.dp)
                                .height(200.dp)
                        )
                    }
                }
            }
        }
    }
}

