package com.example.nextfilm.data.sources.remote


import com.example.nextfilm.BuildConfig
import com.example.nextfilm.data.sources.remote.responses.TrendingMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    //aqui a gente passa apenas o caminho mediano, a gente vai definir um caminho completo mais rpa frente, a saber, https://api.themoviedb.org/3/
    @GET("trending/{category}/{time_window}")
    suspend fun getTrendingMovieList(
        @Path("category") category: String = "all",
        @Path("time_window") timeWindow: String,
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    // aqui a gente tem que ficar de olho nos Query paramethers da API, no caso da que eu to usando ele sempre retorna 20 filmes por pagina, o que da pra mexer aqui é na linguagem
    ): TrendingMovies
}

enum class TimeWindow{
    DAY,
    WEEK
}

