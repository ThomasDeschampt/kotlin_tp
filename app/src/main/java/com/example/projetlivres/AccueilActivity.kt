package com.example.projetlivres

import Livres
import LivresDAO
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class AccueilActivity : AppCompatActivity() {

    private lateinit var malist: RecyclerView
    val listeadapter = ListeAdapter()

    val livresDAO = LivresDAO()
    var livres = listOf<Livres>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accueil_activity)

        recupererlivres()
        malist= findViewById(R.id.malist)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        malist.layoutManager = layoutManager

        malist.adapter = listeadapter

    }

    override fun onResume() {
        super.onResume()

        recupererlivres()
        malist= findViewById(R.id.malist)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        malist.layoutManager = layoutManager

        malist.adapter = listeadapter
    }

    fun recupererlivres() {
        val bdd = FirebaseFirestore.getInstance()
        val livresCollection = bdd.collection("Livres")

        livresCollection.get().addOnSuccessListener { result ->
           livres = livresDAO.creerlivre(result)
            listeadapter.mlivre = livres
            listeadapter.notifyDataSetChanged()
        }
    }
}
