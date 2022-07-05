package com.example.molweek1

import android.net.Uri

//data class GalleryListData(var image_number: Int)
//data class GalleryListData(val image_name: String, val image_path: String)
data class GalleryListData(val image_uri: Uri, val image_index: Int)