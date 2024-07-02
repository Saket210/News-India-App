package com.example.newsIndia.domain.usecases.news

import com.example.newsIndia.domain.model.Article
import com.example.newsIndia.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}