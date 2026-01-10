package com.example.nextfilm.ui.state

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nextfilm.data.models.MoviesListEntry
import com.example.nextfilm.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject




@HiltViewModel
class MovieEntryViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

}