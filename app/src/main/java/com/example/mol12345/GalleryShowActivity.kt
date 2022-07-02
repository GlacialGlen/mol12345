package com.example.mol12345

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class GalleryShowActivity: AppCompatActivity() {

    private var imageList = mutableListOf<GalleryListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_main)

        val viewPager: ViewPager2 = findViewById(R.id.gallery_scroll_viewpager)
        val galleryShowAdapter = GalleryShowAdapter(intent)
        if (imageList.isEmpty()) {
            for (i in 1..21) {
                imageList.add(GalleryListData(i))
            }
        }
        galleryShowAdapter.imageList = imageList
        viewPager.adapter = galleryShowAdapter
        val extras = intent.extras
        if (extras == null) {
            viewPager.currentItem = 0
        } else {
            viewPager.currentItem = extras.getInt("image_number") - 1
        }
    }
}