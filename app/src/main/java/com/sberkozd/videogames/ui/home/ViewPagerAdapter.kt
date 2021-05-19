package com.sberkozd.videogames.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sberkozd.videogames.data.models.Game
import com.sberkozd.videogames.databinding.ItemViewpagerBinding


class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private val items: MutableList<Game> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ItemViewpagerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    fun setItems(items: List<Game>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.binding.apply {
            this.itemViewPagerIvPhoto.load(items[position].background_image)
            this.root.setOnClickListener {
                Toast.makeText(it.context, items[position].name, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount() = items.size

    class ViewPagerViewHolder(val binding: ItemViewpagerBinding) : RecyclerView.ViewHolder(binding.root)
}
