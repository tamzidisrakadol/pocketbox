package com.example.pocket.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pocket.R
import com.example.pocket.databinding.ActivitySearchAndAddCarBinding

class SearchAndAddCarActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchAndAddCarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchAndAddCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            onBackPressed()
        }

    }
}