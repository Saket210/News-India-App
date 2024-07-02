package com.example.newsIndia.di

import android.app.Application
import androidx.room.Room
import com.example.newsIndia.data.local.Dao
import com.example.newsIndia.data.local.NewsDatabase
import com.example.newsIndia.data.local.NewsTypeConvertor
import com.example.newsIndia.data.remote.NewsApi
import com.example.newsIndia.data.repository.NewsRepositoryImpl
import com.example.newsIndia.domain.repository.NewsRepository
import com.example.newsIndia.domain.usecases.news.DeleteArticle
import com.example.newsIndia.domain.usecases.news.GetNews
import com.example.newsIndia.domain.usecases.news.NewsUseCases
import com.example.newsIndia.domain.usecases.news.SearchNews
import com.example.newsIndia.domain.usecases.news.SelectArticles
import com.example.newsIndia.domain.usecases.news.SelectedArticle
import com.example.newsIndia.domain.usecases.news.UpsertArticle
import com.example.newsIndia.util.Constant.BASE_URL
import com.example.newsIndia.util.Constant.NEWS_DATA_BASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        dao: Dao
    ): NewsRepository = NewsRepositoryImpl(newsApi, dao)

    @Provides
    @Singleton
    fun provideNewUseCase(
        newsRepository: NewsRepository,
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectedArticle = SelectedArticle(newsRepository)

        )
    }

    @Provides
    @Singleton
    fun providesNewsDataBase(application: Application): NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATA_BASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDTO(newsDatabase: NewsDatabase): Dao = newsDatabase.newsDao
}