package com.example.headsupprep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(var users : List<UsersItem>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = users[position]

        holder.itemView.apply {
            val isExpandeble: Boolean = user.expandeble
            tvName.text = user.name
            tvTaboo1.text = user.taboo1
            tvTaboo2.text = user.taboo2
            tvTaboo3.text = user.taboo3
            llItem.visibility = if(isExpandeble) View.VISIBLE
            else View.GONE

            llItem.setOnClickListener {
                val celebrity = users[position]
                celebrity.expandeble = !celebrity.expandeble
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int = users.size


}