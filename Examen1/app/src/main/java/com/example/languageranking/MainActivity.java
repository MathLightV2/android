package com.example.languageranking;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    Vector<Langage> v;
     LocalDateTime lastIme;
     TextView date;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Serialize(lastIme = LocalDateTime.now());
        }
        UnSerialize();
        date.setText( lastIme.toString()  );

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // jai api 24 donc faut mettre ca
            Serialize(LocalDateTime.now());
        }
    }

    public void Serialize(LocalDateTime date) {
        try {
            FileOutputStream fos = openFileOutput("fichier.ser", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(date);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void UnSerialize() {
        try {
            FileInputStream fis = openFileInput("fichier.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                lastIme = (LocalDateTime) ois.readObject();
            }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
    }







    public void lireFichier() throws FileNotFoundException {
        BufferedReader br;
        v = new Vector<Langage>();


        try {
            FileInputStream fis = openFileInput("langage.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                // jsp comment aller chercher les info
                v.add(new Langage(line,line,line,line));
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
