package com.pablogarcia.my_movies.ui.movies.liked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pablogarcia.my_movies.databinding.FragmentLikedMoviesBinding
import com.pablogarcia.my_movies.model.Movie
import com.pablogarcia.my_movies.ui.base.BaseFragment
import com.pablogarcia.my_movies.ui.movies.MoviesAdapter
import com.pablogarcia.my_movies.ui.movies.SharedMoviesViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LikesMoviesFragment: BaseFragment<FragmentLikedMoviesBinding>() {

    private val sharedMoviesViewModel: SharedMoviesViewModel by sharedViewModel()
    private lateinit var adapter: MoviesAdapter

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLikedMoviesBinding {
        return FragmentLikedMoviesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserver()
        setupRecyclerView()
    }

    private fun addObserver(){
        sharedMoviesViewModel.getLikedMovies().observe(viewLifecycleOwner, ::updateUi)
    }

    private fun setupRecyclerView() {
        adapter = MoviesAdapter()
        binding.likedMoviesList.layoutManager = LinearLayoutManager(context)
        binding.likedMoviesList.adapter = adapter
    }


    private fun updateUi(likedMovies: List<Movie>) {
        adapter.updateMovies(likedMovies)
    }
}
