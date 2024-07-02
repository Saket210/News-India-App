package com.example.newsIndia.domain.usecases.news

import com.example.newsIndia.domain.model.Article
import com.example.newsIndia.domain.repository.NewsRepository

class SelectedArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectedArticle(url)
    }
}