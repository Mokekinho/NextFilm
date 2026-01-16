package com.example.nextfilm.ui.state


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextfilm.data.repository.NextFilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import com.example.nextfilm.data.models.MediaListEntry
import com.example.nextfilm.data.sources.remote.TimeWindow
import com.example.nextfilm.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class MovieState(
    val trendingMovieList: List<MediaListEntry> = emptyList(),
    val popularMovieList: List<MediaListEntry> = emptyList(),
    val topRatedMovieList: List<MediaListEntry> = emptyList(),
    val timeWindow: TimeWindow = TimeWindow.DAY,
    val error: String? = null,
    val isLoading: Boolean = false,
    val isLoaded: Boolean = false,
)

@HiltViewModel // vou injetatar coisas aqui
class MovieViewModel @Inject constructor(
    private val repository: NextFilmRepository
) : ViewModel() {
    private val logTag = "MovieViewModel"
    private val defaultErrorMessage = "Error on Load Data"

    private val _state = MutableStateFlow(MovieState())
    val state: StateFlow<MovieState> = _state.asStateFlow()

    init {
        loadData()
    }

    private suspend fun loadTrendingMovieList() {

        val result = repository.getTrendingMediaList(
            mediaType = "movie",
            timeWindow = _state.value.timeWindow,
            page = 1,
        )

        when (result) {

            is Resource.Success -> {

                _state.update {
                    it.copy(
                        trendingMovieList = it.trendingMovieList + result.data!!.results.map { res ->
                            MediaListEntry(
                                res.title ?: "",
                                res.posterPath ?: "",
                                res.id,
                                mediaType = res.mediaType?: ""
                            )
                        }
                    )
                }


            }

            is Resource.Error -> {
                throw Exception(result.message ?: defaultErrorMessage)
            }

            is Resource.Loading -> Unit

        }



    }
    private suspend fun loadPopularMovieList() {
        val result = repository.getPopularMovieList(
            page = 1,
        )

        when (result) {

            is Resource.Success -> {

                _state.update {
                    it.copy(
                        popularMovieList = it.popularMovieList + result.data!!.results.map { res ->

                            Log.d(logTag, "Mapeando o filme =${res.title}")
                            MediaListEntry(
                                res.title ?: "",
                                res.posterPath ?: "",
                                res.id,
                                mediaType = "movie"
                            )
                        }
                    )
                }


            }

            is Resource.Error -> {
                throw Exception(result.message ?: defaultErrorMessage) // to lançando uma excessão
            }

            is Resource.Loading -> Unit

        }
    }
    private suspend fun loadTopRatedMovieList() {

        val result = repository.getTopRatedMovieList(
            page = 1,
        )

        when (result) {

            is Resource.Success -> {

                _state.update {
                    it.copy(
                        topRatedMovieList = it.topRatedMovieList + result.data!!.results.map { res ->

                            Log.d(logTag, "Mapeando o filme =${res.title}")
                            MediaListEntry(
                                movieName = res.title ?: "",
                                imageUrl = res.posterPath ?: "",
                                id = res.id,
                                mediaType = "movie"
                            )
                        }
                    )
                }
            }

            is Resource.Error -> {
                throw Exception(result.message?: defaultErrorMessage)
            }

            is Resource.Loading -> Unit

        }



    }

    fun loadData(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    isLoaded = false,
                    error = null,
                )
            }
            try{
                loadTrendingMovieList()
                loadPopularMovieList()
                loadTopRatedMovieList()

                _state.update {
                    it.copy(
                        isLoading = false,
                        isLoaded = true,
                        error = null,
                    )
                }

            }
            catch (e: Exception){
                _state.update {
                    it.copy(
                        error = e.message,
                        isLoading = false,
                        isLoaded = false
                    )
                }
            }
        }
    }
}
