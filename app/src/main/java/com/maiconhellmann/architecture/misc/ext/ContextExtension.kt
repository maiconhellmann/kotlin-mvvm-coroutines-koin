package com.maiconhellmann.architecture.misc.ext

import android.content.ComponentName
import android.content.Context
import android.content.CursorLoader
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Looper
import android.provider.MediaStore
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.widget.Toast


fun Context.isNetworkConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo?.isConnectedOrConnecting ?: false
}

fun Context.toggleAndroidComponent(componentClass: Class<*>, enable: Boolean) {
    val componentName = ComponentName(this, componentClass)

    val newState = if (enable)
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    else
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED

    packageManager.setComponentEnabledSetting(componentName, newState, PackageManager.DONT_KILL_APP)
}

/**
 * Default short toast
 */
fun Context.toast(any: Any, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, any.toString(), duration).show()
}

/**
 * Default short toast
 */
fun Context.toast(@StringRes resString: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(resString), duration)
}

/**
 * Long duration toast
 */
fun Context.longToast(any: Any) {
    toast(any.toString(), Toast.LENGTH_LONG)
}

/**
 * Long duration toast
 */
fun Context.longToast(@StringRes stringRes: Int) {
    toast(getString(stringRes), Toast.LENGTH_LONG)
}

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(message, duration)
}

fun Fragment.toast(@StringRes resString: Int, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(getString(resString), duration)
}

fun Fragment.longToast(@StringRes stringRes: Int) {
    context?.toast(getString(stringRes), Toast.LENGTH_LONG)
}


fun Context.getPath(contentUri: Uri): String {
    val proj = arrayOf(MediaStore.Images.Media.DATA)
    val result: String

    if (Looper.myLooper() == null) {
        Looper.prepare()
    }
    val cursorLoader = CursorLoader(
            this,
            contentUri, proj, null, null, null)
    val cursor = cursorLoader.loadInBackground()

    result = if (cursor != null) {
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        cursor.getString(column_index)
    } else {
        contentUri.path
    }

    return result
}

fun Context.getColorCompat(@ColorRes resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}



