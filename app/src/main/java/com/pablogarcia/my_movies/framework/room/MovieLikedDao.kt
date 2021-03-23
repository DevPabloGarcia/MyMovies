package com.pablogarcia.my_movies.framework.room

import androidx.room.*
import com.pablogarcia.my_movies.framework.room.MovieLikedDatabase.Companion.TABLE_NAME
import com.pablogarcia.my_movies.model.Movie

@Dao
interface MovieLikedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getLikedMovies(): List<Movie>

}
