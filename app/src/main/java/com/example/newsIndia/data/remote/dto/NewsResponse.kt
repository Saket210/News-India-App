package com.example.newsIndia.data.remote.dto

import com.example.newsIndia.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
