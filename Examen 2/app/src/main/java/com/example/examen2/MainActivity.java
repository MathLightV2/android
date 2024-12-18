package com.example.examen2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    int etapes = 1;

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

        // VIEWS
        ImageView montgolfiere = findViewById(R.id.imageView);
        EditText champ = findViewById(R.id.editTextText);
        Button bouton = findViewById(R.id.button);

        /*
        //QUESTION 1

        // ANIMATORS
        ObjectAnimator transX = ObjectAnimator.ofFloat(montgolfiere, "translationX", 400);
        ObjectAnimator transY = ObjectAnimator.ofFloat(montgolfiere, "translationY", -500);

        AnimatorSet transXY = new AnimatorSet();
        transXY.setDuration(2000);
        transXY.playTogether(transX, transY);

        // ONCLICK
        bouton.setOnClickListener(source -> transXY.start());

        */


        // QUESTION 2
        ObjectAnimator anim1x = ObjectAnimator.ofFloat(montgolfiere, "translationX", 400);
        ObjectAnimator anim1y = ObjectAnimator.ofFloat(montgolfiere, "translationY", -500);

        AnimatorSet anim1 = new AnimatorSet();
        anim1.setDuration(2000);
        anim1.playTogether(anim1x, anim1y);

        ObjectAnimator anim2x = ObjectAnimator.ofFloat(montgolfiere, "translationX", 0);
        ObjectAnimator anim2y = ObjectAnimator.ofFloat(montgolfiere, "translationY", -1000);

        AnimatorSet anim2 = new AnimatorSet();
        anim2.setDuration(2000);
        anim2.playTogether(anim2x, anim2y);

        ObjectAnimator anim3 = ObjectAnimator.ofFloat(montgolfiere, "translationY", -1300);
        anim3.setDuration(2000);


        bouton.setOnClickListener(source -> {
            if (etapes == 1){
                anim1.start();
                etapes++;
            }
            else if (etapes == 2 && !anim1.isRunning()){
                anim2.start();
                etapes++;
            }
            else if (etapes == 3 && !anim2.isRunning()){
                anim3.start();
                etapes++;
            }
        });
    }
}