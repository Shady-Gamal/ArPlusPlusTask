package com.example.arplusplus.features.news.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.arplusplus.R
import com.example.arplusplus.ui.theme.linearGradient
import com.example.arplusplus.ui.theme.primaryColor
import com.example.arplusplus.ui.theme.textColor

@Composable
fun SearchForNews() {

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
       Spacer(modifier = Modifier.height(100.dp))
        Image(painter = painterResource(id = R.drawable.ic_search), contentDescription = "search",
            modifier = Modifier.size(200.dp), colorFilter = ColorFilter.tint(color = primaryColor))
        Text(text = stringResource(R.string.search_for_something), fontWeight = FontWeight.W700, color = textColor)}
    

}