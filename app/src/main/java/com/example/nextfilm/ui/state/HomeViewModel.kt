package com.example.nextfilm.ui.state

import androidx.lifecycle.ViewModel
import com.example.nextfilm.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel // vou injetatar coisas aqui
class HomeViewModel @Inject constructor(
  private val repository: MovieRepository
) : ViewModel() {

}