package com.example.mol12345

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GalleryAdapter: RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {
    var imageList = mutableListOf<GalleryListData>()

    inner class GalleryHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var idImageView: ImageView = itemView.findViewById(R.id.gallery_image)

        fun setImage(listData: GalleryListData) {   // for binding
            val uri = "image_${listData.image_number}"
            val context = idImageView.context
            val imageResource = context.resources.getIdentifier(uri, "drawable", context.packageName)
            idImageView.setImageResource(imageResource)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.gallery_list_item, parent, false)

        return GalleryHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        val data = imageList[position]
        holder.setImage(data)
    }
}

