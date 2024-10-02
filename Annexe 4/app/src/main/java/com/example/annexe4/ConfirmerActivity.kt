package com.example.annexe4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Vector

class ConfirmerActivity : AppCompatActivity() {
    var prenom: EditText? = null
    var nom: EditText? = null
    var bouton: Button? = null
    var v: Vector<*>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_confirmer)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ec: Ecouteur = Ecouteur()
        prenom = findViewById(R.id.editTextText)
        nom = findViewById(R.id.editTextText2)
        bouton = findViewById(R.id.button2)

        v = Vector<Any?>()
        v.add("S")

        val S = v.get(0) as String


        bouton.setOnClickListener(ec)
    }

    private inner class Ecouteur : View.OnClickListener {
        override fun onClick(v: View) {
            if (v === bouton) {
                val intent = Intent()
                intent.putExtra("utilisateur", Utilisateur(prenom!!.text.toString(), nom!!.text.toString()))
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}