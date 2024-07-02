package com.example.newsIndia.domain.repository

import androidx.paging.PagingData
import com.example.newsIndia.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String,sources: List<String>): Flow<PagingData<Article>>
    suspend fun upsertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    fun selectArticle(): Flow<List<Article>>
    suspend fun selectedArticle(url: String): Article?
}