package com.rehan.epifiandroidassignment.repositories

import com.rehan.epifiandroidassignment.api.MovieAPI
import com.rehan.epifiandroidassignment.models.MovieDetails
import com.rehan.epifiandroidassignment.utils.Constants
import com.rehan.epifiandroidassignment.utils.NetworkResult
import com.rehan.epifiandroidassignment.utils.Status
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor (private val movieAPI: MovieAPI) {

    suspend fun getMovieDetails(imdbId: String): NetworkResult<MovieDetails>{

        return try {
            val response = movieAPI.getMovieDetails(imdbId, Constants.MY_API_KEY)
            if(response.isSuccessful){
                NetworkResult(Status.SUCCESS, response.body(), null)
            }else{
                NetworkResult(Status.ERROR, null, null)
            }
        }catch (e: Exception){
            NetworkResult(Status.ERROR, null, null)
        }
    }
}