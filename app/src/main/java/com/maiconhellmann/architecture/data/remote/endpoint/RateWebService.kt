package com.maiconhellmann.architecture.data.remote.endpoint

import com.maiconhellmann.architecture.BuildConfig
import com.maiconhellmann.architecture.data.remote.dto.RatesDto
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface RateWebService {

    @GET("latest?${BuildConfig.ACCESS_KEY}")
    fun latest(): Deferred<RatesDto>
}