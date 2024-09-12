package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var boutonAjouter: Button
    lateinit var boutonAfficher: Button
    lateinit var boutonQuitter: Button
    lateinit var ec: Ecouteur

    // var = variable qui peut changer de variable
    // val "final" ne peut pas changer de valeur
    // ? la variable peut être nul
    // doivent absolument être initialisé au départ ( ou être déclarées dans un constructeur )
    // dnas une activité on peut utulisé a lateinit pour ne pas a avoir a inisilialser une variable lors d'une déclaration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.AjouterActivityLayout)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ec = Ecouteur() // déclaration du type optionel

        boutonAjouter = findViewById(R.id.BoutonAjouter)
        boutonAfficher = findViewById(R.id.BoutonAficher)
        boutonQuitter = findViewById(R.id.BoutonQuitter)


        boutonAjouter.setOnClickListener(ec)
        boutonAfficher.setOnClickListener(ec)
        boutonQuitter.setOnClickListener(ec)

        try {
            val ref = SingletonMemo.getInstance(applicationContext);
            val temp = ref.deSerializerListe();
            ref.listeMemo = temp;
        } catch(e:Exception) {
            e.printStackTrace();
        }
        
    }

    inner class Ecouteur : View.OnClickListener {
        override fun onClick(v: View) {
            if (v === boutonAjouter) {
                val i = Intent(this@MainActivity, AjouterActivity::class.java)
                startActivity(i)
            } else if (v === boutonAfficher) {
                val i = Intent(this@MainActivity, ListeActivity::class.java)
                startActivity(i)
            } else if (v === boutonQuitter) {
                finish()
            }
        }
    }
}