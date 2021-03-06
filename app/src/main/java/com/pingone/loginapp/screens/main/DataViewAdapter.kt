package com.pingone.loginapp.screens.main

import androidx.recyclerview.widget.RecyclerView

import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.pingone.loginapp.R

class DataViewAdapter(private var data: List<Pair<String, String>>, private var inflater: LayoutInflater?) :
    RecyclerView.Adapter<DataViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater!!.inflate(R.layout.recyclerview_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = data[position]
        holder.dataText.text = holder.itemView.context.getString(R.string.string_id, data.first)
        holder.valueText.text = data.second
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var dataText: TextView = itemView.findViewById(R.id.data_name)
        internal var valueText: TextView = itemView.findViewById(R.id.data_value)

    }
}