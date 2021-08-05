package com.example.homework12_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class PhotoView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

        var dataPath = intent.getStringExtra("Path").toString()
        var dataDescription = intent.getStringExtra("Description").toString()

        val Image = findViewById<ImageView>(R.id.PhotoView)
        val text = findViewById<TextView>(R.id.textV)

        text.text = dataDescription
        Glide.with(this).load(dataPath).into(Image);
    }
}