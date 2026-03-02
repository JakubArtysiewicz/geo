package com.example.geo

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.geo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val locations = listOf<Location>(
            Location("Ruch lewostronny","Czerwone budki telefoniczne", "Wielka Brytania"),
            Location("Dużo rowerów"," Płaski teren"," Holandia"),
            Location("Sahara", "arabski"," Maroko"),
            Location("Fiordy", "Zorza polarna", "Norwegia"),
            Location("Amazonia", "Język portugalski", "Brazylia")
        )
        binding.tvScore.visibility = View.INVISIBLE
        var numberOfPoints = 0;
        var numberOfGeo = 1;
        binding.tvTitle.text = "Lokalizacja " + numberOfGeo.toString()
        fun displayHints(numberOfLocation: Int){
            binding.tvClue1.text = locations[numberOfLocation-1].hint1
            binding.tvClue2.text = locations[numberOfLocation-1].hint2
        }

        if(numberOfGeo == 1){
            displayHints(numberOfGeo)
        }
        binding.btnNext.setOnClickListener {
            binding.etAnswer.text.clear()
            numberOfGeo += 1
            displayHints(numberOfGeo)
            binding.btnCheck.isEnabled = true
        }

        binding.btnCheck.setOnClickListener {
            displayHints(numberOfGeo)
            val answer = binding.etAnswer.text.toString()
            val countyAnswer = locations[numberOfGeo-1].county
            if(answer == countyAnswer) {
                binding.tvResult.text = "Poprawna Odpowiedz"
                numberOfPoints += 1
                binding.btnCheck.isEnabled = false
            }
            else {
                binding.tvResult.text = "Źle, poprawna odpowiedź to:" + countyAnswer
                numberOfPoints += 1
                binding.btnCheck.isEnabled = false
            }
        }
    }
}