package com.example.nextfilm.data.sources.remote.responses.details.movie

data class Reviews(
    val page: Int,
    val results: List<ResultXX>,
    val total_pages: Int,
    val total_results: Int
)