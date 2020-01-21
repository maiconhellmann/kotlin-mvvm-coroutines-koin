package com.maiconhellmann.architecture.data.local

import androidx.room.*
import com.maiconhellmann.architecture.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * from movie")
    fun getMovieList(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Update
    fun update(movie: Movie): Int


    @Query("DELETE FROM movie")
    fun deleteAll()
}