package com.rehan.epifiandroidassignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.rehan.epifiandroidassignment.api.MovieAPI
import com.rehan.epifiandroidassignment.models.MovieDetails
import com.rehan.epifiandroidassignment.repositories.MovieDetailsRepository
import com.rehan.epifiandroidassignment.utils.Events
import com.rehan.epifiandroidassignment.utils.MoviePaging
import com.rehan.epifiandroidassignment.utils.NetworkResult
import com.rehan.epifiandroidassignment.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieAPI: MovieAPI, private val movieDetailsRepository: MovieDetailsRepository) : ViewModel(){

    private val query = MutableLiveData<String>()

    // Switch map is used to switch the values within livedata
    val list = query.switchMap {
        Pager(PagingConfig(pageSize = 10)){
            MoviePaging(it, movieAPI)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s: String){
        query.postValue(s)
    }

    private val _movieDetails = MutableLiveData<Events<NetworkResult<MovieDetails>>>()
    val movieDetails : LiveData<Events<NetworkResult<MovieDetails>>> = _movieDetails

    fun getMovieDetails(imdbId: String) = viewModelScope.launch {
        _movieDetails.postValue(Events(NetworkResult(Status.LOADING, null, null)))
        _movieDetails.postValue(Events(movieDetailsRepository.getMovieDetails(imdbId)))
    }
}