package com.loc.newsapp.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.Source
import com.loc.newsapp.presentation.common.Dimens.ArticleImageSize
import com.loc.newsapp.presentation.common.Dimens.ExtraSmallPadding1
import com.loc.newsapp.presentation.common.Dimens.ExtraSmallPadding2
import com.loc.newsapp.presentation.common.Dimens.SmallSizeIcon
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    modifier : Modifier = Modifier,
    article: Article,
    onClick : ()-> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick() }
    )
    {
        AsyncImage(modifier = Modifier.size(ArticleImageSize),model = ImageRequest.Builder(context).
            data(article.urlToImage).build(),
            contentDescription =null,
            contentScale = ContentScale.Crop
            )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding1)
                .height(ArticleImageSize)
        ) {
            Text(text = article.title, style = MaterialTheme.
            typography.bodyMedium, color = colorResource(
                id = R.color.text_title
            ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
                )
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = article.source.name, style = MaterialTheme.
                typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold
                ), color = colorResource(
                    id = R.color.text_title
                ),
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(modifier =Modifier.size(SmallSizeIcon),
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription =null , tint = colorResource(id = R.color.body) )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(text = article.publishedAt, style = MaterialTheme.
                typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold
                ), color = colorResource(
                    id = R.color.body
                ),
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsAppTheme {
        ArticleCard(article = Article(
            author=" ",
            content=" ",
            description=" Hey buddy how are diubg i miss you so much that i cant sto thinking about you each and every day bro so whatsup with you my bro",
            publishedAt="4hours",
            source= Source(id = " ", name = "CNN"),
            title = "Mask mandates return at some US hospitals as COVID, flu jump - Reuters",
            url = " ",
            urlToImage=""
        )) {

        }
    }

}