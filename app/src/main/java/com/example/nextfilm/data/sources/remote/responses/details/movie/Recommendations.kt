package com.example.nextfilm.data.sources.remote.responses.details.movie

data class Recommendations(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)