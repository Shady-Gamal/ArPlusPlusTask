package com.example.data.data_sources.remote.data_source_impl.news


import com.example.data.data_sources.remote.news.NewsService
import com.example.data.mappers.news.toEntity
import com.example.data.util.Constants
import com.example.domain.entity.NewsResponseEntity
import com.example.domain.repository.NewsOnlineDataSource
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(
    private val newsService: NewsService
) : NewsOnlineDataSource {
    override suspend fun getNews(search: String?, page: Int, perPage: Int): NewsResponseEntity =try {

        newsService.getAllNews(search,Constants.API_KEY,perPage, page).toEntity()

    }catch(e : Exception){
        throw e
    }
}