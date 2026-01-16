package com.example.nextfilm.ui.state

import androidx.lifecycle.ViewModel
import com.example.nextfilm.data.repository.NextFilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject




@HiltViewModel
class MediaEntryViewModel @Inject constructor(
    private val repository: NextFilmRepository
): ViewModel() {

}