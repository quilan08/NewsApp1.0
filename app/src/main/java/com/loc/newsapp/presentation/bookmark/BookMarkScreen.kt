package com.loc.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.common.Dimens
import com.loc.newsapp.presentation.home.ArticlesList
import com.loc.newsapp.presentation.navgraph.Route

@Composable
fun BookMarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article)-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = Dimens.MeduimPadding1,
                end = Dimens.MeduimPadding1,
                top = Dimens.MeduimPadding1
            )
    ) {
       Text(
           text = "Bookmark",
           style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
           color = colorResource(
               id = R.color.text_title
           )
       )
        Spacer(modifier = Modifier.height(Dimens.MeduimPadding1))

        ArticlesList(articles = state.articles, onClick = {navigateToDetails(it)} )
        
    }
}