package com.example.nextfilm.data.sources.remote.responses.details.movie

data class ReleaseDate(
    val certification: String,
    val descriptors: List<String>,
    val iso_639_1: String,
    val note: String,
    val release_date: String,
    val type: Int
)