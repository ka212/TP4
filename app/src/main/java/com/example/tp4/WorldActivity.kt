package com.example.tp4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp4.databinding.ActivityWorldBinding

class WorldActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorldBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWorldBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val continents = resources.getStringArray(R.array.Continent)
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, continents)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner1.adapter = adapter1

        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("Choisir un pays"))
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner2.adapter = adapter2

        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    val defaultAdapter = ArrayAdapter(this@WorldActivity, android.R.layout.simple_spinner_item, listOf("Choisir un pays"))
                    defaultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinner2.adapter = defaultAdapter
                    return
                }

                val continent = parent?.getItemAtPosition(position).toString()
                updateCountriesSpinner(continent)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun updateCountriesSpinner(continent: String) {
        val countriesArrayId = when (continent) {
            "Afrique" -> R.array.PaysAfr
            "Europe" -> R.array.PaysEur
            "Asie" -> R.array.PaysAsie
            "Océanie" -> R.array.PaysOc
            "Amérique" -> R.array.PaysAm
            else -> null
        }

        if (countriesArrayId != null) {
            val countries = resources.getStringArray(countriesArrayId)
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries.toList())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner2.adapter = adapter

            binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (position == 0) return

                    val selectedCountry = parent?.getItemAtPosition(position).toString()
                    val wikiUrl = "https://fr.wikipedia.org/wiki/" + selectedCountry.replace(" ", "_")
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(wikiUrl))
                    startActivity(intent)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
    }
}
