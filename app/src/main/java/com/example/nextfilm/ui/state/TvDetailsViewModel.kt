package com.example.nextfilm.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextfilm.data.models.TvDetails
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

data class TvDetailsState(
    val tvDetails: TvDetails? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)

@HiltViewModel
class TvDetailsViewModel @Inject constructor(
    private val repository: NextFilmRepository
): ViewModel(){

    private val _state = MutableStateFlow(TvDetailsState())
    val state: StateFlow<TvDetailsState> = _state.asStateFlow()


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

            when(val result = repository.getTvDetails(id)){
                is Resource.Success<*> ->{
                    val data = result. data
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            tvDetails = TvDetails(
                                name = data!!.name,
                                overview = data.overview,
                                posterUrl = data.poster_path,
                                backdropUrl = data.backdrop_path,
                                genres = data.genres,
                                homePageUrl = data.homepage,
                                firstEpisodeYear = LocalDate.parse(data.first_air_date).year.toString(),
                                numberOfSeasons = data.number_of_seasons,
                                numberOfEpisodes = data.number_of_episodes,
                                lastEpisodeYear = null, //Todo pensar em uma forma pra caso seja null os argumentos
                                seasons = data.seasons
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