package com.example.newsIndia.presentation.bookmark

import com.example.newsIndia.domain.model.Article

data class BookMarkState(
    val article: List<Article> = emptyList()
)
