package com.example.mvvm_structure_room.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}