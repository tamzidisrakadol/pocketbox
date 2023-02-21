package com.example.pocket.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.pocket.R
import com.example.pocket.adapter.IPostAdapter
import com.example.pocket.adapter.PostAdapter
import com.example.pocket.databinding.ActivityMainBinding
import com.example.pocket.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(), IPostAdapter {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var firestore: FirebaseFirestore
    private var postModelList = mutableListOf<Post>()
    private lateinit var postAdapter: PostAdapter


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

        //fireStore + setup recyclerView
        setUpFireStore()
        postAdapter = PostAdapter(postModelList,this,this)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter = postAdapter

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


    private fun setUpFireStore(){
        firestore = FirebaseFirestore.getInstance()
        val postRef= firestore.collection("posts")
        postRef.get().addOnSuccessListener {
            for (fPost in it){
                val posts = fPost.toObject(Post::class.java)
                postModelList.add(posts)
            }
            postAdapter.notifyDataSetChanged()

        }
    }

    override fun onClick(postId: String) {

    }
}