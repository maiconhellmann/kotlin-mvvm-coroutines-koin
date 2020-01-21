package com.maiconhellmann.architecture.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maiconhellmann.architecture.data.model.Movie

@Database(entities = arrayOf(Movie::class), version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        private var INSTANCE: MovieDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): MovieDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MovieDatabase::class.java, "movie.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }

}