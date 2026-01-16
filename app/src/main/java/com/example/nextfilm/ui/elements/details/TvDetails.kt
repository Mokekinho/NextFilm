package com.example.nextfilm.ui.elements.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.nextfilm.ui.state.MovieDetailsViewModel
import com.example.nextfilm.ui.state.TvDetailsViewModel
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.nextfilm.data.sources.remote.responses.details.tv.Season
import com.example.nextfilm.util.Constants.BASE_IMAGE_URL
import com.example.nextfilm.util.Constants.IMAGE_FORMAT_W200

@Composable
fun TvDetails(
    id: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: TvDetailsViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val tvDetails = state.tvDetails
    val isLoading = state.isLoading
    val error = state.error

    val context = LocalContext.current

    if(tvDetails == null &&  !isLoading){
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
    else if(tvDetails!= null) {
        //val scrollState = rememberScrollState()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
            //.verticalScroll(scrollState)
        ) {
            item {
                ImageBox(
                    tvDetails.backdropUrl,
                    tvDetails.name
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
                            text = tvDetails.numberOfSeasons.toString() + " " + "Seasons",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        DefaultHorizontalSpacer()

                        Text(
                            text = tvDetails.firstEpisodeYear,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        // se eu quiser colocar a data de termino
//                Text(
//                    text = tvDetails.firstEpisodeYear + " - " + tvDetails.lastEpisodeYear,
//                    style = MaterialTheme.typography.bodyLarge
//                )


                    }



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
                                    tvDetails.homePageUrl.toUri()
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
                        text = tvDetails.overview,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    DefaultVerticalSpacer()

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        tvDetails.genres.forEach() {
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
            item{
                DefaultVerticalSpacer()
                // Seasons
                Column(
                    modifier = modifier
                        .fillMaxSize()
                ) {

                    tvDetails.seasons.forEach {
                        SeasonCard(it)
                        DefaultVerticalSpacer()
                    }

                }

            }
        }
    }
}

@Composable
fun SeasonCard(
    season: Season,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
        ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            //verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = BASE_IMAGE_URL + IMAGE_FORMAT_W200 + season.poster_path,
                contentDescription = "Poster of movie: ${season.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                //.height(500.dp)
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(5.dp)
            ) {
                Text(
                    season.name,
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    season.overview,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }


}