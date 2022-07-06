package com.dapascript.brita.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dapascript.brita.data.NewsRepository

/**
 * @Author: Luthfi Daffa Prabowo
 * @Date: 06/07/2022 15.00
 */
class ViewModelFactory(private val newsRepository: NewsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> NewsViewModel(newsRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}