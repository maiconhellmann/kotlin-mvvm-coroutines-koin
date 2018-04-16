package com.maiconhellmann.architecture.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper

/**
 */
abstract class AbstractViewModel : ViewModel() {

    /**
     * Handle data loading
     */
    val isDataLoading = MutableLiveData<Boolean>()

    /**
     * Handle errors
     */
    val exception = MutableLiveData<Throwable>()


    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }

    open fun setLoading(isLoading: Boolean? = true) {
        isDataLoading.value = isLoading

        if (isLoading == true) {
            exception.value = null
        }
    }

    open fun setError(t: Throwable) {
        exception.value = t
    }

}