package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class AjouterActivity : AppCompatActivity() {
   lateinit var test: Button
   lateinit var champAjout: EditText
    val ec = Ecouteur();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_ajouter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.AjouterActivityLayout)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        champAjout = findViewById(R.id.champAjout)
        test = findViewById(R.id.BoutonAjouterMemo)

        test.setOnClickListener(ec);


        /*
        ec = new Ecouteur();


        test.setOnClickListener(ec);*/
    }

    inner  class Ecouteur: OnClickListener{
        override fun onClick(v: View?) {

            var texteMemo = champAjout.text.toString();
            val fos  : FileOutputStream = openFileOutput("memo.txt", MODE_APPEND)
            val osw = OutputStreamWriter(fos)
            val bw = BufferedWriter(osw)
            bw.write(texteMemo)
            bw.newLine()
            bw.close()
            finish()
        }
    }





    /*     private class Ecouteur implements View.OnClickListener {
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
}*/
}
