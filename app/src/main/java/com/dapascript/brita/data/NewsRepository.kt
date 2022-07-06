package com.dapascript.brita.data

import com.dapascript.brita.data.network.ApiService

/**
 * @Author: Luthfi Daffa Prabowo
 * @Date: 06/07/2022 05.54
 */
class NewsRepository(private val apiService: ApiService) {

    suspend fun getTechNews() = apiService.getTechNews()
    suspend fun getBusinessNews() = apiService.getBusinessNews()
    suspend fun getNewsSearch(q: String) = apiService.getNewsSearch(q)
}