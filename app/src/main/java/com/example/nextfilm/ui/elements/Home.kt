package com.example.nextfilm.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.nextfilm.navigation.MovieListNav
import com.example.nextfilm.ui.state.HomeViewModel
import com.example.nextfilm.ui.util.MovieEntry


@Composable
fun Home(
    navController: NavController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    //val entryList : List<MoviesListEntry> = emptyList()

    val state by homeViewModel.state.collectAsStateWithLifecycle()

    val trendingMoviesList = state.trendingList

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        Text(
            text = "Trending",
            style = MaterialTheme.typography.displaySmall
        )

        LazyRow() {
            items(trendingMoviesList){
                MovieEntry(
                    entry = it,
                    navController = navController,
                )
            }
        }

        TextButton(
            onClick = {
                navController.navigate(MovieListNav("Trending"))
            }
        ) {
            Text("See All Trending Movies")
        }
    }
}





