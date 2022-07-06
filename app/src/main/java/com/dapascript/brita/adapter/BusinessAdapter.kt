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
 * @Date: 06/07/2022 15.49
 */
class BusinessAdapter : RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder>() {

    private val businessList: ArrayList<ArticlesItem> = ArrayList()

    fun addList(list: List<ArticlesItem>) {
        businessList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        return BusinessViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        holder.bind(businessList[position])
    }

    override fun getItemCount(): Int = businessList.size

    inner class BusinessViewHolder(private val binding: ListItemBinding) :
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