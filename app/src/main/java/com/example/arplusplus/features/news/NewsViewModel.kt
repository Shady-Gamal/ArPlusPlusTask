package com.example.arplusplus.features.news

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import com.example.domain.entity.ArticlesItemEntity
import com.example.domain.entity.Resource
import com.example.domain.usecases.DeleteFavoriteNewsItemUseCase
import com.example.domain.usecases.GetFavoriteNewsUseCase
import com.example.domain.usecases.GetNewsUseCase
import com.example.domain.usecases.InsertFavoriteNewsItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val getFavoriteNewsUseCase: GetFavoriteNewsUseCase,
    private val insertFavoriteNewsItemUseCase: InsertFavoriteNewsItemUseCase,
    private val deleteFavoriteNewsItemUseCase: DeleteFavoriteNewsItemUseCase
) : ViewModel() {

    val selectedIndex = mutableIntStateOf(0)
    val tabItems = listOf("News","Favorites")
    private val _textSearch = MutableStateFlow<String?>(null)
    var textSearch: StateFlow<String?> = _textSearch.asStateFlow()
    val news = mutableStateOf<Pager<Int, ArticlesItemEntity>?>(null)
    val error = mutableStateOf<String?>(null)
    val isNewsLoading = mutableStateOf(false)
    val favoriteNews = mutableStateListOf<ArticlesItemEntity?>()
    val isFavoriteLoading = mutableStateOf(false)


    @OptIn(FlowPreview::class)
    fun getNews() {
        viewModelScope.launch {
            try {
                viewModelScope.launch {
                    textSearch.debounce(1000).collect { query ->
                        if (query?.isNotBlank() == true && query.isNotEmpty()){
                            isNewsLoading.value = true
                            Timber.e(isNewsLoading.value.toString())
                        news.value = getNewsUseCase.invoke(query)
                        isNewsLoading.value = false

                        }
                    }

                }
            }
            catch (e : Exception){
               error.value = e.message
            }
        }
    }
    fun getFavoriteNews(){
        viewModelScope.launch{
            getFavoriteNewsUseCase.invoke().collect{
                when (it){
                    is Resource.Success -> {
                        it.data?.let { items -> favoriteNews.addAll(items)
                        isFavoriteLoading.value = false
                        }
                    }
                    is Resource.Error -> {
                        error.value = it.message
                        isFavoriteLoading.value = false
                    }
                    is Resource.Loading -> isFavoriteLoading.value = true
                }
            }
        }
    }

    fun insertNewsItem(newsItem : ArticlesItemEntity){
        viewModelScope.launch {
            try {
                insertFavoriteNewsItemUseCase.invoke(newsItem)
                favoriteNews.add(newsItem)

            }catch (e : Exception){
                error.value = e.message
            }
        }
    }

    fun deleteNewsItem(newsItem: ArticlesItemEntity){

        viewModelScope.launch {
            try {
                deleteFavoriteNewsItemUseCase.invoke(newsItem)
                favoriteNews.remove(newsItem)
            }catch (e : Exception){
                error.value = e.message
            }

        }
    }

    fun setSearchText(it: String?) {
        _textSearch.value = it
    }


}