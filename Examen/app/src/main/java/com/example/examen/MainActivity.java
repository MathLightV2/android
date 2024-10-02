package com.example.examen;

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

    TextView resulta;

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

        UnSerialize();
        date = findViewById(R.id.Date);
        resulta = findViewById(R.id.result);
        date.setText( "derniere utilisation: " +  lastIme.toString());


        // lireFichier()
        //int nb = 0;
        // for(int x =0; x< v.size();++x){  fait plenter

        //   if(v.get(x).getDateTrois() == "90" ){
        //     ++nb;

        //}
        // }
    //resulta.setText((String) nb));

    }

    @Override
    protected void onStop() {
        super.onStop();
        Serialize(LocalDateTime.now());
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
            lastIme = (LocalDateTime) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            lastIme = LocalDateTime.now();
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
