package com.example.annexe5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    ListView liste;
    Vector<HashMap<String, Object>> vec = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listSam), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        liste = findViewById(R.id.listeS);

        HashMap<String, Object> temp = new HashMap<>();
        temp.put("position", "3");
        temp.put("nom", "Touch Me");
        temp.put("date", "22/06/86");
        temp.put("image", R.drawable.touchme);
        vec.add(temp);

        temp = new HashMap<>();
        temp.put("position", "8");
        temp.put("nom", "Nothing gonna stop me now");
        temp.put("date", "30/05/86");
        temp.put("image", R.drawable.nothing);
        vec.add(temp);

        temp = new HashMap<>();
        temp.put("position", "3");
        temp.put("nom", "santamaria");
        temp.put("date", "28/08/98");
        temp.put("image", R.drawable.santamaria);
        vec.add(temp);

        temp = new HashMap<>();
        temp.put("position", "3");
        temp.put("nom", "hot boy");
        temp.put("date", "10/04/2018");
        temp.put("image", R.drawable.hotboy);
        vec.add(temp);


        SimpleAdapter adapter = new SimpleAdapter(this,
                vec, R.layout.pochette, new String[]{"position", "nom", "date", "image"},
                new int[]{R.id.possition, R.id.nom, R.id.date, R.id.image});

        liste.setAdapter(adapter);

        liste.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            HashMap<String, Object> rep = vec.get(position);
            String nom = (String) rep.get("nom");

            Toast toast = Toast.makeText(MainActivity.this, nom, Toast.LENGTH_SHORT);
            toast.show();
        });

    }





    }