package com.example.myapplication.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.DataUsage
import com.example.myapplication.R
import kotlinx.android.synthetic.main.row_data_usage.view.*


class DataUsageAdapter(
        private val dataUsage: List<DataUsage>
) : RecyclerView.Adapter<DataUsageAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return dataUsage.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_data_usage, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataquantity.text = dataUsage[position].dataQuantity
        holder.cost.text = dataUsage[position].cost
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dataquantity = view.quantity!!
        val cost = view.cost!!
    }
}