package com.example.nextfilm.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.nextfilm.ui.MainScreen
import com.example.nextfilm.ui.elements.Home
import com.example.nextfilm.ui.elements.MovieListFromTopic
import com.example.nextfilm.ui.elements.details.MovieDetails
import com.example.nextfilm.ui.elements.details.TvDetails
import kotlinx.serialization.Serializable



@Composable
fun AppNavigation( //Live templates
    navController : NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = HomeNav
    ) {
        composable<HomeNav> {
            Home(navController, modifier)
        }
        composable<TvNav> {

        }
        composable<MovieNav> {

        }
        composable<MovieDetailsNav> {
            val args: MovieDetailsNav = it.toRoute()
            MovieDetails(
                args.id,
                navController,
                modifier
            )
            //Add the movie composable
        }
        composable<TvDetailsNav> {
            val args: TvDetailsNav = it.toRoute()
            TvDetails(
                args.id,
                navController,
                modifier
            )
        }
        composable<MovieListNav>{
            val args: MovieListNav = it.toRoute()
            MovieListFromTopic(
                args.topic,
                navController,
                modifier
            )
        }
    }
}

@Serializable
object HomeNav // Is the screen that shows the moovies

@Serializable
object MovieNav

@Serializable
object TvNav
@Serializable
data class MovieDetailsNav(val id: Int) // Is the screen that show the details of the movie

@Serializable
data class TvDetailsNav(val id: Int)
@Serializable
data class MovieListNav(val topic: String, )

@Serializable
object MainScreenNav

