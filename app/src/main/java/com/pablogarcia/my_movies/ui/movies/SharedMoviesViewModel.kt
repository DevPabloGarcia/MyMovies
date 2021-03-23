package com.pablogarcia.my_movies.ui.movies

import androidx.lifecycle.*
import com.pablogarcia.my_movies.model.Movie
import com.pablogarcia.my_movies.data.repository.local.LocalRepository
import com.pablogarcia.my_movies.usecase.DeleteLocalMovieUseCase
import com.pablogarcia.my_movies.usecase.GetLocalMoviesUseCase
import com.pablogarcia.my_movies.usecase.InsertLocalMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SharedMoviesViewModel(
    private val getLocalMoviesUseCase: GetLocalMoviesUseCase,
    private val insertLocalMovieUseCase: InsertLocalMovieUseCase,
    private val deleteLocalMovieUseCase: DeleteLocalMovieUseCase
): ViewModel() {

    private val liveLikedMovies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>().also {
            loadLocalMovies()
        }
    }

    private val liveMovies: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>().also {
            loadMovies()
        }
    }

    //region PUBLIC_METHODS

    /**
     * Update local repository with movie liked value
     * - if movie is liked, insert it
     * - remove from local database in other case
     */
    fun updateLikedMovie(movie: Movie) {
        if (movie.liked) {
            addLikedMovie(movie)
        } else {
            removeLikedMovie(movie)
        }
    }

    fun getMovies(): LiveData<List<Movie>> = liveMovies

    fun getLikedMovies(): LiveData<List<Movie>> = liveLikedMovies

    //endregion

    //region PRIVATE_METHODS

    /**
     * Add liked movie to local repository
     *
     * @param movie - movie to add
     */
    private fun addLikedMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        insertLocalMovieUseCase.post(movie)
        loadLocalMovies()
    }

    /**
     * Remove liked movie from local repository
     *
     * @param movie - movie to remove
     */
    private fun removeLikedMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        deleteLocalMovieUseCase.delete(movie)
        loadLocalMovies()
    }

    /**
     * Load movies from cloud repository
     */
    private fun loadMovies() = viewModelScope.launch {

        delay(1500)
        val tempMovies = mutableListOf<Movie>()
        for (index: Int in 0..10) {
            tempMovies.add(Movie(index, false))
        }
        liveMovies.postValue(tempMovies)
    }

    /**
     * Load liked movies from local repository
     */
    private fun loadLocalMovies() = viewModelScope.launch(Dispatchers.IO) {
        val movies = getLocalMoviesUseCase.get()
        liveLikedMovies.postValue(movies)
    }

    //endregion
}
