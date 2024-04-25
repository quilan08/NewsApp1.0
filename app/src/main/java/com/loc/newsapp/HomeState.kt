package com.loc.newsapp

data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
)