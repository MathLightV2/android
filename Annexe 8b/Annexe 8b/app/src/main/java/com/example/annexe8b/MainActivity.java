package com.example.annexe8b;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button boutonGauche = findViewById(R.id.button);
        Button boutonTitre = findViewById(R.id.button2);
        Button boutonSplash = findViewById(R.id.button3);

        boutonGauche.setOnClickListener(source -> startActivity(new Intent(MainActivity.this, AnimationGaucheActivity.class)));
        boutonTitre.setOnClickListener(source -> startActivity(new Intent(MainActivity.this, AnimationTitreActivity.class)));
        boutonSplash.setOnClickListener(source -> startActivity(new Intent(MainActivity.this, AnimationGaucheActivity.class)));
    }
}