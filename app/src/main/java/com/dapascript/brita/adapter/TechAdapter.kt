package com.dapascript.brita.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dapascript.brita.data.model.ArticlesItem
import com.dapascript.brita.databinding.ListItemBinding
import com.dapascript.brita.utils.Helper

/**
 * @Author: Luthfi Daffa Prabowo
 * @Date: 06/07/2022 06.16
 */
class TechAdapter : RecyclerView.Adapter<TechAdapter.NewsViewHolder>() {

    private val techList: ArrayList<ArticlesItem> = ArrayList()

    fun addList(items: ArrayList<ArticlesItem>) {
        techList.clear()
        techList.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = techList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).let {
            return NewsViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(techList[position])
    }

    inner class NewsViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArticlesItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(item.urlToImage)
                    .into(imgNews)

                tvTitleNews.text = item.title
                tvContentNews.text = item.description
                tvDateNews.text = item.publishedAt
            }

            itemView.setOnClickListener {
                Helper.moveToChrome(itemView.context, item.url)
            }
        }
    }
}