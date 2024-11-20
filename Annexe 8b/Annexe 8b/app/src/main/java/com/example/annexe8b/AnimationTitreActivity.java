package com.example.annexe8b;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AnimationTitreActivity extends AppCompatActivity {
    boolean up = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animation_titre);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // TextView
        TextView textView = findViewById(R.id.textView);
        textView.setScaleX(100);
        textView.setScaleY(100);
        textView.setAlpha(0);

        // Animations
        ObjectAnimator animationScaleX = ObjectAnimator.ofFloat(textView, "scaleX", 1);
        ObjectAnimator animationScaleY = ObjectAnimator.ofFloat(textView, "scaleY", 1);
        ObjectAnimator animationAlpha = ObjectAnimator.ofFloat(textView, "alpha", 1);

        // AnimatorSet
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.setDuration(3000);
        animatorSet.playTogether(animationScaleX, animationScaleY, animationAlpha);


        Button bouton = findViewById(R.id.button5);
        bouton.setOnClickListener(source -> animatorSet.start());
    }
}