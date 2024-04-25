package com.loc.newsapp.domain.usecases.News

import com.loc.newsapp.domain.local.NewsDao
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectedArticles(
    private val newsRepository: NewsRepository
) {
     operator fun invoke(): Flow<List<Article>> {
     return  newsRepository.selectArticles()
    }
}