package com.loc.newsapp.presentation.details.Screens

import android.content.Intent
import android.media.ImageReader
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.common.Dimens
import com.loc.newsapp.presentation.common.Dimens.ArticleImageHeight
import com.loc.newsapp.presentation.common.Dimens.MeduimPadding1
import com.loc.newsapp.presentation.details.components.DetailsEvents
import com.loc.newsapp.presentation.details.components.DetailsTopBar
import com.loc.newsapp.presentation.navgraph.Route
import com.loc.newsapp.presentation.search.SearchEvent
import dagger.Lazy

@Composable
fun DetailsScreens(
    article: Article,
    event: (DetailsEvents)-> Unit,
    navigateUp:() ->Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                              Intent(Intent.ACTION_VIEW).also {
                                  it.data = Uri.parse(article.url)
                                  if (it.resolveActivity(context.packageManager) != null ){
                                      context.startActivity(it)
                                  }
                              }
            },
            onShareClick = { Intent(Intent.ACTION_SEND).also {
                it.putExtra(Intent.EXTRA_TEXT, article.url)
                it.type = "text/plain"
                if(it.resolveActivity(context.packageManager) != null){
                    context.startActivity(it)
                }
            }},
            onBookmarkClick = {   event(DetailsEvents.upsertDeleteArticle(article))},
            onBackClick = { navigateUp() })

        LazyColumn(
            modifier= Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = Dimens.MeduimPadding1,
                end = Dimens.MeduimPadding1,
                top = Dimens.MeduimPadding1
            )
        ){
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                 Spacer(modifier = Modifier.height(MeduimPadding1))
                Text(text = article.title, style = MaterialTheme.typography
                    .displaySmall, color = colorResource(
                    id = R.color.text_title
                ))
                Text(text = article.content, style = MaterialTheme.typography
                    .bodyMedium, color = colorResource(
                    id = R.color.body
                ))
            }
        }
        }
}

