package com.maiconhellmann.architecture.misc.ext

import kotlinx.coroutines.*

fun launchAsync(block: suspend CoroutineScope.() -> Unit): Job {
    return GlobalScope.launch { block() }
}