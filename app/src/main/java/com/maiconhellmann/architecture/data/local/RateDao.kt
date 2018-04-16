package com.maiconhellmann.architecture.data.local

import android.arch.persistence.room.*
import com.maiconhellmann.architecture.data.model.Rate

@Dao
interface RatesDao {

    /**
     * Select all rates from the rates table.
     *
     * @return all rates.
     */
    @Query("SELECT * FROM Rate")
    fun getRates(): List<Rate>

    /**
     * Insert a rate in the database. If the rate already exists, replace it.
     *
     * @param rate the rate to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRate(rate: Rate)

    /**
     * Update a rate.
     *
     * @param rate rate to be updated
     * @return the number of rates updated. This should always be 1.
     */
    @Update
    fun updateRate(rate: Rate): Int

    /**
     * Delete all rates.
     */
    @Query("DELETE FROM Rate")
    fun deleteRates()

}