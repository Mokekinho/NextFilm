package com.example.nextfilm.data.models

import androidx.annotation.DrawableRes
import com.example.nextfilm.data.sources.remote.responses.details.movie.Genre
import com.example.nextfilm.data.sources.remote.responses.details.movie.Reviews
import com.example.nextfilm.data.sources.remote.responses.details.movie.Cast
import com.example.nextfilm.data.sources.remote.responses.details.movie.Crew

data class MovieDetails(
    val name: String,
    val overview: String,
    val posterUrl: String,
    val backdropUrl: String,
    val genres: List<Genre>,
    val homePageUrl: String,
    val releaseYear: String,
    val runtime: String,
    val tagline: String,

    //Rating

    val voteAverage: Float,
    val voteCount: Int,
    val popularity:  Float,
    val reviews: Reviews, // aqui pelo o que eu entendi é como se fossem comentários, não vou usar por agora mas futuramente é legal ter

    // Cast/People
    val cast: List<Cast>, // vem de credits cast
    val crew: List<Crew>, // ta ligado a que fez, credits crew

    // Media
    val trailerUrl: String, // estudar como que funciona, parece que tem varios tipos e é na parte de Teasers,

    //Idicação de idade,
    @param:DrawableRes val ageCertification: Int,

    // Whatch/Providers
    val whereToWatch: String,



    )