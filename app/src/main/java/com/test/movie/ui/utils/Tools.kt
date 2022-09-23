package com.test.movie.ui.utils

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.test.movie.core.App
import com.test.movie.core.location.LocationService
import java.io.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatterBuilder
import java.util.*

fun Fragment.showToast(msg: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(requireContext(), msg, length).show()
}

fun Activity.validatePermission(continua:  () -> Unit){
    if(checkingPermission()){
       continua()
    } else{
        requestingPermission()
    }
}

fun Activity.checkingPermission(): Boolean{
    val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)
    var success = true
    permissions.forEach { permission ->
        if(ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED){
            success = false
        }
    }
    return success
}

fun Activity.requestingPermission() {
    val permission = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)
    ActivityCompat.requestPermissions(this,
        permission,
        101);
}

fun getDate(): String {
    return SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale("es-MX")).format(Date())
}

fun toMinutes(minutes: Int): Long {
    return 1000L * 60 * minutes
}