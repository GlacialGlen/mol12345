package com.example.molweek1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView

//
class GalleryAdapter: RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {
    var imageList = mutableListOf<GalleryListData>()

    inner class GalleryHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var idImageButton: ImageButton = itemView.findViewById(R.id.gallery_image)

        fun setImage(listData: GalleryListData) {   // for binding
            val context = idImageButton.context
            idImageButton.setImageURI(listData.image_uri)

            idImageButton.setOnClickListener {
                val intent = Intent(context, GalleryShowActivity::class.java)
                intent.putExtra("image_uri", listData.image_uri)
                intent.putExtra("image_index", listData.image_index)
                context.startActivity(intent)
            }
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
