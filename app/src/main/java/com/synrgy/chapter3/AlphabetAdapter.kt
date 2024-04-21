package com.synrgy.chapter3

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController


class AlphabetAdapter(private val onItemClick: (String) -> Unit)
    : RecyclerView.Adapter<AlphabetAdapter.AlphabetViewHolder>() {

    private val list = ('A').rangeTo('Z').toList()

    inner class AlphabetViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.btnItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlphabetViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)

        return AlphabetViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AlphabetViewHolder, position: Int) {
        val item = list[position]
        holder.button.text = item.toString()

        holder.button.setOnClickListener {
            onItemClick(item.toString())
        }
    }
}
