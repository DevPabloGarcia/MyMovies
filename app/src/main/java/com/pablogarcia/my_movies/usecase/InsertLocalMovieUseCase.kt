package com.pablogarcia.my_movies.usecase

import com.pablogarcia.my_movies.data.repository.local.LocalRepository
import com.pablogarcia.my_movies.model.Movie

interface InsertLocalMovieUseCase {

    suspend fun post(movie: Movie)
}

class InsertLocalMovieUseCaseImpl(private var localRepository: LocalRepository
): InsertLocalMovieUseCase {

    override suspend fun post(movie: Movie) {
        localRepository.insertMovie(movie)
    }
}
