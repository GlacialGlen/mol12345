package com.example.mol12345

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Fragment02: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val gallerySpanCount = 3
    private var imageList = mutableListOf<GalleryListData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView: View = inflater.inflate(R.layout.fragment_02, container, false) ?: return null

        recyclerView = fragmentView.findViewById(R.id.gallery_recycler_view)

        val galleryAdapter = GalleryAdapter()
        if (imageList.isEmpty()) {
            for (i in 1..21) {
                imageList.add(GalleryListData(i))
            }
        }
        galleryAdapter.imageList = imageList
        recyclerView.adapter = galleryAdapter

        val manager = GridLayoutManager(activity, gallerySpanCount)
        recyclerView.layoutManager = manager

        return fragmentView
    }

    fun collectImages(): MutableList<GalleryListData> {
        var imageList: MutableList<GalleryListData> = mutableListOf()



        return imageList
    }
}