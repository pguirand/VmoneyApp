package com.example.vmoneyapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.vmoneyapp.R
import com.example.vmoneyapp.databinding.LoginViewBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.RuntimeException

class LoginActivity : AppCompatActivity() {
    private val RC_SIGN_IN: Int = 1
    private lateinit var signInRequest: BeginSignInRequest
    private val TAG: String? = "MAIN_PAGE"
    private lateinit var analytics: FirebaseAnalytics
    lateinit var auth: FirebaseAuth
    lateinit var binding: LoginViewBinding
    private lateinit var googleSignInClient: GoogleSignInClient


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
//        updateUI(currentUser)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.login_view)

        analytics = Firebase.analytics

        // [START config_signin]
        // Configure Google Sign In

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        auth = Firebase.auth


        binding = LoginViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val buttonLogin = binding.loginBtn

        binding.googleBtn?.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        binding.signUpBtn?.setOnClickListener {
            val email = binding.userText.text.toString().trim()
            val password = binding.userPass.text.toString().trim()

            if ((email.isNullOrEmpty()) || (password.isNullOrEmpty())) {
                Toast.makeText(
                    baseContext,
                    "Fields should not be empty.",
                    Toast.LENGTH_SHORT,
                ).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signupWithEmail:success")
                            Toast.makeText(
                                baseContext,
                                "Signed up successfully!.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            binding.txInfos?.text = task.exception?.localizedMessage
//                            val user = auth.currentUser
//                            val intent = Intent(this, HomeScreenActivity::class.java)
//                            startActivity(intent)
//                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signupWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Sign up failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            binding.txInfos?.text = task.exception?.localizedMessage
                        }
                    }
            }


        }


        binding.loginBtn.setOnClickListener {
            val email = binding.userText.text.toString().trim()
            val password = binding.userPass.text.toString().trim()

            if ((email.isNullOrEmpty()) || (password.isNullOrEmpty())) {
                Toast.makeText(
                    baseContext,
                    "Fields should not be empty.",
                    Toast.LENGTH_SHORT,
                ).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            val intent = Intent(this, HomeScreenActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            binding.txInfos?.text = task.exception?.localizedMessage
                        }
                    }
            }


        }




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
//                    updateUI(user)
                    Toast.makeText(this, "Success ${user?.email}", Toast.LENGTH_LONG).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        this,
                        "Failure ${task.exception.toString()}",
                        Toast.LENGTH_LONG
                    ).show()
                    //                    updateUI(null)
                }
            }
    }

}

