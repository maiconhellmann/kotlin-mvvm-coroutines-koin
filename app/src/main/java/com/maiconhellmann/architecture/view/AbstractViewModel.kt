package com.maiconhellmann.architecture.view

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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

    open suspend fun setLoading(isLoading: Boolean? = true) {

        withContext(Dispatchers.Main) {
            isDataLoading.value = isLoading

            if (isLoading == true) {
                exception.value = null
            }
        }
    }

    open suspend fun setError(t: Throwable) {
        withContext(Dispatchers.Main) {
            exception.value = t
        }
    }

}