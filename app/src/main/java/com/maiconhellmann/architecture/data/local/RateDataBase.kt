package com.maiconhellmann.architecture.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.maiconhellmann.architecture.data.model.Rate

@Database(entities = arrayOf(Rate::class), version = 1, exportSchema = false)
abstract class RateDatabase : RoomDatabase() {

    abstract fun rateDao(): RatesDao


    companion object {

        private var INSTANCE: RateDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): RateDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            RateDatabase::class.java, "rates.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }

}