package com.loc.newsapp.domain.usecases.News

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(source: List<String?>): Flow<PagingData<Article>>{
        return newsRepository.getNews(sources = source)
    }
}