package com.example.annexe8;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    ObjectAnimator animation;
    ObjectAnimator animation2;
    View object;
    Button bouton;
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

        Path p = new Path();
        p.moveTo(50,200);
        p.lineTo(900,100);
        p.lineTo(900,650);
        p.arcTo(400,600,900,1000,0,130,false);

        object = findViewById(R.id.view);
        bouton = findViewById(R.id.start);

        animation = ObjectAnimator.ofFloat(object,"x","y",p);
       // animation = ObjectAnimator.ofFloat(object,View.TRANSLATION_X, 500);
        //animation.setDuration(3000); // duré par défaut 300 ms
        // changer l'interpolation
       // animation.setInterpolator(new BounceInterpolator());
        //animation.setRepeatCount(ValueAnimator.INFINITE);
        //animation.setRepeatMode(ValueAnimator.REVERSE);

        animation2 = ObjectAnimator.ofArgb(object,"backgroundColor", Color.BLUE,Color.rgb(49,129,3));

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animation, animation2);
        set.setDuration(3000);


        bouton.setOnClickListener( v -> set.start() );
    }
}