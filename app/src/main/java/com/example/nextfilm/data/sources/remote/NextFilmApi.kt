package com.example.nextfilm.data.sources.remote


import com.example.nextfilm.BuildConfig
import com.example.nextfilm.data.sources.remote.responses.details.movie.MovieDetailsResponse
import com.example.nextfilm.data.sources.remote.responses.details.tv.TvDetailsResponse
import com.example.nextfilm.data.sources.remote.responses.entry.Media
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NextFilmApi {

    //aqui a gente passa apenas o caminho mediano, a gente vai definir um caminho completo mais rpa frente, a saber, https://api.themoviedb.org/3/
    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMovieList(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    // aqui a gente tem que ficar de olho nos Query paramethers da API, no caso da que eu to usando ele sempre retorna 20 filmes por pagina, o que da pra mexer aqui é na linguagem
    ): Media

    @GET("movie/popular") // tem o Tv popular também
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): Media

    @GET("tv/popular") // tem o Tv popular também
    suspend fun getPopularTv(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): Media

    @GET("movie/top_rated") // tem o Tv popular também
    suspend fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): Media

    @GET("tv/top_rated") // tem o Tv popular também
    suspend fun getTopRatedTv(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): Media


    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("append_to_response") appendToResponse: String =  "credits,videos,release_dates,recommendations,reviews,watch/providers,external_ids",
        @Query("language") language: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): MovieDetailsResponse

    @GET("tv/{id}")
    suspend fun getTvDetails(
        @Path("id") id: Int,
        @Query("append_to_response") appendToResponse: String = "credits,videos,content_ratings,recommendations,watch/providers,external_ids",
        @Query("language") language: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
    ): TvDetailsResponse


}

enum class TimeWindow{
    DAY,
    WEEK
}


