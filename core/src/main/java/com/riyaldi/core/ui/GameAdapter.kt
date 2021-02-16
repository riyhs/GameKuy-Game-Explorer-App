package com.riyaldi.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
                Glide.with(itemView.context)
                        .load(data.bgImage)
                        .into(ivPoster)
                tvJudul.text = data.name
                tvDesc.text = data.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}