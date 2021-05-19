package com.sberkozd.videogames.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sberkozd.videogames.data.models.Game
import com.sberkozd.videogames.databinding.ItemRecyclerviewBinding
import com.sberkozd.videogames.ui.favorite.FavoriteFragmentDirections

class GameListAdapter(private val isHome: Boolean) :
    RecyclerView.Adapter<GameListAdapter.ViewPagerViewHolder>() {

    private val items: MutableList<Game> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ItemRecyclerviewBinding
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
            this.itemRecyclerViewIvPhoto.load(items[position].background_image)
            this.itemRecyclerViewTvGameName.text = items[position].name
            this.itemRecyclerViewTvGameRating.text =
                items[position].rating.toString() + " / " + items[position].released
            this.root.setOnClickListener {
                if (isHome) {
                    it.findNavController()
                        .navigate(HomeFragmentDirections.actionHomeToDetail(items[position].id))
                } else {
                    it.findNavController()
                        .navigate(FavoriteFragmentDirections.actionHomeToDetail(items[position].id))
                }

            }
        }
    }

    override fun getItemCount() = items.size

    class ViewPagerViewHolder(val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
