package com.riyaldi.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.riyaldi.core.R
import com.riyaldi.core.databinding.ItemListLayoutBinding
import com.riyaldi.core.domain.model.Game

class GameAdapter: RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    private var listData = ArrayList<Game>()
    var onItemClick: ((Game) -> Unit)? = null

    fun setData(newListData: List<Game>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder =
        GameViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_layout, parent, false))

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListLayoutBinding.bind(itemView)
        fun bind(data: Game){
            with(binding) {
                val options = RequestOptions()
                        .override(binding.ivItemPoster.width, binding.ivItemPoster.height)

                Glide.with(itemView.context)
                        .load(data.bgImage)
                        .placeholder(R.drawable.placeholder)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(options)
                        .into(ivItemPoster)

                tvItemJudul.text = data.name
                tvItemGenres.text = data.genres
                tvItemReleased.text = data.platforms
                tvItemMetascore.text = data.metaScore.toString()

                when {
                    data.metaScore < 50 -> {
                        metascore.background = getDrawable(itemView.context, R.drawable.custom_bg_metascore_red)
                        tvItemMetascore.setTextColor(getColor(itemView.context, R.color.red))
                    }
                    data.metaScore < 75 -> {
                        metascore.background = getDrawable(itemView.context, R.drawable.custom_bg_metascore_yellow)
                        tvItemMetascore.setTextColor(getColor(itemView.context, R.color.yellow))
                    }
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}