package com.example.nextfilm.ui.state

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextfilm.data.models.MoviesListEntry
import com.example.nextfilm.data.repository.MovieRepository
import com.example.nextfilm.data.sources.remote.TimeWindow
import com.example.nextfilm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class MovieListFromTopicState(
    val movieList: List<MoviesListEntry> = emptyList(),
    val timeWindow: TimeWindow = TimeWindow.DAY,
    val error: String? = null,
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
)

@HiltViewModel
class MovieListFromTopicViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    private var currentPage = 1

    private val logTag = "MovieListFromTopicViewModel"

    private val _state = MutableStateFlow(MovieListFromTopicState())
    val state: StateFlow<MovieListFromTopicState> = _state.asStateFlow()

    init{
        loadMoviePaginated()// carrega as primeiras
    }
    fun loadMoviePaginated() {
        //if (_state.value.isLoading || _state.value.endReached) return // vou retornar pq o compose pode demorar a se recompor. // não vou usar mas manter a logica na viewmodel é melhor
        //TODO Estudar como funcia a recomposição no Compose

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                )
            }
            val result = repository.getTrendingMediaList(
                timeWindow = _state.value.timeWindow,
                page = currentPage,
            )

            when (result) {

                is Resource.Success -> {
                    currentPage++
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            endReached = currentPage >= result.data!!.totalPages,
                            movieList = it.movieList + result.data.results.map{ res ->

                                Log.d(logTag, "Mapeando o filme =${res.title}")
                                MoviesListEntry(
                                    res.title ?: "",
                                    res.posterPath?: "",
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

                is Resource.Loading<*> -> {// nao faz nada por que sim, nao vai ser util }
                }

            }

        }

    }

    fun onItemDisplayed(index: Int) { // ele vai verificar se pode Mostrar um Item, por hora não vou usar pq foi o chatGPT quem fez
        val state = _state.value

        if (
            index >= state.movieList.size - 4 &&
            !state.isLoading &&
            !state.endReached
        ) {
            loadMoviePaginated()
        }
    }
}
