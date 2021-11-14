package com.example.gridlayoutmanager.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gridlayoutmanager.R

enum class ViewSpan(val span: Int) {
    FULL(span = 2), GRID(span = 1)
}

enum class LayoutPosition(val position: MutableList<Int>) {
    Large (position = mutableListOf(0, 5, 6, 7)),
    Grid (position = mutableListOf(1, 2, 3, 4))
}
class RecyclerViewAdapter(private val items: List<String>):

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        return when(viewType) {
            ViewSpan.FULL.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.full_width, parent, false)
                FullWidthViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.half_width, parent, false)
                HalfWidthViewHolder(view)
            }
        }

    }

    override fun getItemCount(): Int = items.size

    class FullWidthViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.full_width_text_view)
    }

    class HalfWidthViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.half_width_text_view)
    }

    override fun getItemViewType(position: Int): Int {

        return when {
            LayoutPosition.Large.position.contains(position) -> {
                ViewSpan.FULL.ordinal
            }
            LayoutPosition.Grid.position.contains(position) -> {
                ViewSpan.GRID.ordinal
            }
            else -> {
                0
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
           ViewSpan.FULL.ordinal -> {
               (holder as FullWidthViewHolder).textView.text = items[position]
           }
           ViewSpan.GRID.ordinal -> {
               (holder as HalfWidthViewHolder).textView.text = items[position]
           }
        }
    }

}