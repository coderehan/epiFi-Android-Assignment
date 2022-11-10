package com.rehan.epifiandroidassignment.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val search: List<Movie>,
    @SerializedName("totalResults")
    val totalResults: String
)
