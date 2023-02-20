package com.example.pocket.daos

import android.util.Log
import com.example.pocket.model.Post
import com.example.pocket.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PostDao {

    private val db = FirebaseFirestore.getInstance()
    private val postCollection = db.collection("posts")
    private val auth = Firebase.auth


    fun addPost(text: String) {
        GlobalScope.launch {
            val currentUserId = auth.currentUser!!.uid
            val userDao = UserDaos()
            val user = userDao.getUserById(currentUserId).await().toObject(User::class.java)
            val currentTime = System.currentTimeMillis()
            val post = Post(text, user!!, currentTime)
            postCollection.document().set(post)

        }
    }
}