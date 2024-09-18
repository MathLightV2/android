package com.example.annexe3b

import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class MainActivity : AppCompatActivity() {

    lateinit var seekBarUn: SeekBar
    lateinit var seekBarDeux: SeekBar
    lateinit var seekBarTrois: SeekBar

    lateinit var listeDeSeek: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        seekBarUn = findViewById(R.id.unSeek)
        seekBarDeux = findViewById(R.id.deuxSeek)
        seekBarTrois = findViewById(R.id.troisSeek)

        // Initialiser listeDeSeek
        listeDeSeek = ArrayList()

        try {
            val fis: FileInputStream = openFileInput("fichier.ser")
            val ois = ObjectInputStream(fis)
            listeDeSeek = ois.readObject() as? ArrayList<Int>
                ?: throw ClassCastException("L'objet n'est pas une ArrayList<Int>")

            if (listeDeSeek.size >= 3) {
                seekBarUn.setProgress(listeDeSeek[0])
                seekBarDeux.setProgress(listeDeSeek[1])
                seekBarTrois.setProgress(listeDeSeek[2])
            } else {
                // Gérer le cas où la liste ne contient pas assez d'éléments
                seekBarUn.setProgress(0)
                seekBarDeux.setProgress(0)
                seekBarTrois.setProgress(0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            seekBarUn.setProgress(0)
            seekBarDeux.setProgress(0)
            seekBarTrois.setProgress(0)
        }
    }

    override fun onStop() {
        super.onStop()
        val fos: FileOutputStream = openFileOutput("fichier.ser", MODE_PRIVATE)
        val oos = ObjectOutputStream(fos)


        listeDeSeek.clear()
        listeDeSeek.add(seekBarUn.progress)
        listeDeSeek.add(seekBarDeux.progress)
        listeDeSeek.add(seekBarTrois.progress)

        oos.writeObject(listeDeSeek)
        oos.close()
    }
}
