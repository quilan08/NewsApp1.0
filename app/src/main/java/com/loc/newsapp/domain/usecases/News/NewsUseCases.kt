package com.loc.newsapp.domain.usecases.News

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews : SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectedArticles: SelectedArticles,
    val selectArticle: SelectArticle
)