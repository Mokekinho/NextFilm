package com.example.nextfilm.data.sources.remote.responses

data class TrendingMovies(
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
)