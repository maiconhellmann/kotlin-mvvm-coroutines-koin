package com.maiconhellmann.architecture.data

import com.maiconhellmann.architecture.data.local.RateDatabase
import com.maiconhellmann.architecture.data.remote.dto.RatesDto
import com.maiconhellmann.architecture.data.remote.endpoint.RateWebService

class RateRepository(private val remoteDataSource: RateWebService,
                     private val localDataSource: RateDatabase) {

    suspend fun getLatestRate(): RatesDto {
        //Database query example
//        val sizeDeffered = async {
//            localDataSource.rateDao().getRates().size
//        }
//        Timber.e("erro: ${sizeDeffered.await()}")

        //remote data source Request
        return remoteDataSource.latest().await()
    }

}