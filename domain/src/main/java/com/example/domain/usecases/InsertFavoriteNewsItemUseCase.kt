package com.example.domain.usecases

import com.example.domain.entity.ArticlesItemEntity
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class InsertFavoriteNewsItemUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(newsItem : ArticlesItemEntity) {

        newsRepository.insertNews(newsItem)

    }
}