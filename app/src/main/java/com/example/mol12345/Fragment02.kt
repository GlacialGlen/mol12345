package com.example.mol12345

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.AccessDeniedException

class Fragment02: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val gallerySpanCount = 3
    private var imageList = mutableListOf<GalleryListData>()
    private lateinit var noImageImageView: ImageView
    private lateinit var noImageTextView: TextView
    private lateinit var galleryAdapter: GalleryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView: View = inflater.inflate(R.layout.fragment_02, container, false) ?: return null

        noImageImageView = fragmentView.findViewById(R.id.no_image_image)
        noImageTextView = fragmentView.findViewById(R.id.no_image_text)
        recyclerView = fragmentView.findViewById(R.id.gallery_recycler_view)

        galleryAdapter = GalleryAdapter()

        collectImages()
        recyclerView.adapter = galleryAdapter

        val manager = GridLayoutManager(activity, gallerySpanCount)
        recyclerView.layoutManager = manager

        return fragmentView
    }

    override fun onResume() {
        collectImages()
        galleryAdapter.notifyDataSetChanged()
        super.onResume()
    }

    private fun collectImages() {
        imageList.clear()
        val validFileExtensions = listOf("png", "jpg", "jpeg", "gif")
        val uri = Uri.parse("file:///" + Environment.getExternalStorageDirectory())
        val paths = Paths.get(uri.path!!)
        var num = 0
        val files = Files.walk(paths, 4)
            .filter(Files::isRegularFile)
            .filter { x ->
                validFileExtensions.fold(false) { acc, ext ->
                    acc || x.fileName.toString().endsWith(ext)
                }
            }

        val it = files.iterator()
        while (true) {
            try {
                val boolV = it.hasNext()
                if (!boolV) {
                    break
                }
                imageList.add(GalleryListData(Uri.parse(it.next().toUri().toString()), num))
                num += 1
            }
            catch (e: java.io.UncheckedIOException) {
                Log.d("SKIP", "Skip $e")
            }

        }


        if (imageList.isNotEmpty()) {
            noImageImageView.visibility = View.INVISIBLE
            noImageTextView.visibility = View.INVISIBLE
        }
        else {
            noImageImageView.visibility = View.VISIBLE
            noImageTextView.visibility = View.VISIBLE
        }

        galleryAdapter.imageList = imageList
    }
}