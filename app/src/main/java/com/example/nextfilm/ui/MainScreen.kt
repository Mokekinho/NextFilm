package com.example.nextfilm.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                .padding(horizontal = 10.dp)
            ,

            ) {
            AppNavigation(
                modifier = Modifier
                    .padding(it)
            )
        }
    }
}
