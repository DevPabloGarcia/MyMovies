package com.pablogarcia.my_movies.usecase

import com.pablogarcia.my_movies.data.repository.local.LocalRepository
import com.pablogarcia.my_movies.model.Movie

interface DeleteLocalMovieUseCase {

    suspend fun delete(movie: Movie)
}

class DeleteLocalMovieUseCaseImpl(private var localRepository: LocalRepository
): DeleteLocalMovieUseCase {

    override suspend fun delete(movie: Movie) {
        localRepository.deleteMovie(movie)
    }
}
