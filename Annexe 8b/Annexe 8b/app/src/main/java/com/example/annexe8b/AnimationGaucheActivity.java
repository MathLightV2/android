package com.example.annexe8b;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AnimationGaucheActivity extends AppCompatActivity {
    boolean up = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animation_gauche);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayout bloc = findViewById(R.id.bloc);
        ObjectAnimator animation = ObjectAnimator.ofFloat(bloc, "translationX", 800);
        animation.setDuration(700);


        Button start = findViewById(R.id.button4);
        start.setOnClickListener(source -> {
            if (!up)
                animation.start();
            else
                animation.reverse();
            up = !up;
        });
    }
}