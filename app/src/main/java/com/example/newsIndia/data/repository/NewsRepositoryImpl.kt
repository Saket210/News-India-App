package com.example.newsIndia.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsIndia.data.local.Dao
import com.example.newsIndia.data.remote.NewsApi
import com.example.newsIndia.data.remote.NewsPagingSource
import com.example.newsIndia.data.remote.SearchNewsPagingSource
import com.example.newsIndia.domain.model.Article
import com.example.newsIndia.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val dao: Dao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        dao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        dao.delete(article)
    }

    override fun selectArticle(): Flow<List<Article>> {
        return dao.getArticles()
    }

    override suspend fun selectedArticle(url: String): Article? {
        return dao.getArticle(url = url)
    }
}