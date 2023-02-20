package com.example.pocket.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle

import com.example.pocket.R
import com.example.pocket.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //nav drawer
        binding.navDrawer.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.profileSec -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }

                R.id.settingSec -> {
                }

                R.id.aboutusSec -> {
                    Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show()
                }

                R.id.share -> {
                    Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
                }

            }
            true
        }


        //floatBtn
        binding.addBtn.setOnClickListener {
            val intent = Intent(this,AddStatusActivity::class.java)
            startActivity(intent)
        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }
}