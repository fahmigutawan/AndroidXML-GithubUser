package com.example.githubuser.util

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class AppRecyclerViewAdapter<Binding : ViewBinding, ItemType> :
    RecyclerView.Adapter<AppRecyclerViewAdapter<Binding, ItemType>.AppRecyclerViewHolder>() {

    abstract val itemHandler: (binding: Binding, type: ItemType) -> Unit
    abstract val inflateViewBinding: (viewGroup: ViewGroup) -> Binding

    private val items = ArrayList<ItemType>().toMutableList()

    inner class AppRecyclerViewHolder(
        private val binding: Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemType) {
            itemHandler(binding, item)
        }
    }

    open fun updateList(list: List<ItemType>){
        items.clear()
        items.addAll(list)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppRecyclerViewHolder = AppRecyclerViewHolder(inflateViewBinding(parent))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: AppRecyclerViewHolder,
        position: Int
    ) = holder.bind(items[position])
}