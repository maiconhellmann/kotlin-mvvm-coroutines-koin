package com.maiconhellmann.architecture.misc.ext

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.text.InputType


fun EditText.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val focusedView = rootView.findFocus() ?: rootView
    val token = focusedView.applicationWindowToken

    imm.hideSoftInputFromWindow(token, 0)
    imm.hideSoftInputFromWindow(token, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun EditText.showKeyboard() {
    requestFocus()

    post {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.showSoftInput(this, 0)
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun EditText.disableSoftInputFromAppearing() {
    setRawInputType(InputType.TYPE_NULL)
    setTextIsSelectable(true)
    isFocusable = true

}