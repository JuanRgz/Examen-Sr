package com.test.movie.ui.fragment.images

import android.app.Activity
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.test.movie.R
import com.test.movie.core.App
import com.test.movie.core.location.FirebaseRepository
import com.test.movie.ui.utils.ResponseDialog

class UploadImageViewModel: ViewModel() {
    fun saveImage(uri: Uri, activity: Activity, callback: ()-> Unit) {
        FirebaseRepository().uploadImage(uri){ isSuccess ->
            callback()
            if(isSuccess){
                ResponseDialog.showDialog(activity, App.instance.getString(R.string.save_image_success), success = true)
            } else{
                ResponseDialog.showDialog(activity, App.instance.getString(R.string.save_image_success_error))
            }
        }
    }

}