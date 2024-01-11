package com.example.uas

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uas.databinding.ListItemBinding

class FavoriteAlbumAdapter : ListAdapter<FavouriteAlbumModelItem, FavoriteAlbumAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnClickListener

    inner class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(albumItem: FavouriteAlbumModelItem) {
            // Set the album name if it's not null
            albumItem.albumName?.let { binding.name.text = it }
        }

        fun callCard(albumItem: FavouriteAlbumModelItem) {
            val intent = Intent(binding.root.context, DetailsAlbumActivity::class.java)
            intent.putExtra("albumName", albumItem.albumName)
            intent.putExtra("year", albumItem.year)
            intent.putExtra("artistNames", albumItem.artists)
            // Assuming your FavouriteAlbumModelItem class has a "uri" field, modify accordingly
            binding.root.context.startActivity(intent)
            onItemClickCallback.onClickCard(albumItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val albumItem = getItem(position)
        holder.bind(albumItem)
        holder.itemView.setOnClickListener {
            holder.callCard(albumItem)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavouriteAlbumModelItem>() {
            override fun areItemsTheSame(
                oldItem: FavouriteAlbumModelItem,
                newItem: FavouriteAlbumModelItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavouriteAlbumModelItem,
                newItem: FavouriteAlbumModelItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setOnClickCallback(callback: OnClickListener) {
        this.onItemClickCallback = callback
    }

    interface OnClickListener {
        fun onClickCard(albumItem: FavouriteAlbumModelItem)
    }
}
