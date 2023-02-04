package com.example.pocket

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.pocket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.open,R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navDrawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profileSec -> {
                    Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show()
                }

                R.id.settingSec -> {
                    Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
                }

                R.id.aboutusSec -> {
                    Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show()
                }

                R.id.share ->{
                    Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
                }

            }
            true
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}