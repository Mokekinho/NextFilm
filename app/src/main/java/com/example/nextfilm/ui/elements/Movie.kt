package com.example.nextfilm.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nextfilm.navigation.MovieListNav
import com.example.nextfilm.ui.elements.details.DefaultVerticalSpacer
import com.example.nextfilm.ui.elements.details.ErrorMessage
import com.example.nextfilm.ui.state.HomeViewModel
import com.example.nextfilm.ui.state.MovieViewModel
import com.example.nextfilm.ui.util.ImageBoxEntry
import com.example.nextfilm.ui.util.MovieEntry
import com.example.nextfilm.util.Constants.BASE_IMAGE_URL
import com.example.nextfilm.util.Constants.IMAGE_FORMAT_ORIGINAL
import kotlinx.coroutines.delay


@Composable
fun Movie(
    navController: NavController,
    modifier: Modifier = Modifier,
    movieViewModel: MovieViewModel = hiltViewModel(),
) {

    val state by movieViewModel.state.collectAsStateWithLifecycle()

    val trendingMovieList = state.trendingMovieList
    val popularMovieList = state.popularMovieList
    val topRatedMovieList = state.topRatedMovieList

    val isLoading = state.isLoading
    val isLoaded = state.isLoaded
    val error = state.error

    if(!isLoaded){
        if(isLoading){
            Box(
                modifier = modifier
                    .fillMaxSize()
                ,
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        else if(error != null){
            ErrorMessage(
                error,
                onRetry = {
                    movieViewModel.loadData()
                }
            )
        }
    }
    else { // se os dados ja estiverem carregados
        //Tentar descobrir como scrolar isso aqui
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

        ) {
            item {


                val pagerState = rememberPagerState() {
                    trendingMovieList.size
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    HorizontalPager(
                        pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            //.fillMaxHeight(0.7f) não funciona pq em Scrolls não se sabe o valor maximo
                            .height(700.dp)
                    ) { pageIndex ->

                        ImageBoxEntry(
                            entry = trendingMovieList[pageIndex],
                            navController = navController,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            val color =
                                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                            Box(
                                modifier = Modifier
                                    .padding(3.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(10.dp)
                            )
                        }
                    }
                }
            }

            item {
                DefaultVerticalSpacer()
                Column(
                    modifier = modifier //agora sim vou usar o padding que esta sendo passado
                        .fillMaxSize()
                ) {

                    Text(
                        text = "Popular",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    LazyRow() {
                        items(popularMovieList) {
                            MovieEntry(
                                entry = it,
                                navController = navController,
                            )
                        }
                    }

                    TextButton(
                        onClick = {
                            //navController.navigate(MovieListNav("Trending")) //TODO, Criar a tela que carrega varios filmes populares
                        }
                    ) {
                        Text("See All Popular Movies")
                    }
                }
            }
            item {
                Column(
                    modifier = modifier //agora sim vou usar o padding que esta sendo passado
                        .fillMaxSize()
                ) {

                    Text(
                        text = "Top Rated",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    LazyRow() {
                        items(topRatedMovieList) {
                            MovieEntry(
                                entry = it,
                                navController = navController,
                            )
                        }
                    }

                    TextButton(
                        onClick = {
                            //navController.navigate(MovieListNav("Trending")) //TODO, Criar a tela que carrega varios filmes mais avaliados
                        }
                    ) {
                        Text("See All Top Rated Movies")
                    }
                }
            }
        }
    }
}