package com.pablogarcia.my_movies.ui.movies.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pablogarcia.my_movies.databinding.FragmentMoviesBinding
import com.pablogarcia.my_movies.ui.base.BaseFragment
import com.pablogarcia.my_movies.ui.movies.MoviesAdapter
import com.pablogarcia.my_movies.ui.movies.SharedMoviesViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MoviesListFragment : BaseFragment<FragmentMoviesBinding>() {

    private val sharedMoviesViewModel: SharedMoviesViewModel by sharedViewModel()
    private lateinit var adapter: MoviesAdapter

    //region OVERRIDE_METHODS

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMoviesBinding {
        return FragmentMoviesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObserver()
        setupRecyclerView()
    }

    //endregion

    //region PRIVATE_METHODS

    /**
     * Setup recycler view
     * - Creates and set movies adapter
     * - Set linear layout manager
     */
    private fun setupRecyclerView() {
        adapter = MoviesAdapter(sharedMoviesViewModel::updateLikedMovie)
        binding.moviesList.layoutManager = LinearLayoutManager(context)
        binding.moviesList.adapter = adapter
    }

    /**
     * Add observers to liked movies list and movies list
     */
    private fun addObserver() {
        sharedMoviesViewModel.getMovies().observe(viewLifecycleOwner) { movies ->
            adapter.updateMovies(movies)
        }
        sharedMoviesViewModel.getLikedMovies().observe(viewLifecycleOwner) { movies ->
            adapter.updateLikedMovies(movies)
        }
    }

    //endregion
}
