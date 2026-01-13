package com.example.nextfilm.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.nextfilm.ui.elements.Home
import com.example.nextfilm.ui.elements.MovieListFromTopic
import kotlinx.serialization.Serializable



@Composable
fun AppNavigation( //Live templates
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = HomeNav
    ) {
        composable<HomeNav> {
            Home(navController, modifier)
        }
        composable<MovieDetailsNav> {
            //Add the movie composable
        }
        composable<TvDetailsNav> {
            //Add the movie composable
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
data class MovieDetailsNav(val id: Int) // Is the screen that show the details of the movie

@Serializable
data class TvDetailsNav(val id: Int)
@Serializable
data class MovieListNav(val topic: String, )