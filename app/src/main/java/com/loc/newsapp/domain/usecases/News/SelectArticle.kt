package com.loc.newsapp.domain.usecases.News

import com.loc.newsapp.domain.local.NewsDao
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository


class SelectArticle(
  private val newsRepository: NewsRepository
) {
    suspend operator fun invoke (url:String) :Article? {
        return newsRepository.selectArticle(url)
    }
}