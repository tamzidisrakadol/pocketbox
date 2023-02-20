package com.example.pocket.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pocket.R
import com.example.pocket.daos.PostDao
import com.example.pocket.databinding.ActivityAddStatusBinding

class AddStatusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStatusBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val postDao = PostDao()


        binding.postBtn.setOnClickListener {
            var status= binding.statusEditText.text.toString()
            if (status.isNotEmpty()){
                postDao.addPost(status)
                finish()
            }
        }
    }
}