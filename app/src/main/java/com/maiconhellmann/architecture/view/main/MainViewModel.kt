package com.maiconhellmann.architecture.view.main

import android.arch.lifecycle.MutableLiveData
import com.maiconhellmann.architecture.data.RateRepository
import com.maiconhellmann.architecture.data.model.Rate
import com.maiconhellmann.architecture.misc.ext.launchAsync
import com.maiconhellmann.architecture.view.AbstractViewModel
import kotlinx.coroutines.experimental.Job

class MainViewModel(private val repository: RateRepository) : AbstractViewModel() {

    val rate = MutableLiveData<Rate>()

    var latestRateJob: Job?= null

    fun getLatestRate() {
        //Fun isn't suspended, so it's necessary to run in mainthread
        latestRateJob = launchAsync {
            try {
                //The data is loading
                setLoading()

                //Request with a suspended repository funcion
                val dto = repository.getLatestRate()

                //Set resuts
                rate.value = dto.rates

            } catch (t: Throwable) {
                //An error was throw
                setError(t)
            } finally {
                //Isn't loading anymore
                setLoading(false)
            }
        }
    }
}