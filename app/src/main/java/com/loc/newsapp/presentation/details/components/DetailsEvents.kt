package com.loc.newsapp.presentation.details.components

import com.loc.newsapp.domain.model.Article

sealed class DetailsEvents {
   data class  upsertDeleteArticle(val article: Article):DetailsEvents()
    object RemoveSideEffects:DetailsEvents()
}