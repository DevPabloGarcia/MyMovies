package com.pablogarcia.my_movies.di.modules

import com.pablogarcia.my_movies.usecase.*
import org.koin.dsl.module

object UseCasesModule {

    val module = module {

        single<InsertLocalMovieUseCase> { InsertLocalMovieUseCaseImpl(get()) }
        single<DeleteLocalMovieUseCase> { DeleteLocalMovieUseCaseImpl(get()) }
        single<GetLocalMoviesUseCase> { GetLocalMoviesUseCaseImpl(get()) }
    }
}
