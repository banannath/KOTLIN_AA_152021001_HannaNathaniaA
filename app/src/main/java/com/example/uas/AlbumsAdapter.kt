package com.example.uas

import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uas.databinding.ListItemBinding
import com.squareup.picasso.Picasso

//import com.miftah.mysubmissionintermediate.core.data.source.remote.response.ListStoryItem
//import com.miftah.mysubmissionintermediate.databinding.CardStoryBinding

class AlbumsAdapter : ListAdapter<ItemsItem, AlbumsAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnClickListener

    inner class ViewHolder(val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: ItemsItem) {
            // Set the name if it's not null
            listItem.data?.name?.let { binding.name.text = it }

            // Load cover art URL if it's not null
            val coverArt = listItem.data?.coverArt
            if (coverArt != null) {
                val sources = coverArt.sources
                if (sources != null && sources.isNotEmpty()) {
                    val url = sources[0]?.url
                    if (url != null) {
                        // Load cover art using Picasso
                        Picasso.get().load(url).into(binding.coverArt)
                    } else {
                        // Handle case where URL is null
                        // You may want to set a placeholder or handle it in another way
                    }
                } else {
                    // Handle case where sources is null or empty
                    // You may want to set a placeholder or handle it in another way
                }
            } else {
                // Handle case where coverArt is null
                // You may want to set a placeholder or handle it in another way
            }
        }


        fun callCard(listItem: ItemsItem) {
            val intent = Intent(binding.root.context, DetailsAlbumActivity::class.java)
            intent.putExtra("albumName", listItem.data?.name)
            intent.putExtra("coverArt", listItem.data?.coverArt?.sources?.get(0)?.url)
            intent.putExtra("year", listItem.data?.date?.year)
            val artistNames = listItem.data?.artists?.items?.map { it?.profile?.name ?: "" }
            intent.putExtra("artistNames", artistNames?.toTypedArray())
            intent.putExtra("uri", listItem.data?.uri)
            startActivity(binding.root.context, intent, null)
            onItemClickCallback.onClickCard(listItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listAlbumItem = getItem(position)
        holder.bind(listAlbumItem)
        holder.itemView.setOnClickListener {
            holder.callCard(listAlbumItem)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(
                oldItem: ItemsItem,
                newItem: ItemsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ItemsItem,
                newItem: ItemsItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    fun setOnClickCallback(call: OnClickListener) {
        this.onItemClickCallback = call
    }

    interface OnClickListener {
        fun onClickCard(albumItem: ItemsItem)
    }
}