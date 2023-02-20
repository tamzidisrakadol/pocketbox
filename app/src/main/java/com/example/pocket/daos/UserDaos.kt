package com.example.pocket.daos

import com.example.pocket.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDaos {

    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection("users")


    //save data to FireStore
    fun addUser(user: User?) {
        user?.let {
            GlobalScope.launch {
                userCollection.document(user.uId).set(it)
            }
        }
    }

    fun getUserById(uId: String): Task<DocumentSnapshot> {
        return userCollection.document(uId).get()
    }
}