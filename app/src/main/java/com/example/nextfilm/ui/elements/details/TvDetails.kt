package com.example.nextfilm.ui.elements.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.nextfilm.ui.state.MovieDetailsViewModel
import com.example.nextfilm.ui.state.TvDetailsViewModel

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

    if(tvDetails == null &&  !isLoading){
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
    else if(tvDetails!= null){
        val scrollState = rememberScrollState()
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            ImageBox(
                tvDetails.backdropUrl,
                tvDetails.name
            )

            DefaultVerticalSpacer()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                verticalAlignment = Alignment.CenterVertically
            ){
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
                    .fillMaxWidth()
                ,
                onClick = {

                }
            ) {
                Text(
                    text = "Go To HomePage",
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
                    .fillMaxWidth()
                ,
                verticalAlignment = Alignment.CenterVertically
            ){
                tvDetails.genres.forEach(){
                    Text(
                        text = it.name ,
                        style = MaterialTheme.typography.bodyMedium,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .clickable(
                                onClick = {

                                }
                            )
                    )

                    Text(
                        text = ", " ,
                        style = MaterialTheme.typography.bodyMedium,
                    )

                }
            }

        }
    }
}