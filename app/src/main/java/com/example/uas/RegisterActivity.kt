package com.example.uas

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest

class RegisterActivity : AppCompatActivity() {
    lateinit var edt_username : EditText
    lateinit var edt_email : EditText
    lateinit var edt_password : EditText
    lateinit var register : Button
    lateinit var back : Button
    lateinit var progressDialog : ProgressDialog
    var firebaseAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser!=null){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edt_username = findViewById(R.id.edt_uname_reg)
        edt_password = findViewById(R.id.edt_pass_reg)
        edt_email = findViewById(R.id.edt_email_reg)
        register = findViewById(R.id.btn_register)
        back = findViewById(R.id.btn_back_login)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Logging")
        progressDialog.setMessage("Silakan tunggu ...")

        back.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        register.setOnClickListener{
            if (edt_email.text.isNotEmpty() && edt_username.text.isNotEmpty() && edt_password.text.isNotEmpty()){
                processRegister(edt_email.toString(), edt_password.toString())
            } else{
                Toast.makeText(this, "Isi dulu semua data", LENGTH_SHORT).show()
            }
        }

    }

    fun processRegister(email: String, password: String) {
        val uname = edt_username.text.toString()
        val pass = edt_password.text.toString()
        val userEmail = edt_email.text.toString()// Use a different variable name to avoid conflict

        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(userEmail, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Registrasi berhasil!!!", Toast.LENGTH_SHORT).show()
                    val user = task.result?.user
                    if (user != null) {
                        val userUpdateProfile = userProfileChangeRequest {
                            displayName = uname
                        }
                        user.updateProfile(userUpdateProfile)
                            .addOnCompleteListener {
                                progressDialog.dismiss()
                                startActivity(Intent(this, MainActivity::class.java))
                            }
                            .addOnFailureListener { error2 ->
                                Toast.makeText(this, error2.localizedMessage, LENGTH_SHORT).show()
                            }
                    }
                } else {
                    progressDialog.dismiss()
                    Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}