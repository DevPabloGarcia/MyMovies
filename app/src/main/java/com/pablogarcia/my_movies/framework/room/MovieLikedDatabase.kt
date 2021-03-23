package com.pablogarcia.my_movies.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pablogarcia.my_movies.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieLikedDatabase : RoomDatabase() {

    abstract fun movieLikedDao(): MovieLikedDao

    companion object {

        private const val DATABASE_NAME = "movie_liked_database"
        const val TABLE_NAME = "movies"

        @Volatile
        private var INSTANCE: MovieLikedDatabase? = null

        fun getInstance(context: Context) : MovieLikedDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieLikedDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
