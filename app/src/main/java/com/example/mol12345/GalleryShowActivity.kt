package com.example.mol12345

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GalleryShowActivity: AppCompatActivity() {
    private lateinit var galleryLargeImage: ImageView
    private lateinit var galleryLargeTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_large)

        galleryLargeImage = findViewById(R.id.gallery_large_image)
        galleryLargeTitle = findViewById(R.id.gallery_large_title)

        val imageResource = intent.extras
        if (imageResource == null) {
            galleryLargeImage.setImageResource(R.drawable.image_default)
            galleryLargeTitle.text = getString(R.string.no_image)
        }
        else {
            galleryLargeImage.setImageResource(imageResource.getInt("image_resource"))
            galleryLargeTitle.text = "Image ${imageResource.getInt("image_number")}"
        }
    }
}