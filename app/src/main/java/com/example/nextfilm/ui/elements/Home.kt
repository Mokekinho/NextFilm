package com.example.nextfilm.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp

@Composable
fun Home(
    modifier: Modifier = Modifier
) {

    val trendingMovies = listOf(
        "Shrek",
        "Tangled",
        "The Super Mario Bros. Movie",
        "Spider-Man: Across the Spider-Verse",
        "Oppenheimer",
        "Barbie",
        "Dune: Part Two",
        "Woody Woodpecker Goes to Camp",
        "Kung Fu Panda 4",
        "Inside Out 2",
        "The Garfield Movie",
        "Kingdom of the Planet of the Apes",
        "Top Gun: Maverick",
        "Avatar: The Way of Water",
        "Puss in Boots: The Last Wish",
        "Wonka",
        "Fast X",
        "The Batman",
        "Elemental",
        "Minions: The Rise of Gru"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        Text(
            text = "Trending",
            style = MaterialTheme.typography.displaySmall
        )

        LazyRow() {
            items(trendingMovies){
                Card(
                    modifier = Modifier
                        .height(200.dp)
                        .width(140.dp)
                    ,

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                        ,
                        contentAlignment = Alignment.Center
                    ){
                        Text(it)
                    }

                }
                Spacer(
                    modifier = Modifier
                        .size(5.dp)
                )
            }
        }
    }
}
