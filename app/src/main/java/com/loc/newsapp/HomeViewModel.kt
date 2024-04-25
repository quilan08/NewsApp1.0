package com.loc.newsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.newsapp.domain.usecases.News.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) :ViewModel(){
    val news = newsUseCases.getNews(
        source = listOf(
            "cnn",
            "al-jazeera-english",
            "associated-press"
        )
    ).cachedIn(viewModelScope)
}