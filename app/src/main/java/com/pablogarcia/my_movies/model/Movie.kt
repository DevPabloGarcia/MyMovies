package com.pablogarcia.my_movies.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movies")
data class Movie(
    @ColumnInfo(name = "id")
    @NotNull
    @PrimaryKey(autoGenerate = false) var id: Int,
    @ColumnInfo(name = "liked") var liked: Boolean = false
)
