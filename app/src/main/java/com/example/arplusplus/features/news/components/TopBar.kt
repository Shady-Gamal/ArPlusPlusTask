package com.example.arplusplus.features.news.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arplusplus.R
import com.example.arplusplus.ui.theme.linearGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    textFieldState: String?,
    onTextChangedCallback: (String) -> Unit,
    onCancelClick : () -> Unit
) {
    TopAppBar(
        modifier = Modifier.background(linearGradient),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicTextField(
                    value = textFieldState ?: "",
                    onValueChange = { onTextChangedCallback(it) },
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f)
                        .background(
                            color = Color(0xFFF9F9F9).copy(.1f),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .clip(
                            RoundedCornerShape(10.dp)
                        ),
                    decorationBox = { innerTextField ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_search),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp)
                                    .size(20.dp),
                                tint = Color.White
                            )
                            Box() {

                                if (textFieldState.isNullOrEmpty()) {
                                    Text(
                                        text = "Search here",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            lineHeight = 16.sp,
                                            fontWeight = FontWeight(400),
                                            color = Color.White.copy(.3f),
                                            textAlign = TextAlign.Center,
                                            letterSpacing = 0.4.sp,
                                        )
                                    )
                                }
                                innerTextField.invoke()
                            }
                        }
                                    },
                    textStyle = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        textAlign = TextAlign.Start
                    ),
                    maxLines = 1,
                    cursorBrush = SolidColor(Color.White),
                    )
                Text(
                        text = "Cancel", modifier = Modifier
                            .padding(start = 5.dp, end = 17.dp)
                            .clickable {
                                onCancelClick()
                            }, fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = Color.White,
                        lineHeight = 20.sp
                    )

            }
        }
    )
}