package com.example.nextfilm.data.sources.remote.responses

import com.google.gson.annotations.SerializedName

data class TrendingMovies(
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages") val totalPages: Int, // pra ele saber o nome certinho que vem da API
    @SerializedName("total_results") val totalResults: Int
)