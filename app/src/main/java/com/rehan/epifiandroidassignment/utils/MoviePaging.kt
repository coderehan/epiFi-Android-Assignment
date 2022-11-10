package com.rehan.epifiandroidassignment.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rehan.epifiandroidassignment.api.MovieAPI
import com.rehan.epifiandroidassignment.models.Movie


class MoviePaging(private val search: String, private val movieAPI: MovieAPI) : PagingSource<Int, Movie>() {        // Inorder to make movie request api call, we have to add movie api interface to this class

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            // If anchorPage is having prevKey, we will add 1 otherwise if anchorPage is having nextKey, we will do minus 1
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key?: 1      // If the page key is null, we will return 1 because by default page number starts with 1

        return try {
            val data = movieAPI.getAllMovies(search, page, Constants.MY_API_KEY)

            // Here we have to pass 3 parameters (1) data (2) prevKey (3) nextKey
            // (1) data represents successful loaded data
            // (2) PrevKey is for previous page if more data can be loaded in that direction, null otherwise.
            // (3) NextKey is for next page if more data can be loaded in that direction, null otherwise.
            LoadResult.Page(
                data = data.body()?.search!!,       // This is our data which contains List<Movie>
                prevKey = if(page == 1) null else page-1,
                nextKey = if(data.body()?.search?.isEmpty()!!) null else page+1
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}