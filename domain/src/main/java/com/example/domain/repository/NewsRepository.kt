package com.example.domain.repository

import androidx.paging.Pager
import com.example.domain.entity.ArticlesItemEntity
import com.example.domain.entity.NewsResponseEntity
import com.example.domain.entity.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(search: String?) : Pager<Int, ArticlesItemEntity>
    suspend fun getFavoriteNews(): Flow<Resource<List<ArticlesItemEntity>>>
    suspend fun insertNews(articlesItemEntity: ArticlesItemEntity)
    suspend fun removeFavoriteItem(articlesItemEntity: ArticlesItemEntity)
}

interface NewsOnlineDataSource {

    suspend fun getNews(search : String?, page : Int, perPage : Int) : NewsResponseEntity
}

interface NewsOfflineDataSource {

    suspend fun getFavoriteNews(): Flow<Resource<List<ArticlesItemEntity>>>
    suspend fun insertNews(articlesItemEntity: ArticlesItemEntity)
    suspend fun removeFavoriteItem(articlesItemEntity: ArticlesItemEntity)
}