package com.example.data.di
import com.example.data.data_sources.local.data_source_impl.NewsOfflineDataSourceImpl
import com.example.data.data_sources.remote.data_source_impl.news.NewsOnlineDataSourceImpl
import com.example.data.repository.news.NewsRepositoryImpl
import com.example.domain.repository.NewsOfflineDataSource
import com.example.domain.repository.NewsOnlineDataSource
import com.example.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(repositoryImpl: NewsRepositoryImpl) : NewsRepository

    @Binds
    @Singleton
    abstract fun bindNewsOnlineDataSource(newsOnlineDataSourceImpl: NewsOnlineDataSourceImpl) : NewsOnlineDataSource


    @Binds
    @Singleton
    abstract fun bindNewsOfflineDataSource(newsOfflineDataSourceImpl: NewsOfflineDataSourceImpl) : NewsOfflineDataSource




}