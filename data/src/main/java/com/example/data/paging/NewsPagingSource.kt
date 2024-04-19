package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.entity.ArticlesItemEntity
import com.example.domain.repository.NewsOnlineDataSource

class NewsPagingSource(
    private val dataSource: NewsOnlineDataSource,
    private val perPage: Int = 10,
    private val search : String?
) : PagingSource<Int, ArticlesItemEntity>() {

    override fun getRefreshKey(state: PagingState<Int, ArticlesItemEntity>): Int? =
        state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItemEntity> = try {
        val page = params.key ?: 1
        val response = dataSource.getNews(search, page, perPage)
        LoadResult.Page(
            response?.articles ?: listOf(),
            prevKey = null,
            nextKey = page.plus(1)
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}