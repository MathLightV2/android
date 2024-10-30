package com.example.attelier;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Auto jaune, vert;

    TextView haut, bas;

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

        haut = findViewById(R.id.voitureHaut);
        bas = findViewById(R.id.voitureBas);

        jaune = new Auto("jaune");
        vert = new Auto("vert");
        jaune.start();
        vert.start();

    }


    public class Auto extends Thread{
        private int positionX;
        private String couleur;
        private boolean arret;

        private Handler h;

        public Auto(String couleur)
        {
            super(couleur); // nom du thread
            this.couleur = couleur;
            positionX =0;
            h= new Handler();

        }
        public void run() {
            while (!arret) {

                System.out.println("  nom du Thread" + Thread.currentThread().getName());
                positionX = positionX + new Random().nextInt(80);
                System.out.println(Thread.currentThread().getName() + " " + positionX);

                h.post(new Thread() {          public void run() {
                    //ce qu’on va faire ici ?…ce qui a rapport au Gui puisque on est ds le thread main

                    if ( couleur.equals("jaune"))
                        haut.setText(String.valueOf(positionX));
                    else if
                    (couleur.equals("vert"))
                        bas.setText(String.valueOf(positionX));
                }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}