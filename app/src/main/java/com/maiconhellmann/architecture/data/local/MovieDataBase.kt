package com.maiconhellmann.architecture.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
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