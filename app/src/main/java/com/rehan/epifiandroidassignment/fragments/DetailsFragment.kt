package com.rehan.epifiandroidassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rehan.epifiandroidassignment.R
import com.rehan.epifiandroidassignment.databinding.FragmentDetailsBinding
import com.rehan.epifiandroidassignment.databinding.FragmentMovieBinding
import com.rehan.epifiandroidassignment.utils.Status
import com.rehan.epifiandroidassignment.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModels()

    val args : DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()      // going back to home page on back press
        }

        movieViewModel.getMovieDetails(args.imdbId!!)

        movieViewModel.movieDetails.observe(viewLifecycleOwner){
            when(it.getContentIfNotHandled()?.status){
                Status.LOADING ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR ->{
                    binding.progressBar.visibility = View.GONE
                }
                Status.SUCCESS ->{
                    binding.progressBar.visibility = View.GONE
                    binding.movieDetails = it.peekContent().data        // Here we will set our data in movieDetails model class imported in xml through data binding
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}