package com.loc.newsapp.di

import android.app.Application
import androidx.room.Room
import com.loc.newsapp.data.manager.LocalManagerImpl
import com.loc.newsapp.data.remote.sub.NewsApi
import com.loc.newsapp.data.repository.NewsRepositoryImpl
import com.loc.newsapp.domain.local.NewsDao
import com.loc.newsapp.domain.local.NewsTypeConvertor
import com.loc.newsapp.domain.local.NewsDatabase
import com.loc.newsapp.domain.manager.LocalManager
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.usecases.News.DeleteArticle
import com.loc.newsapp.domain.usecases.News.GetNews
import com.loc.newsapp.domain.usecases.News.NewsUseCases
import com.loc.newsapp.domain.usecases.News.SearchNews
import com.loc.newsapp.domain.usecases.News.SelectArticle
import com.loc.newsapp.domain.usecases.News.SelectedArticles
import com.loc.newsapp.domain.usecases.News.UpsertArticle
import com.loc.newsapp.domain.usecases.appEntry.AppEntryUseCases
import com.loc.newsapp.domain.usecases.appEntry.ReadAppEntry
import com.loc.newsapp.domain.usecases.appEntry.SavedAppEntry
import com.loc.newsapp.utils.Constants
import com.loc.newsapp.utils.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalManager(
        application: Application
    )  : LocalManager = LocalManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localManager: LocalManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localManager),
        savedAppEntry = SavedAppEntry(localManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(

    ):NewsApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).
        build().create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ):NewsRepository = NewsRepositoryImpl(newsApi,newsDao)


    //get the news from the api
    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases{
        return  NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectedArticles = SelectedArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ) :NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ):NewsDao = newsDatabase.newsDao

}

