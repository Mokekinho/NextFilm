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
import androidx.compose.ui.unit.dp
import com.example.nextfilm.navigation.AppNavigation

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
            ,
            topBar = {
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
                ){
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
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .clickable(
                                    onClick = {

                                    }
                                )
                        )
                        Text(
                            text = "Series",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .clickable(
                                    onClick = {

                                    }
                                )
                        )
                        Text(
                            text = "Movies",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .clickable(
                                    onClick = {

                                    }
                                )
                        )


                    }
                }

            }

            ) {
            it // vou escrever isso aqui pro nao ter que usar o padding

            AppNavigation(
                modifier = Modifier
                    //.padding(it)
                    .padding(horizontal = 10.dp)
            //por padrao ele ja tem padding pra nao ficar em baixo da camera
            )
        }
    }
}
