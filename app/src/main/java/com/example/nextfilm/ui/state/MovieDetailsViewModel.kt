package com.example.nextfilm.ui.state

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextfilm.R
import com.example.nextfilm.data.models.MovieDetails
import com.example.nextfilm.data.repository.NextFilmRepository
import com.example.nextfilm.data.sources.remote.responses.details.movie.ResultX
import com.example.nextfilm.data.sources.remote.responses.details.movie.ResultXXX
import com.example.nextfilm.data.sources.remote.responses.details.movie.Videos
import com.example.nextfilm.util.Constants.YOUTUBE_BASE_URL
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
                                runtime = (data.runtime/60).toString() + "h " + (data.runtime%60).toString() + "m",
                                tagline = data.tagline,
                                voteAverage = data.vote_average.toFloat(),
                                voteCount = data.vote_count,
                                popularity = data.popularity.toFloat(),
                                reviews = data.reviews,
                                cast = data.credits.cast,
                                crew = data.credits.crew,
                                trailerUrl = makeTrailerUrl(
                                    data.videos.results
                                ),
                                ageCertification = makeAgeCertification(
                                    data.release_dates.results
                                ),
                                whereToWatch = makeWhereToWatch()

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

    private fun makeTrailerUrl(
        videos: List<ResultXXX>
    ): String{

        val trailer = videos.firstOrNull(){it.key.isNotEmpty()}?.key

        return (YOUTUBE_BASE_URL + trailer)
    }

    @DrawableRes
    private fun makeAgeCertification(
        results: List<ResultX>
    ): Int{

        val usResult = results.find { it.iso_3166_1 == "US" } // find ele acha o correto

        val certification = usResult?.release_dates
            ?.firstOrNull { it.certification.isNotEmpty() }
            ?.certification // vou pegar a certificação, se tiver mais de uma certificação ele vai pegar a primeira que nao for nula e tiver valor

        return when (certification) {
            "G"     -> R.drawable.mpa_g_rating
            "PG"    -> R.drawable.mpa_pg_rating
            "PG-13" -> R.drawable.mpa_pg_13_rating
            "R"     -> R.drawable.mpa_r_rating
            "NC-17" -> R.drawable.mpa_nc_17_rating
            else    -> R.drawable.ic_launcher_foreground // Ou um ícone de "Não avaliado"
        }
    }

    private fun makeWhereToWatch(

    ): String{
        return ""
    }


}