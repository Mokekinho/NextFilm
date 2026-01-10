package com.example.nextfilm.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nextfilm.data.models.MoviesListEntry
import com.example.nextfilm.navigation.MovieListNav
import com.example.nextfilm.navigation.MovieNav
import com.example.nextfilm.ui.state.HomeViewModel
import com.example.nextfilm.ui.theme.TextSecondary
import com.example.nextfilm.ui.util.MovieEntry
import com.example.nextfilm.util.Constants.BASE_IMAGE_URL


@Composable
fun Home(
    navController: NavController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val entryList : List<MoviesListEntry> = emptyList()

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        Text(
            text = "Trending",
            style = MaterialTheme.typography.displaySmall
        )

        LazyRow() {
            items(entryList){
                MovieEntry(
                    entry = it,
                    navController = navController,
                )
            }
        }

        Button(
            onClick = {
                navController.navigate(MovieListNav("Trending"))
            }
        ) {
            Text("Trending")
        }
    }
}





