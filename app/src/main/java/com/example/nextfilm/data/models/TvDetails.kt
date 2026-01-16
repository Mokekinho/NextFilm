package com.example.nextfilm.data.models

import com.example.nextfilm.data.sources.remote.responses.details.Genre
import com.example.nextfilm.data.sources.remote.responses.details.tv.Season

data class TvDetails(
    val name: String,
    val overview: String,
    val posterUrl: String,
    val backdropUrl: String,
    val genres: List<Genre>,
    val homePageUrl: String,
    val firstEpisodeYear: String,

    val numberOfEpisodes: Int,
    val lastEpisodeYear: String?,
    val numberOfSeasons: Int,
    val seasons: List<Season>,

    )