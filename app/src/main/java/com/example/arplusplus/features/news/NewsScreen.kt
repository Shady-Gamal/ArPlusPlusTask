package com.example.arplusplus.features.news

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.arplusplus.features.news.components.AddToFavorite
import com.example.arplusplus.features.news.components.NewsCardItem
import com.example.arplusplus.features.news.components.SearchForNews
import com.example.arplusplus.features.news.components.TopBar
import com.example.arplusplus.ui.theme.linearGradient
import com.example.arplusplus.ui.theme.primaryColor
import com.example.arplusplus.ui.theme.selectedTab
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
) {
    Timber.e( "timber ====> ${viewModel.favoriteNews?.size.toString()}")
    val context = LocalContext.current

    val pagerState = rememberPagerState {
        viewModel.tabItems.size
    }

    val textSearch = viewModel.textSearch.collectAsState()
    LaunchedEffect(key1 = viewModel.selectedIndex.intValue) {
        pagerState.animateScrollToPage(viewModel.selectedIndex.intValue)
    }
    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {

        if (!pagerState.isScrollInProgress) {

            viewModel.selectedIndex.intValue = pagerState.currentPage
        }
    }

    LaunchedEffect(key1 = Unit ){
        viewModel.getNews()
        viewModel.getFavoriteNews()
    }
    val paging = viewModel.news.value?.flow?.collectAsLazyPagingItems()
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopBar(textFieldState = textSearch.value,
                onTextChangedCallback = {viewModel.setSearchText(it)
                    if (viewModel.selectedIndex.intValue != 0) viewModel.selectedIndex.intValue = 0
                    viewModel.selectedIndex.intValue = 0
                }, onCancelClick = {
                    viewModel.setSearchText(null)

            })

        }
    ) { paddingValues->

        LaunchedEffect(key1 = viewModel.error.value){

            if (viewModel.error.value?.isNotEmpty() == true){
            Toast.makeText(context,viewModel.error.value, Toast.LENGTH_SHORT).show()
            }
        }
        Column(modifier = Modifier.padding(paddingValues)) {
            TabRow(selectedTabIndex = viewModel.selectedIndex.intValue,
                modifier = Modifier.background(linearGradient),
                containerColor = Color.Transparent, indicator = { tabPositions ->
                    if (viewModel.selectedIndex.intValue < tabPositions.size) {
                        TabRowDefaults.Indicator(
                            modifier = Modifier.tabIndicatorOffset(
                                tabPositions[viewModel.selectedIndex.intValue
                                ]
                            ),
                            color = selectedTab,
                        )
                    }
                }
                ) {
            viewModel.tabItems.forEachIndexed { index, item ->
                Tab(selected = index == viewModel.selectedIndex.intValue, onClick = {
                    viewModel.selectedIndex.intValue = index
                }) {
                    Text(
                        text = item,
                        style = TextStyle(
                            fontSize = 14.86.sp,
                            fontWeight = FontWeight(400),
                            color = if (index == viewModel.selectedIndex.intValue) Color.White else Color.White.copy(
                                0.3f
                            ),
                            textAlign = TextAlign.Center,
                        ), modifier = Modifier.padding(vertical = 15.dp)
                    )
                }

            }
            }
            HorizontalPager(state = pagerState, modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .weight(1f)) {

                if (it == 0) {
                    if (paging?.itemSnapshotList.isNullOrEmpty() && !viewModel.isNewsLoading.value && viewModel.textSearch.collectAsState().value.isNullOrEmpty()) {
                        SearchForNews()
                    }else if (paging?.itemSnapshotList?.isEmpty() == true) {
                        Box(modifier = Modifier.fillMaxSize()) {

                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = primaryColor)}
                    Timber.e("Iam literally here")}

                    LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp), content = {
                        items(paging?.itemCount ?: 0) {

                            val isFavorite = if (viewModel.favoriteNews.find {
                                        article-> article?.title == paging?.get(it)!!.title } != null) true
                            else false

                            NewsCardItem(modifier = Modifier.padding(horizontal = 16.dp),
                                articlesItemEntity = paging?.get(it)!!,
                                onFavoriteClick = {articlesItemEntity->
                                if (isFavorite) viewModel.deleteNewsItem(articlesItemEntity)
                                    else viewModel.insertNewsItem(articlesItemEntity)
                                },
                                isFavorite = isFavorite,
                                onLinkClick ={

                                    uriHandler.openUri(it)
                                }

                            )

                        }
                        when (paging?.loadState?.append) {
                            is LoadState.Error -> item {

                            }

                            LoadState.Loading -> item {
                                PaginationLoadingItem()
                            }

                            is LoadState.NotLoading -> {}
                            null -> {
                            }
                        }
                    })
                }else {
                    if (viewModel.favoriteNews.isEmpty()) AddToFavorite()
                    if (viewModel.isFavoriteLoading.value) CircularProgressIndicator()
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxSize(), content = {
                        items(viewModel.favoriteNews.size) {
                            val item = viewModel.favoriteNews.get(it)
                            NewsCardItem(modifier = Modifier.padding(horizontal = 16.dp),
                                articlesItemEntity = item,
                                onFavoriteClick = {articlesItemEntity->
                                    viewModel.deleteNewsItem(articlesItemEntity)
                                },
                                isFavorite = true
                            , onLinkClick = {uriHandler.openUri(it)})

                        }

                    })

                }
                
            }
        }
    }
}


@Composable
fun PaginationLoadingItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Black,
            modifier = modifier
                .size(35.dp)
                .padding(vertical = 8.dp)

        )
    }
}