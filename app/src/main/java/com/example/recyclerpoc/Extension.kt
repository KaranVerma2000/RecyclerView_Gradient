package com.example.recyclerpoc

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView

//This function returns the y-cord of the view..
fun View.absY(): Float {
    val location = IntArray(2)
    getLocationOnScreen(location)
    return (location[1].toFloat() - height.toFloat())
}

fun Drawable.updateTint(color: Int) {
    DrawableCompat.wrap(this)?.let {
        DrawableCompat.setTint(it, color)
    }
}

inline fun <reified T : RecyclerView.ViewHolder> RecyclerView.forEachVisibleHolder(
    action: (T) -> Unit
) {
    for (i in 0 until childCount) {
        action(getChildViewHolder(getChildAt(i)) as T)
    }
}