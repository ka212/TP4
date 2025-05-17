package com.example.tp4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp4.databinding.ActivityCitiesBinding
import java.net.URLEncoder


class CitiesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        var binding = ActivityCitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val villes = arrayOf(
            "Tunis", "Sfax", "Sousse", "Ettadhamen", "Kairouan", "Gabès", "Bizerte",
            "Ariana", "Gafsa", "El Mourouj", "Ben Arous", "La Marsa", "Monastir",
            "Médenine", "Tataouine", "Tozeur", "Kebili", "Nabeul", "Zarzis",
            "Mahdia", "Jendouba", "Le Kef", "Siliana", "Kasserine", "Beja", "Manouba"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, villes)
        binding.list.adapter = adapter

        binding.list.setOnItemClickListener { parent, view, position, id ->
            val villeSelectionnee = parent.getItemAtPosition(position).toString()

            binding.text.text = "Ville sélectionnée : $villeSelectionnee"

            val query = URLEncoder.encode(villeSelectionnee, "UTF-8")
            val url = "https://www.google.com/search?q=$query"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)




        }
    }}