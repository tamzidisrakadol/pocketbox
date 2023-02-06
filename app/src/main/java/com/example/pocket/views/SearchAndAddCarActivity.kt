package com.example.pocket.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.pocket.R
import com.example.pocket.databinding.ActivitySearchAndAddCarBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchAndAddCarActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchAndAddCarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchAndAddCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.button.setOnClickListener {
//           val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
//            onBackPressed()
//        }

    }
}