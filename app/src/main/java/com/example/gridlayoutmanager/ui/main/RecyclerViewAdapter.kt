package com.example.gridlayoutmanager.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gridlayoutmanager.R

enum class ViewType {
    FULL, GRID
}
class RecyclerViewAdapter(private val items: List<String>):

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        return when(viewType) {
            ViewType.FULL.ordinal -> {
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
       return when(position) {
           0 -> ViewType.FULL.ordinal
           else ->  ViewType.GRID.ordinal
       }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
           ViewType.FULL.ordinal -> {
               (holder as FullWidthViewHolder).textView.text = items[position]
           }
           ViewType.GRID.ordinal -> {
               (holder as HalfWidthViewHolder).textView.text = items[position]
           }
        }
    }

}