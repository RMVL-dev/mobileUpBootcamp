package com.example.bootcamp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.green
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcamp.R
import com.example.bootcamp.databinding.ViewHolderBinding
import com.squareup.picasso.Picasso

class CryptoAdapter:ListAdapter<CryptoItem, CryptoAdapter.Holder> (Comparator()){

    class Holder (view: View): RecyclerView.ViewHolder(view){
        val binding = ViewHolderBinding.bind(view)
        fun bind(item: CryptoItem) = with(binding){
            tvCryptoName.text = item.name
            tvCryptoSymbol.text = item.symbol
            tvCurrentPrice.text = item.currentPrice
            tvCurrentPricePercent.text = item.priceChangePercent
            Picasso.get().load(item.imageUrl).into(ivCryptoImage)
            if(item.priceChangePercent[0] == '-')
                tvCurrentPricePercent.setTextColor(Color.RED)
            else tvCurrentPricePercent.setTextColor(Color.GREEN)
        }
    }

    class Comparator : DiffUtil.ItemCallback<CryptoItem>(){
        override fun areItemsTheSame(oldItem: CryptoItem, newItem: CryptoItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CryptoItem, newItem: CryptoItem): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder,
            parent,
            false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

}