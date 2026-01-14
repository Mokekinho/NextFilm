package com.example.nextfilm.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nextfilm.navigation.MovieListNav
import com.example.nextfilm.ui.state.HomeViewModel
import com.example.nextfilm.ui.util.ImageBoxEntry
import com.example.nextfilm.ui.util.MovieEntry
import com.example.nextfilm.util.Constants.BASE_IMAGE_URL
import com.example.nextfilm.util.Constants.IMAGE_FORMAT_ORIGINAL


@Composable
fun Home(
    navController: NavController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    //val entryList : List<MoviesListEntry> = emptyList()

    val state by homeViewModel.state.collectAsStateWithLifecycle()

    val trendingMoviesList = state.trendingList

    //val scrollState = rememberScrollState()

    //Tentar descobrir como scrolar isso aqui
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            //.verticalScroll(scrollState)
    ) {
        item {


            val pagerState = rememberPagerState() {
                trendingMoviesList.size
            }

            //Meu carrossel em tela maxima

            HorizontalPager(
                pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    //.fillMaxHeight(0.7f) não funciona pq em Scrolls não se sabe o valor maximo
                    .height(550.dp)
            ) { pageIndex ->
                ImageBoxEntry(
                    entry = trendingMoviesList[pageIndex],
                    navController = navController,
                )
            }
        }
        item {


            Column(
                modifier = modifier //agora sim vou usar o padding que esta sendo passado
                    .fillMaxSize()
            ) {

                Text(
                    text = "Trending",
                    style = MaterialTheme.typography.displaySmall
                )

                LazyRow() {
                    items(trendingMoviesList) {
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
    }
}





