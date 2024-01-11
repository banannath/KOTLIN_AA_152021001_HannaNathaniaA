package com.example.uas

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uas.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest

class LoginActivity : AppCompatActivity() {
    lateinit var edt_email: EditText
    lateinit var edt_password: EditText
    lateinit var login: Button
    lateinit var register: Button
    lateinit var progressDialog : ProgressDialog
    var firebaseAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edt_password = findViewById(R.id.edt_pass)
        edt_email = findViewById(R.id.edt_email)
        login = findViewById(R.id.btn_login)
        register = findViewById(R.id.btn_register_page)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Logging")
        progressDialog.setMessage("Silakan tunggu ...")

        register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        login.setOnClickListener {
            if (edt_email.text.isNotEmpty() && edt_password.text.isNotEmpty()) {
                processLogin()
            } else {
                Toast.makeText(this, "Isi email dan password terlebih dahulu", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun processLogin() {
        val pass = edt_password.text.toString()
        val email = edt_email.text.toString()

        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener {
                progressDialog.dismiss()
            }
    }
}