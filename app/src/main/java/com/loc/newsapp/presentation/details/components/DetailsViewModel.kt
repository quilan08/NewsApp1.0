package com.loc.newsapp.presentation.details.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.usecases.News.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
):ViewModel() {
    var sideEffects by mutableStateOf<String?>(null)
        private set

    fun onEvent(events: DetailsEvents) {
        when(events) {
         is   DetailsEvents.upsertDeleteArticle ->
         {     viewModelScope.launch {
             val article = newsUseCases.selectArticle(events.article.url)
             if (article == null) upsertArticle(events.article)
             else deleteArticle(events.article)
         }

         }

          is  DetailsEvents.RemoveSideEffects ->  sideEffects =null
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article)
        sideEffects ="Article Deleted"
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article)
        sideEffects ="Article Saved"

    }
}