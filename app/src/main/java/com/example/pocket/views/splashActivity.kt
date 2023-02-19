package com.example.pocket.views

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.pocket.R
import com.example.pocket.daos.UserDaos
import com.example.pocket.databinding.ActivitySplashBinding
import com.example.pocket.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class splashActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding
    private val RC_SIGN_IN: Int =1233
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient:GoogleSignInClient
    private lateinit var mProgressDialog: ProgressDialog
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        firebaseAuth = FirebaseAuth.getInstance()
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setTitle("Logging in")
        mProgressDialog.setMessage("Please be patient")
        mProgressDialog.setCancelable(false)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.your_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.signInBtn.setOnClickListener {
            signInWithGoogle()
        }


    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser!=null){
            updateUI()
        }
    }

    private fun signInWithGoogle() = lifecycleScope.launch(Dispatchers.IO) {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private  fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        val authResult = firebaseAuth.signInWithCredential(credential)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                lifecycleScope.launch(Dispatchers.IO) {
                    firebaseAuthWithGoogle(account!!)
                    withContext(Dispatchers.Main){
                        mProgressDialog.show()
                        delay(3000)
                        mProgressDialog.hide()
                        updateUI()
                    }
                }
            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }

    private fun updateUI(){
        val fUser = FirebaseAuth.getInstance().currentUser
        if (fUser!=null){
            val user = User(fUser.uid,fUser.displayName,fUser.photoUrl.toString())
            val userDaos = UserDaos()
            userDaos.addUser(user)
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}