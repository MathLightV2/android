package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
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
import java.util.Vector

class ListeActivity : AppCompatActivity() {
     lateinit var list: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_liste)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.AjouterActivityLayout)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        list = findViewById(R.id.ListeMemo)

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, convertir())
        list.setAdapter(adapter)

    }

    fun convertir():Vector<String>{
        var listeTexteMemo = Vector<String>()
        var listeMemo = SingletonMemo.getInstance(this@ListeActivity).listeMemo
        for ( memo in listeMemo)
            listeTexteMemo.add(memo.texte)
        return listeTexteMemo

    }



        /*       list = findViewById(R.id.ListeMemo);
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
}*/

}