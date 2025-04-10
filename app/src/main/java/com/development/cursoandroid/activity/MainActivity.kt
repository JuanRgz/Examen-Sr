package com.development.cursoandroid.activity;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.development.cursoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.root);


    }
}