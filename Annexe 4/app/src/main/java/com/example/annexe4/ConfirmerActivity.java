package com.example.annexe4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmerActivity extends AppCompatActivity {

    EditText prenom, nom;
    Button bouton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Ecouteur ec = new Ecouteur();
        prenom = findViewById(R.id.editTextText);
        nom = findViewById(R.id.editTextText2);
        bouton = findViewById(R.id.button2);

        bouton.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v == bouton){
                Intent intent = new Intent();
                intent.putExtra("utilisateur", new Utilisateur(prenom.getText().toString(), nom.getText().toString()));
                setResult(ConfirmerActivity.RESULT_OK, intent);
                finish();
            }
        }
    }
}