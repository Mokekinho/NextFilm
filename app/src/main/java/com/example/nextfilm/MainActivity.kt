package com.example.nextfilm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nextfilm.navigation.AppNavigation
import com.example.nextfilm.ui.MainScreen
import com.example.nextfilm.ui.theme.NextFilmTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint // permite que a classe anotada por @HiltAndroidApp de dependenias pra essa activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NextFilmTheme {
                MainScreen()
            }
        }
    }
}

