package com.development.cursoandroid.ui.firestore

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.development.cursoandroid.R
import com.development.cursoandroid.databinding.ActivityFirestoreBinding
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreActivity : AppCompatActivity() {

    lateinit var bind: ActivityFirestoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = ActivityFirestoreBinding.inflate(layoutInflater)
        setContentView(bind.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = FirebaseFirestore.getInstance()
        db.collection("ciudades").document("LA").addSnapshotListener { value, error ->
            value?.let { document ->
                val ciudad = document.toObject(Ciudad::class.java)
                Log.d("Firebase", "Color: ${ciudad?.color}")
                Log.d("Firebase", "Population: ${ciudad?.population}")
                Log.d("Firebase", "PC: ${ciudad?.pc}")
            }
        }
        db.collection("ciudades").document("NY").set(Ciudad(8000000, "blue", 30)).addOnSuccessListener {
            Log.d("Firebase", "Ciudad creada")
        }.addOnFailureListener {
            Log.d("Firebase", "Error")
        }
    }
}

data class Ciudad(val population: Int = 0, val color: String = "", val pc: Int = 0)