package com.example.mol12345

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import java.nio.file.Files
import java.nio.file.Paths

class GalleryShowActivity: AppCompatActivity() {

    private var imageList = mutableListOf<GalleryListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_main)
        supportActionBar?.hide()
        val viewPager: ViewPager2 = findViewById(R.id.gallery_scroll_viewpager)
        val galleryShowAdapter = GalleryShowAdapter()
        if (imageList.isEmpty()) {
            val validFileExtensions = listOf("png", "jpg", "jpeg", "gif")
            val uri = Uri.parse("file:///" + Environment.getExternalStorageDirectory())
            val paths = Paths.get(uri.path!!)
            var num = 0
            Files.walk(paths, 2)
                .filter(Files::isRegularFile)
                .filter { x ->
                    validFileExtensions.fold(false) { acc, ext ->
                        acc || x.fileName.toString().endsWith(ext)
                    }
                }
                .forEach {
                    Log.d("IMAGE", "$it")

                    imageList.add(GalleryListData(Uri.parse(it.toUri().toString()), num))
                    num += 1
                }
        }
        galleryShowAdapter.imageList = imageList
        viewPager.adapter = galleryShowAdapter
        val extras = intent.extras
        if (extras == null) {
            viewPager.currentItem = 0
        } else {
            viewPager.currentItem = extras.getInt("image_index")
        }
    }
}