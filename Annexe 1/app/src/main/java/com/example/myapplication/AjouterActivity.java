package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class AjouterActivity extends AppCompatActivity {

    Button test;
    EditText champAjout;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ajouter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.AjouterActivityLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        test.findViewById(R.id.BoutonAjouterMemo);
        champAjout.findViewById(R.id.champAjout);

        ec = new Ecouteur();


        test.setOnClickListener(ec);



    }
    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            FileOutputStream fos;
            BufferedWriter bw = null;
            try {
                fos = openFileOutput("memo.txt", Context.MODE_APPEND);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                bw = new BufferedWriter(osw);
                bw.write(champAjout.getText().toString());
                bw.newLine();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            finally {
                fermerFlux(bw);
            }
            finish();
        }

    }
    public void fermerFlux(Writer bw){
        try {
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

