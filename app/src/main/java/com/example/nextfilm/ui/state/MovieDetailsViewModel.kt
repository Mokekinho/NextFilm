package com.example.nextfilm.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextfilm.data.models.MovieDetails
import com.example.nextfilm.data.repository.NextFilmRepository
import com.example.nextfilm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

data class MovieDetailsState(
    val movieDetails: MovieDetails? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: NextFilmRepository
): ViewModel(){

    private val _state = MutableStateFlow(MovieDetailsState())
    val state: StateFlow<MovieDetailsState> = _state.asStateFlow()


    fun loadDetails(
        id: Int
    ){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            when(val result = repository.getMovieDetails(id)){
                is Resource.Success<*> ->{
                    val data = result. data
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            movieDetails = MovieDetails(
                                name = data!!.title,
                                overview = data.overview,
                                posterUrl = data.poster_path,
                                backdropUrl = data.backdrop_path,
                                genres = data.genres,
                                homePageUrl = data.homepage,
                                releaseYear = LocalDate.parse(data.release_date).year.toString(),
                                runtime = (data.runtime/60).toString() + "h " + (data.runtime%60).toString() + "m"
                            )
                        )
                    }
                }
                is Resource.Error<*> -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading<*> -> {
                    // do nothing
                }
            }
        }
    }
}