package com.example.newsIndia.domain.usecases.news

import com.example.newsIndia.domain.model.Article
import com.example.newsIndia.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticle()
    }
}