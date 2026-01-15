package com.example.nextfilm.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nextfilm.navigation.AppNavigation
import com.example.nextfilm.navigation.HomeNav
import com.example.nextfilm.navigation.MovieNav
import com.example.nextfilm.navigation.TvNav

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
            ,
            topBar = {
                val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route
                if(
                    currentScreen in listOf(
                        HomeNav::class.qualifiedName,
                        TvNav::class.qualifiedName,
                        MovieNav::class.qualifiedName
                    )
                ){

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    listOf(
                                        MaterialTheme.colorScheme.background,
                                        Color.Transparent
                                    )
                                )
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .windowInsetsPadding(WindowInsets.statusBars) //considera o padding da camera

                            ,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "Home",
                                textDecoration = if(currentScreen == HomeNav::class.qualifiedName) TextDecoration.Underline else null
                                    ,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier
                                    .clickable(
                                        //TODO não entendi muio bem pq o pop up tem que ir pro startDestination, so sei que funciona
                                        onClick = {
                                            navController.navigate(HomeNav){
                                                popUpTo(navController.graph.startDestinationId){
                                                    //inclusive = false
                                                    saveState = true // salva o estado
                                                }
                                                launchSingleTop = true // so impede de criar outro se estiver no topo, por isso é importante fazer o popUpTo
                                                restoreState = true //restaura o estado
                                            }
                                        }
                                    )
                            )
                            Text(
                                text = "Tv",
                                textDecoration = if(currentScreen == TvNav::class.qualifiedName) TextDecoration.Underline else null
                                ,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier
                                    .clickable(
                                        onClick = {
                                            navController.navigate(TvNav){
                                                popUpTo(navController.graph.startDestinationId){
                                                    //inclusive = false
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                            )
                            Text(
                                text = "Movies",
                                textDecoration = if(currentScreen == MovieNav::class.qualifiedName) TextDecoration.Underline else null
                                ,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier
                                    .clickable(
                                        onClick = {
                                            navController.navigate(MovieNav){
                                                popUpTo(navController.graph.startDestinationId){
                                                    //inclusive = false
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }

                                        }
                                    )
                            )
                        }
                    }

                }
            }

            ) {
            it // vou escrever isso aqui pro nao ter que usar o padding

            AppNavigation(
                navController = navController,
                modifier = Modifier
                    //.padding(it)
                    .padding(horizontal = 10.dp)
            //por padrao ele ja tem padding pra nao ficar em baixo da camera
            )
        }
    }
}
