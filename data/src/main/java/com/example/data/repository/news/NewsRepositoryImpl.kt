package com.example.data.repository.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.data.paging.NewsPagingSource
import com.example.domain.entity.ArticlesItemEntity
import com.example.domain.entity.Resource
import com.example.domain.repository.NewsOfflineDataSource
import com.example.domain.repository.NewsOnlineDataSource
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsOnlineDataSource: NewsOnlineDataSource,
    private val newsOfflineDataSource: NewsOfflineDataSource
) : NewsRepository {

    override suspend fun getNews(search : String?): Pager<Int, ArticlesItemEntity> =try {

        Pager(PagingConfig(10)){
            NewsPagingSource(
                newsOnlineDataSource,
                search = search
            )
        }
    }catch (e : Exception){
        throw e
    }

    override suspend fun getFavoriteNews(): Flow<Resource<List<ArticlesItemEntity>>> {
        return newsOfflineDataSource.getFavoriteNews()
    }

    override suspend fun insertNews(articlesItemEntity: ArticlesItemEntity) = try {
        newsOfflineDataSource.insertNews(articlesItemEntity)
    }catch (e : Exception){

        throw e
    }

    override suspend fun removeFavoriteItem(articlesItemEntity: ArticlesItemEntity) =try {
        newsOfflineDataSource.removeFavoriteItem(articlesItemEntity)
    }catch (e : Exception){
        Timber.e(e.message.toString())
        throw e

    }
}