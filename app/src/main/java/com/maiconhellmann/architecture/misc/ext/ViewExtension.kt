package com.maiconhellmann.architecture.misc.ext

import android.content.res.ColorStateList
import android.support.annotation.ColorInt
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.FrameLayout
import timber.log.Timber

fun View.visible(){
    this.visibility = View.VISIBLE
}
fun View.visible(visible : Boolean){
    if(visible){
        visible()
    }else{
        gone()
    }
}
fun View.gone(){
    this.visibility = View.GONE
}
fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.isVisible(): Boolean{
    return visibility == View.VISIBLE
}
fun View.isGone(): Boolean{
    return visibility == View.GONE
}
fun View.isInvisible(): Boolean{
    return visibility == View.INVISIBLE
}

fun View.snackbar(resId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    snackbar(this.resources.getString(resId), duration)
}

fun View.snackbar(msg: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, msg, duration).show()
}
fun View.longSnackbar(resId: Int) {
    snackbar(resId, Snackbar.LENGTH_LONG)
}


fun TextInputEditText.setTextEx(text: CharSequence?){
    this.setText(text)
/*
    if(text?.toString()?.isEmpty() == true){
        setInputTextLayoutColor(Color.RED)
    }else{
        setInputTextLayoutColor(Color.LTGRAY)
    }
    */
}
fun TextInputEditText.setInputTextLayoutColor(@ColorInt color: Int) {
    try {

        val layout = if(parent is FrameLayout){
            parent.parent as TextInputLayout
        }else{
            parent as TextInputLayout
        }

        layout.editText?.highlightColor = color
        layout.editText?.setHintTextColor(color)
        layout.editText?.setTextColor(color)

        val fDefaultTextColor = TextInputLayout::class.java.getDeclaredField("mDefaultTextColor")
        fDefaultTextColor.isAccessible = true
        fDefaultTextColor.set(layout, ColorStateList(arrayOf(intArrayOf(0)), intArrayOf(color)))

        val fFocusedTextColor = TextInputLayout::class.java.getDeclaredField("mFocusedTextColor")
        fFocusedTextColor.isAccessible = true
        fFocusedTextColor.set(layout, ColorStateList(arrayOf(intArrayOf(0)), intArrayOf(color)))
    } catch (e: Exception) {
        Timber.e(e)
    }

}
