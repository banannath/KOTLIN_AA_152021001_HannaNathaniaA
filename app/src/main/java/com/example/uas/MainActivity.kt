package com.example.uas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(fHome())

        // Ambil email pengguna dari Firebase
        val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email
        Log.d("Emailku", currentUserEmail.toString())
        var bottomnavmenu = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomnavmenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bot_menu_home -> {
                    loadFragment(fHome())
                    true
                }

                R.id.bot_menu_favorite -> {
                    val favoriteFragment = fFavorite().apply {
                        arguments = Bundle().apply {
                            putString("userEmail", currentUserEmail)
                        }
                    }
                    loadFragment(favoriteFragment)
                    true
                }

                R.id.bot_menu_profile -> {
                    // Kirim email pengguna ke Fragment Profile
                    val profileFragment = fProfile().apply {
                        arguments = Bundle().apply {
                            putString("userEmail", currentUserEmail)
                        }
                    }
                    loadFragment(profileFragment)
                    true
                }

                else -> false
            }
        }

        var firebaseAuth = FirebaseAuth.getInstance()
        var topmenu = findViewById<androidx.appcompat.widget.Toolbar>(R.id.top_toolbar)
        setSupportActionBar(topmenu)
        topmenu.setOnMenuItemClickListener  { item ->
            when (item.itemId) {

                R.id.logout -> {
                    logout()
                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                    true
                }

                else -> false
            }
        }
    }

    fun logout(){
        var firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}
