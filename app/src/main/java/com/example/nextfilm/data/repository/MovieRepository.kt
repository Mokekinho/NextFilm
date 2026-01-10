package com.example.nextfilm.data.repository

import com.example.nextfilm.data.sources.remote.MovieApi
import com.example.nextfilm.data.sources.remote.TimeWindow
import com.example.nextfilm.data.sources.remote.responses.TrendingMovies
import com.example.nextfilm.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped // diz ao Hilt que esse repositorio aqui vai viver enquanto a Activity viver
class MovieRepository @Inject constructor(
    private val api : MovieApi
) {
    suspend fun getTrendingMovieList(
        timeWindow: TimeWindow,
        page: Int,
        language: String = "en-US",
    ): Resource<TrendingMovies> {


        val response = try {
            api.getTrendingMovieList(
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
}