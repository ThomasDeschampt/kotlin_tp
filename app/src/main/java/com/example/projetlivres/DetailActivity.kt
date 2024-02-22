package com.example.projetlivres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.google.firebase.firestore.FirebaseFirestore

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val titre = intent.getStringExtra("titre")
        val auteur = intent.getStringExtra("auteur")
        val description = intent.getStringExtra("description")
        val photo = intent.getStringExtra("photo")
        val posseder = intent.getBooleanExtra("posseder", false)

        findViewById<TextView>(R.id.titre).text = titre
        findViewById<TextView>(R.id.auteur).text = auteur
        findViewById<TextView>(R.id.description).text = description
        Picasso.get().load(photo).into(findViewById<ImageView>(R.id.photo))
        findViewById<CheckBox>(R.id.checkBox).isChecked = posseder

        val checkbox = findViewById<CheckBox>(R.id.checkBox)

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            val bdd = FirebaseFirestore.getInstance()
            val livresCollection = bdd.collection("Livres")
            if (isChecked) {
                livresCollection.whereEqualTo("titre", titre).get().addOnSuccessListener { result ->
                    val document = result.documents[0]
                    document.reference.update("posseder", true)
                }
            } else {
                livresCollection.whereEqualTo("titre", titre).get().addOnSuccessListener { result ->
                    val document = result.documents[0]
                    document.reference.update("posseder", false)
                }
            }
        }

        val retourButton = findViewById<Button>(R.id.button_retour)
        retourButton.setOnClickListener {
            finish()
        }

    }


}

