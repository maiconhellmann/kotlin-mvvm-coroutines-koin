package com.maiconhellmann.architecture.misc.ext

import android.Manifest
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import com.maiconhellmann.architecture.view.ViewConstants


fun Activity.copytoClipBoard(label: String, value: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(label, value)
    clipboard.primaryClip = clip
}

fun Fragment.copytoClipBoard(label: String, value: String) {
    activity?.copytoClipBoard(label, value)
}

fun Activity.hasPermissions(): Boolean {
    val permissions = arrayOf(Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION)

    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        permissions
                .filter { ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }
                .forEach { return false }
    }
    return true
}

fun Activity.requestPermissions() {
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var permissions = emptyArray<String>()

        //Camera
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            permissions += Manifest.permission.CAMERA
        }
        //Write storage
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissions += Manifest.permission.WRITE_EXTERNAL_STORAGE
        }

        //Location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissions += Manifest.permission.ACCESS_FINE_LOCATION
        }

        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this,
                    permissions, ViewConstants.REQUEST_APP_PERMISSIONS)
        }
    }
}