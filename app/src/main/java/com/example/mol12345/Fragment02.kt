package com.example.mol12345

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.nio.file.Files
import java.nio.file.Paths

class Fragment02: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val gallerySpanCount = 3
    private var imageList = mutableListOf<GalleryListData>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView: View = inflater.inflate(R.layout.fragment_02, container, false) ?: return null

        recyclerView = fragmentView.findViewById(R.id.gallery_recycler_view)

        val galleryAdapter = GalleryAdapter()
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
        galleryAdapter.imageList = imageList
        recyclerView.adapter = galleryAdapter

        val manager = GridLayoutManager(activity, gallerySpanCount)
        recyclerView.layoutManager = manager

        return fragmentView
    }

}