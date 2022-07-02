package com.example.mol12345

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GalleryShowAdapter(intent: Intent): RecyclerView.Adapter<GalleryShowAdapter.GalleryShowHolder>() {
    var imageList = mutableListOf<GalleryListData>()
    var intent = intent

    inner class GalleryShowHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var idImage: ImageView = itemView.findViewById(R.id.gallery_large_image)
        private var idTitle: TextView = itemView.findViewById(R.id.gallery_large_title)

        fun setImage(listData: GalleryListData) {   // for binding
            val uri = "image_${listData.image_number}"
//            val context = idImageView.context
            val context = idImage.context
            val imageResource = context.resources.getIdentifier(uri, "drawable", context.packageName)
//            idImageView.setImageResource(imageResource)
            idImage.setImageResource(imageResource)
            idTitle.text = "Image ${listData.image_number}"


//            galleryLargeImage = itemView.findViewById(R.id.gallery_large_image)
//            galleryLargeTitle = itemView.findViewById(R.id.gallery_large_title)
//
//            val extras = intent.extras
//            if (extras == null) {
//                idImage.setImageResource(R.drawable.image_default)
//                idTitle.text = context.getString(R.string.no_image)
//            }
//            else {
//                idImage.setImageResource(extras.getInt("image_resource"))
//                idTitle.text = "Image ${extras.getInt("image_number")}"
//            }
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