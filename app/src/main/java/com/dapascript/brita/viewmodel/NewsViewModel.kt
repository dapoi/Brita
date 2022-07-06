package com.dapascript.brita.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dapascript.brita.data.NewsRepository
import com.dapascript.brita.data.model.ArticlesItem
import com.dapascript.brita.data.model.ArticlesItemSearch
import com.dapascript.brita.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @Author: Luthfi Daffa Prabowo
 * @Date: 06/07/2022 05.56
 */
class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    private val _techNews = MutableLiveData<Resource<List<ArticlesItem>>>()
    val techNews: LiveData<Resource<List<ArticlesItem>>>
        get() = _techNews

    private val _businessNews = MutableLiveData<Resource<List<ArticlesItem>>>()
    val businessNews: LiveData<Resource<List<ArticlesItem>>>
        get() = _businessNews

    private val _searchNews = MutableLiveData<Resource<List<ArticlesItemSearch>>>()
    val searchNews: LiveData<Resource<List<ArticlesItemSearch>>>
        get() = _searchNews

    fun getTechNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _techNews.postValue(Resource.Loading())
            try {
                _techNews.postValue(Resource.Success(newsRepository.getTechNews().articles))
            } catch (e: Exception) {
                _techNews.postValue(Resource.Error(e.message ?: "Something went wrong"))
            }
        }
    }

    fun getBusinessNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _businessNews.postValue(Resource.Loading())
            try {
                _businessNews.postValue(Resource.Success(newsRepository.getBusinessNews().articles))
            } catch (e: Exception) {
                _businessNews.postValue(Resource.Error(e.message ?: "Something went wrong"))
            }
        }
    }

    fun getNewsSearch(q: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchNews.postValue(Resource.Loading())
            try {
                _searchNews.postValue(Resource.Success(newsRepository.getNewsSearch(q).articles))
            } catch (e: Exception) {
                _searchNews.postValue(Resource.Error(e.message ?: "Something went wrong"))
            }
        }
    }
}