package com.loc.newsapp.presentation.bookmark

import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.usecases.News.SelectedArticles

data class BookmarkState (
    val articles: List<Article> = emptyList()
)