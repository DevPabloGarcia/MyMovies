package com.pablogarcia.my_movies.usecase

import com.pablogarcia.my_movies.data.repository.local.LocalRepository
import com.pablogarcia.my_movies.model.Movie

interface GetLocalMoviesUseCase {

    suspend fun get(): List<Movie>
}

class GetLocalMoviesUseCaseImpl(private var localRepository: LocalRepository
) : GetLocalMoviesUseCase {

    override suspend fun get(): List<Movie> {
        return localRepository.getMovies()
    }
}
