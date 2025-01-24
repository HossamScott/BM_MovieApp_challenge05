package com.hossam.bm_movieapp.data.model

data class MovieDetailsResponse(
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val runtime: Int,
    val genres: List<Genre>,
    val vote_average: Double,
    val tagline: String,
    val poster_path: String?,
    val backdrop_path: String?
)

data class Genre(val id: Int, val name: String)