package com.example.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.Call
import com.example.myapplication.R
import kotlinx.android.synthetic.main.row_call_usage.view.*
import kotlinx.android.synthetic.main.row_data_usage.view.*

class CallUsageAdapter(
        private val callUsage: List<Call>,
        private val context: Context
) : RecyclerView.Adapter<CallUsageAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return callUsage.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_call_usage, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contactName.text = callUsage[position].contactName.name
        holder.durationAndDate.text = callUsage[position].time + "  " + callUsage[position].callDuration
        holder.cost.text = callUsage[position].cost

        Glide.with(context)
                .load(callUsage[position].contactName.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.avatar)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar = view.avatar
        val contactName = view.contact
        val durationAndDate = view.durationDate
        val cost = view.durationDate
    }
}