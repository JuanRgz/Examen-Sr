package com.development.cursoandroid;

import android.app.Activity
import android.app.ComponentCaller
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

import com.development.cursoandroid.databinding.ActivityMainBinding;
import com.development.cursoandroid.fragments.PrimerFragment

class MainActivity : AppCompatActivity() {

    lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.root);

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, PrimerFragment())
        }
    }

    private fun navegarSegundaActivitdad(){
        val i = Intent(this, SegundaActivity::class.java)
        i.putExtra("nombre","Curso Android")
        startActivityForResult(i, 1)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        super.onActivityResult(requestCode, resultCode, data, caller)
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                val nombre = data?.getStringExtra("nombre2") ?: ""
            }
        }
    }
}