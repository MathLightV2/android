package com.example.annexe4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button bouton;
    ActivityResultLauncher<Intent> lanceur;
    Utilisateur utilisateur;

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

        Ecouteur ec = new Ecouteur();
        textView = findViewById(R.id.textView);
        bouton = findViewById(R.id.button);
        bouton.setOnClickListener(ec);

        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new CallBackConfirmer());
        UnSerialize();
        if (savedInstanceState != null){
            utilisateur = (Utilisateur)savedInstanceState.getSerializable("utilisateur");
            message();
        }

    }

    private void message(){
        String message = "Bonjour " + utilisateur.getPrenom() + " " + utilisateur.getNom() + " !";
        textView.setText(message);
    }

    private class CallBackConfirmer implements ActivityResultCallback<ActivityResult>{
        @Override
        public void onActivityResult(ActivityResult o) {
            if (o.getResultCode() == ConfirmerActivity.RESULT_OK){
                utilisateur = (Utilisateur) o.getData().getExtras().get("utilisateur");
                message();
            }
        }
    }

    private class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (v == bouton){
                lanceur.launch(new Intent(MainActivity.this, ConfirmerActivity.class));
                Serialize();
            }
        }
    }

    public void Serialize() {
        try {
            FileOutputStream fos = openFileOutput("fichier.ser", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(utilisateur);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void UnSerialize() {
        try {
            FileInputStream fis = openFileInput("fichier.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            utilisateur = (Utilisateur) ois.readObject();
            message();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Serialize();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("utilisateur", utilisateur);
    }
}