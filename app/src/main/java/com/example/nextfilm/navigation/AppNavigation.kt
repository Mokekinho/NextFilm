package com.example.nextfilm.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.nextfilm.ui.elements.Home
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
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
            Home(modifier)
        }
        composable<MovieNav> {
            //Add the movie composable
        }
    }
}


@Serializable
object HomeNav // Is the screen that shows the moovies

@Serializable
data class MovieNav(val id: Int) // Is the screen that show the details of the movie