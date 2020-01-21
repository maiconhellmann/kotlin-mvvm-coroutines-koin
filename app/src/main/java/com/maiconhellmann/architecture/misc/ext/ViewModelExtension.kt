package com.maiconhellmann.architecture.misc.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.maiconhellmann.architecture.misc.ViewLifecycleFragment

fun <T> Any.observe(owner: LifecycleOwner, data: MutableLiveData<T>, function: (data: T?) -> Unit) {
    data.observe(owner, Observer {
        function(it)
    })
}

fun <T> AppCompatActivity.observe(data: MutableLiveData<T>, function: (data: T?) -> Unit) {
    data.observe(this@observe, Observer {
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
    viewLifecycleOwner.let{
        data.observe(it, Observer {
            function(it)
        })
    }
}