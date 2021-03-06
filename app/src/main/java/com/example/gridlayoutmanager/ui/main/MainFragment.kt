package com.example.gridlayoutmanager.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.example.gridlayoutmanager.R

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val layoutManager = GridLayoutManager(context, 2,  RecyclerView.VERTICAL, false)

        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when {
                    LayoutPosition.Large.position.contains(position) -> {
                        ViewSpan.LARGE.span
                    }
                    LayoutPosition.Grid.position.contains(position) -> {
                       ViewSpan.GRID.span
                    }
                    else -> {
                        0
                    }
                }
            }
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        val adapter = RecyclerViewAdapter(viewModel.listItems)
        recyclerView.adapter = adapter
    }
}