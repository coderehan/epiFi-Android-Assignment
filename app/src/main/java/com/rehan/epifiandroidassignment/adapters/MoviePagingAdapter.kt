package com.rehan.epifiandroidassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rehan.epifiandroidassignment.databinding.AdapterMovieItemsBinding
import com.rehan.epifiandroidassignment.models.Movie

class MoviePagingAdapter : PagingDataAdapter<Movie, MoviePagingAdapter.ViewHolder>(DIFF_UTIL) {

    private var onClick : ((String)->Unit)? = null

    companion object{
        // DiffUtil is used update the recyclerview with fresh information
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterMovieItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        holder.binding.setVariable(BR.movie, data)  // Here we will set our data in movie model class which is imported in xml through data binding

        // When clicked on movie items in recyclerview in movie fragment page
        holder.binding.root.setOnClickListener {
            onClick?.let {
                it(data?.imdbID!!)
            }
        }
    }

    fun onMovieClicked(listener:(String)->Unit){
        onClick = listener
    }

    inner class ViewHolder(val binding: AdapterMovieItemsBinding) : RecyclerView.ViewHolder(binding.root)

}