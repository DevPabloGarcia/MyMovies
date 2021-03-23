package com.pablogarcia.my_movies.di.modules

import android.app.Application
import com.pablogarcia.my_movies.data.repository.local.LocalRepository
import com.pablogarcia.my_movies.framework.room.MovieLikedDao
import com.pablogarcia.my_movies.framework.room.MovieLikedDatabase
import com.pablogarcia.my_movies.framework.room.RoomRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object LocalRepositoryModule {

    val module = module {

        fun provideDataBase(application: Application): MovieLikedDatabase {
            return MovieLikedDatabase.getInstance(application)
        }

        fun provideMovieLikedDao(database: MovieLikedDatabase) : MovieLikedDao {
            return database.movieLikedDao()
        }

        fun provideLocalRepository(movieLikedDao: MovieLikedDao): LocalRepository {
            return RoomRepositoryImpl(movieLikedDao)
        }

        single { provideDataBase(androidApplication()) }
        single { provideMovieLikedDao(get()) }
        single { provideLocalRepository(get())}
    }
}
