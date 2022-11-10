package com.rehan.epifiandroidassignment.di

import com.rehan.epifiandroidassignment.api.MovieAPI
import com.rehan.epifiandroidassignment.repositories.MovieDetailsRepository
import com.rehan.epifiandroidassignment.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesMovieAPI(retrofit: Retrofit): MovieAPI {
        return retrofit.create(MovieAPI::class.java)
    }

    @Provides
    fun providesRepository(movieAPI: MovieAPI): MovieDetailsRepository{
        return MovieDetailsRepository(movieAPI)
    }
}