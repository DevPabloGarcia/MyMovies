package com.pablogarcia.my_movies.data.repository.local

import com.pablogarcia.my_movies.model.Movie

interface LocalRepository {

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    suspend fun getMovies(): List<Movie>

}
