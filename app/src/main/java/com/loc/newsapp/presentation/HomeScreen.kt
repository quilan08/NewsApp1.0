package com.loc.newsapp.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.common.Dimens
import com.loc.newsapp.presentation.common.SearchBar
import com.loc.newsapp.presentation.home.ArticlesList
import com.loc.newsapp.presentation.navgraph.Route

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles :LazyPagingItems<Article>, navigateToSearchScreen :(String) -> Unit,
    navigateToDetailScreen:(Article) -> Unit,
) {
    val titles by remember {
        derivedStateOf {
            if(articles.itemCount >10 ) {
                articles.itemSnapshotList.items.
                        slice(IntRange(start = 0, endInclusive = 9)).
                        joinToString(separator = "\uD83d\uDFE5" ){
                            it.title
                        }
            }else {
                ""
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.MeduimPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_network),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = Dimens.MeduimPadding1)
        )
        Spacer(modifier = Modifier.height(Dimens.MeduimPadding1))
        SearchBar(
            text =" ",
            readOnly = true,
            onValueChange = {},
            onClick = {navigateToSearchScreen(Route.SearchScreen.route)},
            onSearch = {}
        )
        Spacer(modifier = Modifier.height(Dimens.MeduimPadding1))
        Text(text = titles, modifier = Modifier
            .fillMaxWidth()
            .padding(start = Dimens.MeduimPadding1)
            .basicMarquee(),
            color =  colorResource(id = R.color.placeholder)
            
        )
        Spacer(modifier = Modifier.height(Dimens.MeduimPadding1))
        ArticlesList(modifier =  Modifier.padding(horizontal = Dimens.MeduimPadding1),article =articles, onClick ={
            navigateToDetailScreen(it)
        } )
    }
}
