package com.example.nextfilm.data.sources.remote.responses.details.movie

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    val credits: Credits,
    val external_ids: ExternalIds,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val recommendations: Recommendations,
    val release_date: String,
    val release_dates: ReleaseDates,
    val revenue: Int,
    val reviews: Reviews,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val videos: Videos,
    val vote_average: Double,
    val vote_count: Int,
    @SerializedName("watch/providers")val watchOrProviders: Watchproviders
)