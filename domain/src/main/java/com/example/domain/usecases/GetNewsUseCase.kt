package com.example.domain.usecases

import androidx.paging.Pager
import com.example.domain.entity.ArticlesItemEntity
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(search : String?) : Pager<Int, ArticlesItemEntity>{

        return newsRepository.getNews(search)
    }
}