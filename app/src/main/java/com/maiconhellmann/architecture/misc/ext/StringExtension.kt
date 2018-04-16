package com.maiconhellmann.architecture.misc.ext

fun String.Companion.empty(): String{
    return ""
}

fun String.Companion.isEmpty(text: Any?): Boolean {
    return text==null || text.toString().trim() == String.empty()
}

fun String.Companion.isNotEmpty(text: Any?): Boolean {
    return text!=null && text.toString().trim() != String.empty()
}
