package com.loc.newsapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.common.ArticleCardShimmerEffects
import com.loc.newsapp.presentation.common.Dimens
import com.loc.newsapp.presentation.common.EmptyScreen

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick:(Article) ->Unit
) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimens.MeduimPadding1)
        ){
            items(count = articles.size) {
               val article = articles[it]
                    ArticleCard(
                        article = article,
                        onClick = { onClick(article) }
                    )

            }
        }
    }

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    article: LazyPagingItems<Article>,
    onClick:(Article) ->Unit
) {
    val handlePagingItems  = HandlePagingResult(article = article)
    if (handlePagingItems){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimens.MeduimPadding1)
        ){
           items(count = article.itemCount) {
               index ->
               article[index]?.let {
               ArticleCard(
                       article = it,
                       onClick = { onClick(it) }
                   )
               }
           }
        }
    }
}

@Composable
fun HandlePagingResult(
    article: LazyPagingItems<Article>
) :Boolean{
    val loadState  = article.loadState
    val error =
        when{loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> { null}
        }

    return when{ loadState.refresh is LoadState.Loading -> {
        ShimmerEffects()
        false
    }   error != null ->{
        EmptyScreen()
        false
    }

        else -> {
            true
        }
    }

}

@Composable
fun ShimmerEffects() {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.MeduimPadding1)
    ) {
        repeat(10){
            ArticleCardShimmerEffects(
                modifier = Modifier.padding(horizontal = Dimens.MeduimPadding1)
            )
        }
    }
}