package com.example.nextfilm.data.models

import com.example.nextfilm.data.sources.remote.responses.details.Genre

data class MovieDetails(
    val name: String,
    val overview: String,
    val posterUrl: String,
    val backdropUrl: String,
    val genres: List<Genre>,
    val homePageUrl: String,

)