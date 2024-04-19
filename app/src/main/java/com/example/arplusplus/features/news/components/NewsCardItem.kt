package com.example.arplusplus.features.news.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.arplusplus.R
import com.example.arplusplus.ui.theme.cardColor
import com.example.domain.entity.ArticlesItemEntity

@Composable
fun NewsCardItem(
    modifier : Modifier = Modifier,
    articlesItemEntity: ArticlesItemEntity?,
    onFavoriteClick: (articleItem : ArticlesItemEntity)-> Unit,
    isFavorite : Boolean,
    onLinkClick: (url : String)-> Unit) {
    ElevatedCard(modifier.height(180.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
            )) {

        Box {

            Row {
                AsyncImage(
                    model = articlesItemEntity?.urlToImage,
                    contentDescription = articlesItemEntity?.title,
                    modifier = Modifier.weight(.4f),
                    contentScale = ContentScale.Crop
                )

                Column(modifier = Modifier
                    .weight(.6f)
                    .padding(vertical = 4.dp, horizontal = 8.dp)) {

                    Text(text = articlesItemEntity?.title ?: "title", fontSize = 20.sp, maxLines = 2, overflow = TextOverflow.Ellipsis,
                        color = Color.Black, fontWeight = FontWeight.W600)
                    Text(text = buildAnnotatedString {
                        withStyle(SpanStyle(color = Color.Gray)) {
                            append(stringResource(R.string.author)) }
                        withStyle(SpanStyle(color = Color.Gray)){
                            append(articlesItemEntity?.author ?: "Unavailable")}
                    }, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(text = articlesItemEntity?.publishedAt ?: "published at")
                    Text(text = articlesItemEntity?.url ?: "Link Unavailable",
                        modifier = Modifier.clickable {
                            onLinkClick(articlesItemEntity?.url!!)
                        }, maxLines = 1, overflow = TextOverflow.Ellipsis,
                        color = Color.Blue)

                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = articlesItemEntity?.content ?: "content",
                        maxLines = 3, overflow = TextOverflow.Ellipsis, lineHeight = 18.sp)


                }


            }

            IconButton(onClick = { onFavoriteClick(articlesItemEntity!!)
            }) {
                Icon(painter = painterResource(id = if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favoite), contentDescription = "favorite",
                    tint = Color.White)
            }

        }
    }

}