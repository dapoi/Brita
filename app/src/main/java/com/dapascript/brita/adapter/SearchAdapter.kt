package com.dapascript.brita.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dapascript.brita.data.model.ArticlesItemSearch
import com.dapascript.brita.databinding.ListItemBinding
import com.dapascript.brita.utils.Helper

/**
 * @Author: Luthfi Daffa Prabowo
 * @Date: 06/07/2022 17.00
 */
class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val searchList: ArrayList<ArticlesItemSearch> = ArrayList()

    fun addList(list: List<ArticlesItemSearch>) {
        searchList.clear()
        searchList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchList[position])
    }

    override fun getItemCount(): Int = searchList.size

    class SearchViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArticlesItemSearch) {
            binding.apply {
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