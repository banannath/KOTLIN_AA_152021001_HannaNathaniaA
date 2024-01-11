package com.example.uas

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class DetailsAlbumActivity : AppCompatActivity() {
    lateinit var btn_back: Button
    lateinit var tv_album : TextView
    lateinit var tv_year : TextView
    lateinit var iv_album : ImageView
    lateinit var tv_artists : TextView
    lateinit var btn_uri : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_album)

        val albumName = intent.getStringExtra("albumName")
        tv_album = findViewById(R.id.album)
        tv_album.text = albumName

        val albumCoverArt = intent.getStringExtra("coverArt")
        val coverArtImageView = findViewById<ImageView>(R.id.image)
        Picasso.get().load(albumCoverArt).into(coverArtImageView)

        val albumYear = intent.getIntExtra("year", 2024)
        tv_year = findViewById(R.id.year)
        tv_year.text = albumYear.toString()

        val artists = intent.getStringArrayExtra("artistNames")
        tv_artists = findViewById(R.id.artist)
        tv_artists.text = artists?.joinToString(", ")


        btn_back = findViewById<Button>(R.id.back)
        btn_back.setOnClickListener {
            val intent = Intent(this@DetailsAlbumActivity, MainActivity::class.java)
            startActivity(intent)
        }

        val albumUri = intent.getStringExtra("uri")
        btn_uri = findViewById(R.id.uri)
        btn_uri.setOnClickListener {
            val uri = Uri.parse(albumUri)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }


    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}