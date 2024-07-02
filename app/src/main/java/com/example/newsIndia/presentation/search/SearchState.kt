package com.example.newsIndia.presentation.search

import androidx.paging.PagingData
import com.example.newsIndia.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val article: Flow<PagingData<Article>>? = null
)