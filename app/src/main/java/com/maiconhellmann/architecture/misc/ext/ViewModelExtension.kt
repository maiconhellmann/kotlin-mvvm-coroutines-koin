package com.maiconhellmann.architecture.misc.ext

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.support.v7.app.AppCompatActivity
import com.maiconhellmann.architecture.misc.ViewLifecycleFragment

fun <T> Any.observe(owner: LifecycleOwner, data: MutableLiveData<T>, function: (data: T?) -> Unit) {
    data.observe(owner, android.arch.lifecycle.Observer {
        function(it)
    })
}

fun <T> AppCompatActivity.observe(data: MutableLiveData<T>, function: (data: T?) -> Unit) {
    data.observe(this@observe, android.arch.lifecycle.Observer {
        function(it)
    })
}
//
//fun <T> Fragment.observeFromFragment(data: MutableLiveData<T>, function: (data: T?) -> Unit) {
//    data.observe(this@observeFromFragment, android.arch.lifecycle.Observer {
//        function(it)
//    })
//}
//
//fun <T> Fragment.observeFromActivity(data: MutableLiveData<T>, function: (data: T?) -> Unit) {
//    data.observe(this@observeFromActivity.activity as AppCompatActivity, android.arch.lifecycle.Observer {
//        function(it)
//    })
//}
fun <T> ViewLifecycleFragment.observe(data: MutableLiveData<T>, function: (data: T?) -> Unit) {
    getViewLifecycleOwner()?.let{
        data.observe(it, android.arch.lifecycle.Observer {
            function(it)
        })
    }
}