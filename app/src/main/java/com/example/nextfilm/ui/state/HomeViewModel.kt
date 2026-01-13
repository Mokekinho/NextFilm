package com.example.nextfilm.ui.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextfilm.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import com.example.nextfilm.data.models.MoviesListEntry
import com.example.nextfilm.data.sources.remote.TimeWindow
import com.example.nextfilm.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class HomeState(
    val trendingList: List<MoviesListEntry> = emptyList(),
    val timeWindow: TimeWindow = TimeWindow.DAY,
    val error: String? = null,
    val isLoading: Boolean = false,
)

@HiltViewModel // vou injetatar coisas aqui
class HomeViewModel @Inject constructor(
  private val repository: MovieRepository
) : ViewModel() {
    private val logTag = "HomeViewModel"

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadMoviePaginated()// carrega as primeiras
    }

    fun loadMoviePaginated() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                )
            }
            val result = repository.getTrendingMovieList(
                timeWindow = _state.value.timeWindow,
                page = 1,
            )

            when (result) {

                is Resource.Success -> {

                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            trendingList = it.trendingList + result.data!!.results.map { res ->

                                Log.d(logTag, "Mapeando o filme =${res.title}")
                                MoviesListEntry(
                                    res.title ?: "",
                                    res.posterPath ?: "",
                                    res.id
                                )
                            }
                        )
                    }


                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

                is Resource.Loading -> {// nao faz nada por que sim, nao vai ser util }
                }

            }

        }

    }
}