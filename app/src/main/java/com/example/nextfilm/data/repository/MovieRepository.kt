package com.example.nextfilm.data.repository

import com.example.nextfilm.data.sources.remote.MovieApi
import com.example.nextfilm.data.sources.remote.TimeWindow
import com.example.nextfilm.data.sources.remote.responses.details.movie.MovieDetailsResponse
import com.example.nextfilm.data.sources.remote.responses.details.tv.TvDetailsResponse
import com.example.nextfilm.data.sources.remote.responses.entry.TrendingMovies
import com.example.nextfilm.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped // diz ao Hilt que esse repositorio aqui vai viver enquanto a Activity viver
class MovieRepository @Inject constructor(
    private val api : MovieApi
) {
    suspend fun getTrendingMediaList(
        timeWindow: TimeWindow,
        mediaType: String,
        page: Int,
        language: String = "en-US",
    ): Resource<TrendingMovies> {


        val response = try {
            api.getTrendingMovieList(
                mediaType = mediaType,
                timeWindow = timeWindow.name.lowercase(),
                page = page,
                language = language,
            )
        }
        catch (e: Exception){
            return Resource.Error(
                data = null,
                message = "An Error Occurred:" + e.message.toString()
            )
        }

        return Resource.Success(
            data = response
        )
    }
    suspend fun getPopularMovieList(
        page: Int,
        language: String = "en-US",
    ): Resource<TrendingMovies> {


        val response = try {
            api.getPopularMovies(
                page = page,
                language = language,
            )
        }
        catch (e: Exception){
            return Resource.Error(
                data = null,
                message = "An Error Occurred:" + e.message.toString()
            )
        }

        return Resource.Success(
            data = response
        )
    }

    suspend fun getTopRatedMovieList(
        page: Int,
        language: String = "en-US",
    ): Resource<TrendingMovies> {


        val response = try {
            api.getTopRatedMovies(
                page = page,
                language = language,
            )
        }
        catch (e: Exception){
            return Resource.Error(
                data = null,
                message = "An Error Occurred:" + e.message.toString()
            )
        }

        return Resource.Success(
            data = response
        )
    }

    suspend fun getMovieDetails(
        id:  Int,
        language: String = "en-US"
    ) : Resource<MovieDetailsResponse> {

        val response = try{
            api.getMovieDetails(
                id,
                language
            )
        }
        catch (e: Exception){
            return Resource.Error(
                data = null,
                message = "An Error Occurred:" + e.message.toString()
            )
        }

        return Resource.Success(
            data = response
        )

    }

    suspend fun getTvDetails(
        id:  Int,
        language: String = "en-US"
    ) : Resource<TvDetailsResponse> {

        val response = try{
            api.getTvDetails(
                id,
                language
            )
        }
        catch (e: Exception){
            return Resource.Error(
                data = null,
                message = "An Error Occurred:" + e.message.toString()
            )
        }

        return Resource.Success(
            data = response
        )
    }
}