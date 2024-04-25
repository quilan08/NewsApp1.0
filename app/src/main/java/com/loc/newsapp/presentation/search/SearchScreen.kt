package com.loc.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.common.Dimens
import com.loc.newsapp.presentation.common.SearchBar
import com.loc.newsapp.presentation.home.ArticlesList
import com.loc.newsapp.presentation.navgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) ->Unit,
    navigateToDetails :(Article) ->Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = Dimens.MeduimPadding1,
                start = Dimens.MeduimPadding1,
                end = Dimens.MeduimPadding1
            )
            .statusBarsPadding().fillMaxSize()
    ){
        SearchBar(text = state.searchQuery, readOnly =false , onValueChange ={event(SearchEvent.UpdateSearchQuery(it))}, onSearch =
            {event(SearchEvent.SearchNews)})
        }
        Spacer(modifier = Modifier.height(Dimens.MeduimPadding1))
    state.articles?.let {
        val articles = it.collectAsLazyPagingItems()
        ArticlesList(article = articles, onClick = {navigateToDetails(it)})
    }
    }

