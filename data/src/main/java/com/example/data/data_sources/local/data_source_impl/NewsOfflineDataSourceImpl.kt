package com.example.data.data_sources.local.data_source_impl

import com.example.data.data_sources.local.ArPlusPlusDatabase
import com.example.data.mappers.news.toEntity
import com.example.data.mappers.news.toModel
import com.example.domain.entity.ArticlesItemEntity
import com.example.domain.entity.NewsResponseEntity
import com.example.domain.entity.Resource
import com.example.domain.repository.NewsOfflineDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class NewsOfflineDataSourceImpl @Inject constructor(
    private val arPlusPlusDatabase : ArPlusPlusDatabase
): NewsOfflineDataSource {
    override suspend fun getFavoriteNews(): Flow<Resource<List<ArticlesItemEntity>>> {

        return flow<Resource<List<ArticlesItemEntity>>>{
            emit(Resource.Success(arPlusPlusDatabase.getNewsDao().getNews().map { it.toEntity() }))
        }.onStart { emit(Resource.Loading())
        }.catch {
            emit(Resource.Error(it.message ?: "There Was an Error"))
        }
        }

    override suspend fun insertNews(articlesItemEntity: ArticlesItemEntity) {
        try {
            arPlusPlusDatabase.getNewsDao().insertNews(articlesItemEntity.toModel())
        }catch (e : Exception){
            throw e
        }
    }
    override suspend fun removeFavoriteItem(articlesItemEntity: ArticlesItemEntity) {

        arPlusPlusDatabase.getNewsDao().deleteNews(articlesItemEntity.toModel())
    }
}