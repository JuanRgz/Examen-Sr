package com.development.cursoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.development.cursoandroid.databinding.ActivitySegundaBinding;

public class SegundaActivity extends AppCompatActivity {

    ActivitySegundaBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivitySegundaBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        String nombre = getIntent().getExtras().getString("nombre");

        if(nombre != null){
            bind.txtOutput.setText(nombre);
        }

        bind.btnBack.setOnClickListener(view -> {
            Intent i = new Intent();
            i.putExtra("nombre2", "Holla desde segunda activity");
            setResult(SegundaActivity.RESULT_OK, i);
            finish();
        });
    }
}