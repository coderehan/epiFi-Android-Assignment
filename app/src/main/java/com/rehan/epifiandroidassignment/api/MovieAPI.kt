package com.rehan.epifiandroidassignment.api

import com.rehan.epifiandroidassignment.models.MovieDetails
import com.rehan.epifiandroidassignment.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("/")
    suspend fun getAllMovies(
        @Query("s") search: String,
        @Query("page") page: Int,
        @Query("apikey") apikey: String
    ): Response<MovieResponse>

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apikey") apikey: String
    ): Response<MovieDetails>
}