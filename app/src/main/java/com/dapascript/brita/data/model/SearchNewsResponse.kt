package com.dapascript.brita.data.model

import com.squareup.moshi.Json

/**
 * @Author: Luthfi Daffa Prabowo
 * @Date: 06/07/2022 16.56
 */
data class SearchNewsResponse(

    @Json(name = "totalResults")
    val totalResults: Int,

    @Json(name = "articles")
    val articles: List<ArticlesItemSearch>,

    @Json(name = "status")
    val status: String
)

data class ArticlesItemSearch(

    @Json(name = "publishedAt")
    val publishedAt: String,

    @Json(name = "author")
    val author: String,

    @Json(name = "urlToImage")
    val urlToImage: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "title")
    val title: String,

    @Json(name = "url")
    val url: String,

    @Json(name = "content")
    val content: String
)