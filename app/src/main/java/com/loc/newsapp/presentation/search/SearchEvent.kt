package com.loc.newsapp.presentation.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchEvent: String):SearchEvent()
    object SearchNews:SearchEvent()
}