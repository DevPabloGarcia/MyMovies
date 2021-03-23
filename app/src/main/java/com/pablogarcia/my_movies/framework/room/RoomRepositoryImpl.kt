package com.pablogarcia.my_movies.framework.room

import androidx.annotation.WorkerThread
import com.pablogarcia.my_movies.data.repository.local.LocalRepository
import com.pablogarcia.my_movies.model.Movie

class RoomRepositoryImpl(private var movieLikedDao: MovieLikedDao): LocalRepository {

    @WorkerThread
    override suspend fun insertMovie(movie: Movie) {

        movieLikedDao.insert(movie)
    }

    @WorkerThread
    override suspend fun deleteMovie(movie: Movie) {

        movieLikedDao.delete(movie)
    }

    @WorkerThread
    override suspend fun getMovies(): List<Movie> {

        return movieLikedDao.getLikedMovies()
    }
}
