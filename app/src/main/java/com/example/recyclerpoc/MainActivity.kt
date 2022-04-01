package com.example.recyclerpoc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val data : ArrayList<Details> = arrayListOf()
    private var pos : Int = -1
    private lateinit var animatedColor: AnimatedColor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recycler)

        recyclerview.layoutManager = LinearLayoutManager(this)

        for (i in 1..30) {
            data.add(Details("Item $i" , false))
        }

        animatedColor = AnimatedColor(
            ContextCompat.getColor(this, R.color.bottomColor),
            ContextCompat.getColor(this, R.color.topColor)
        )

        val recyclerAdapter = RecyclerAdapter(data)
        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = recyclerAdapter
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        recyclerView.forEachVisibleHolder { holder: RecyclerView.ViewHolder ->
                            changeDrawableColor(holder as RecyclerAdapter.ItemViewHolder)
                        }
                    }
                })
        }
    }

    private fun changeDrawableColor(holder: RecyclerAdapter.ItemViewHolder) {
        holder.itemView.post {
            ContextCompat.getDrawable(this, R.drawable.bg_round_corner_outgoing)
                ?.let { incomingBgDrawable ->
                    val color = animatedColor.with(getFloatRange(holder.itemView))

                    if (getFloatRange(holder.itemView) >= 0.6){
                        holder.name.alpha = 0.4f
                    }
                    if (getFloatRange(holder.itemView) >= 0.7){
                        holder.name.alpha = 0.3f
                    }
                    if (getFloatRange(holder.itemView) >= 0.8){
                        holder.name.alpha = 0.2f
                    }
                    if (getFloatRange(holder.itemView) >= 0.9){
                        holder.name.alpha = 0.1f
                    }
                    if (getFloatRange(holder.itemView) >= 1){
                        holder.name.alpha = 0f
                    }
                    if (getFloatRange(holder.itemView) <= 0.4){
                        holder.name.alpha = 1f
                    }
                    incomingBgDrawable.updateTint(color)
                    holder.container.background = incomingBgDrawable
                }
        }
    }


    private fun getFloatRange(view: View): Float {
        return 1f - (view.absY() / resources.displayMetrics.heightPixels.toFloat())
    }
}