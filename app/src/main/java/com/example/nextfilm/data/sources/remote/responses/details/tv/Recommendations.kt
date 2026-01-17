package com.example.nextfilm.data.sources.remote.responses.details.tv

data class Recommendations(
    val page: Int,
    val results: List<Any>,
    val total_pages: Int,
    val total_results: Int
)