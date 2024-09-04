package com.example.annexe1b

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun nbLigne() : Int {
        var nb = 0 ;
        lateinit var br : BufferedReader;
        var fis : FileInputStream = openFileInput("lol.txt")
        val isr = InputStreamReader(fis);
        br = BufferedReader(isr);
        var line = br.readLine();
 // br.use{} peut faire le même
        while (line != null){
            ++nb;
            line = br.readLine();
        }
        br.close()

        return nb;




    }

    fun nbLChar() : Int {
        var nb = 0 ;
        lateinit var br : BufferedReader;
        var fis : FileInputStream = openFileInput("lol.txt")
        val isr = InputStreamReader(fis);
        br = BufferedReader(isr);
        var line = br.readLine();

        while (line != null){
            nb += line.length;
            line = br.readLine();
        }
        br.close()
        return nb;



    }

    fun nbLCharC() : Int {
        var nb = 0 ;
        lateinit var br : BufferedReader;
        var fis : FileInputStream = openFileInput("lol.txt")
        val isr = InputStreamReader(fis);
        br = BufferedReader(isr);
        var line = br.readLine();

        while (line != null){
            nb += line.count { it == 'c' }
            line = br.readLine();

            }

        br.close()
        return nb;



    }

    fun modifier(ligne:String)  {
        var nb = 0 ;
        lateinit var br : BufferedWriter;
        var fis : FileOutputStream = openFileOutput("lol.txt", MODE_APPEND);
        val isr = OutputStreamWriter(fis);
        br = BufferedWriter(isr);
        br.newLine();
        br.write(ligne);
        br.close()



    }

}