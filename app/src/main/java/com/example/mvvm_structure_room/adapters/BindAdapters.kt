package com.example.mvvm_structure_room.adapters

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mvvm_structure_room.R

@BindingAdapter("imageFromUri")
fun ImageView.imageFromUri(uri: String) {
    val uriImage: Uri = Uri.parse(uri)
    Glide.with(this.context).load(uriImage).placeholder(R.drawable.ic_person).into(this)
}