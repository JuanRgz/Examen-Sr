package com.development.cursoandroid.ui.fragments

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.versionedparcelable.VersionedParcelize

class MainViewModel: ViewModel() {
    private val user = MutableLiveData<Usuario>()

    fun setUser(usuario: Usuario){
        user.value = usuario
    }

    fun getUser(): LiveData<Usuario>{
        return user
    }
}

data class Usuario(val nombre: String, val edad: Int)