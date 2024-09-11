package com.example.myapplication

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.time.LocalDate

class AjouterActivity : AppCompatActivity() {
   lateinit var test: Button
   lateinit var champAjout: EditText
   lateinit var datepick : Button
   lateinit var texteDate : TextView
   var selectedDate: LocalDate = LocalDate.now()
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
        datepick = findViewById(R.id.echeance);
        texteDate = findViewById(R.id.date)

        test.setOnClickListener(ec);
        datepick.setOnClickListener(ec);


        /*
        ec = new Ecouteur();


        test.setOnClickListener(ec);*/
    }

    inner  class Ecouteur: OnClickListener, OnDateSetListener{
        override fun onClick(v: View?) {
            if (v === test) {
                var texteMemo = champAjout.text.toString();
                SingletonMemo.getInstance(this@AjouterActivity).ajouteMemo(Memo(texteMemo,selectedDate))
            }
            else {
                val d : DatePickerDialog = DatePickerDialog(this@AjouterActivity);
                d.setOnDateSetListener(this)
                d.show()
            }
            finish();
        }

        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

            selectedDate = LocalDate.of(year,month+1, dayOfMonth )
            texteDate.setText(selectedDate.toString());

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
