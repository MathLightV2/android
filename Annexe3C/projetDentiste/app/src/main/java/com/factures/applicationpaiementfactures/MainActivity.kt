package com.factures.applicationpaiementfactures

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class MainActivity : AppCompatActivity() {

    lateinit var dent1: LinearLayout
    lateinit var dent2: LinearLayout
    var dent = Dent
    var dents: List<Dent> = emptyList() // Initialize as empty list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        UnSerialize()

        dent1 = findViewById(R.id.dent1)
        dent2 = findViewById(R.id.dent2)

        if (dents.isNotEmpty()) {
            (dent1.getChildAt(0) as EditText).setText(dents[0].numDent)
            (dent1.getChildAt(1) as CheckBox).isChecked = dents[0].traitement
            (dent1.getChildAt(2) as EditText).setText(dents[0].notes)

            (dent2.getChildAt(0) as EditText).setText(dents[1].numDent)
            (dent2.getChildAt(1) as CheckBox).isChecked = dents[1].traitement
            (dent2.getChildAt(2) as EditText).setText(dents[1].notes)
        }

        val dentUn = Dent(
            (dent1.getChildAt(0) as EditText).text.toString(),
            (dent1.getChildAt(1) as CheckBox).isChecked,
            (dent1.getChildAt(2) as EditText).text.toString()
        )

        val dentDeux = Dent(
            (dent2.getChildAt(0) as EditText).text.toString(),
            (dent2.getChildAt(1) as CheckBox).isChecked,
            (dent2.getChildAt(2) as EditText).text.toString()
        )

        // Update dents list
        dents = listOf(dentUn, dentDeux)
    }

    override fun onStop() {
        super.onStop()
        Serialize()
    }

    public fun Serialize() {
        try {
            val fos: FileOutputStream = openFileOutput("fichier.ser", MODE_PRIVATE)
            val oos = ObjectOutputStream(fos)
            oos.writeObject(dents)
            oos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    public fun UnSerialize() {
        try {
            val fis: FileInputStream = openFileInput("fichier.ser")
            val ois = ObjectInputStream(fis)
            dents = ois.readObject() as List<Dent>
            ois.close()
        } catch (e: Exception) {
            e.printStackTrace()
            dents = emptyList()
        }
    }
}
