package com.example.nextfilm.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.nextfilm.ui.elements.details.DefaultVerticalSpacer
import com.example.nextfilm.ui.elements.details.ErrorMessage
import com.example.nextfilm.ui.state.TvViewModel
import com.example.nextfilm.ui.util.ImageBoxEntry
import com.example.nextfilm.ui.util.MovieEntry

@Composable
fun Tv(
    navController: NavController,
    modifier: Modifier = Modifier,
    tvViewModel: TvViewModel = hiltViewModel()
) {



    //val entryList : List<MoviesListEntry> = emptyList()

    val state by tvViewModel.state.collectAsStateWithLifecycle()

    val trendingTvList = state.trendingTvList
    val popularTvList = state.popularTvList
    val topRatedTvList = state.topRatedTvList

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
                    tvViewModel.loadData()
                }
            )
        }
    }
    else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

        ) {
            item {


                val pagerState = rememberPagerState() {
                    trendingTvList.size
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
                            entry = trendingTvList[pageIndex],
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
                        items(popularTvList){
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
                        Text("See All Popular Tv")
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
                        items(topRatedTvList) {
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
                        Text("See All Top Rated Tv")
                    }
                }
            }
        }
    }
}