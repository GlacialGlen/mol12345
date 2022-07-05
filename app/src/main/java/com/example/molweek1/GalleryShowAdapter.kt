package com.example.molweek1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GalleryShowAdapter: RecyclerView.Adapter<GalleryShowAdapter.GalleryShowHolder>() {
    var imageList = mutableListOf<GalleryListData>()

    inner class GalleryShowHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var idImage: ImageView = itemView.findViewById(R.id.gallery_large_image)
        private var idTitle: TextView = itemView.findViewById(R.id.gallery_large_title)

        fun setImage(listData: GalleryListData) {   // for binding
            idImage.setImageURI(listData.image_uri)

            val fullPath = listData.image_uri.toString()
            var index = 0
            var chardex = 0
            for (digit in fullPath) {
                if (digit == '/' && chardex < 6) {
                    chardex += 1
                }
                if (chardex == 6) {
                    break
                }
                index += 1
            }
            idTitle.text = fullPath.substring(index)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryShowHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_gallery_large, parent, false)

        return GalleryShowHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: GalleryShowHolder, position: Int) {
        val data = imageList[position]
        holder.setImage(data)
    }
}