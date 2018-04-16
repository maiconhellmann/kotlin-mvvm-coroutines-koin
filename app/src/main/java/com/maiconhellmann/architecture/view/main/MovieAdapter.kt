package com.maiconhellmann.architecture.view.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maiconhellmann.architecture.R
import com.maiconhellmann.architecture.data.model.Movie
import kotlinx.android.synthetic.main.row_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.RecyclerViewAdapterViewHolder>() {

    var dataList: List<Movie> = emptyList<Movie>().toMutableList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_movie, parent, false)
        return RecyclerViewAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapterViewHolder, position: Int) {
        val data = dataList[position]

        holder.textViewTitle.text = data.title
    }

    override fun getItemCount(): Int = dataList.size

    inner class RecyclerViewAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitle = itemView.textViewTitle
    }
}