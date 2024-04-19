package com.example.domain.usecases

import com.example.domain.entity.ArticlesItemEntity
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteFavoriteNewsItemUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(articlesItemEntity: ArticlesItemEntity) {

        newsRepository.removeFavoriteItem(articlesItemEntity)

    }
}