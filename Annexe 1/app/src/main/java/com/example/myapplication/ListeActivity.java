package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListeActivity extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_liste);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.AjouterActivityLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        list = findViewById(R.id.ListeMemo);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recuperer());
        list.setAdapter(adapter);


    }

    public ArrayList<String> recuperer() {
        ArrayList<String> temp = new ArrayList<>();
        BufferedReader br;

        try {
            FileInputStream fis = openFileInput("memo.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                temp.add(line);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    return null;
    }
}
