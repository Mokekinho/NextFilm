package com.example.nextfilm.ui.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun Tv(
    navController: NavController,
    modifier: Modifier = Modifier,
    tvNav: TvViewModel = hiltViewModel()
) {

}