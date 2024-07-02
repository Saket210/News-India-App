package com.example.newsIndia.domain.usecases.news

import com.example.newsIndia.domain.model.Article
import com.example.newsIndia.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}