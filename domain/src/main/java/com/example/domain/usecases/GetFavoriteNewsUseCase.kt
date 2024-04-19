package com.example.domain.usecases

import com.example.domain.entity.ArticlesItemEntity
import com.example.domain.entity.Resource
import com.example.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteNewsUseCase @Inject constructor(val newsRepository: NewsRepository) {

    suspend operator fun invoke() : Flow<Resource<List<ArticlesItemEntity>>> {

        return newsRepository.getFavoriteNews()

    }
}