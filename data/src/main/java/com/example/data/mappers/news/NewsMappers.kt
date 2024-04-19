package com.example.data.mappers.news

import com.example.data.models.ArticlesItemModel
import com.example.data.models.NewsResponseModel
import com.example.data.models.SourceModel
import com.example.domain.entity.ArticlesItemEntity
import com.example.domain.entity.NewsResponseEntity
import com.example.domain.entity.SourceEntity

fun NewsResponseModel.toEntity() : NewsResponseEntity{
    return NewsResponseEntity(totalResults,articles?.map { it?.toEntity()!! }, status)

}
fun ArticlesItemModel.toEntity() : ArticlesItemEntity{

   return ArticlesItemEntity(publishedAt,author,urlToImage,description,source?.toEntity(),title,url,content)

}

fun SourceModel.toEntity() : SourceEntity{
   return SourceEntity(name, id)
}


fun NewsResponseEntity.toModel() : NewsResponseModel{

    return NewsResponseModel(totalResults,articles?.map { it.toModel() },status)
}

fun ArticlesItemEntity.toModel(): ArticlesItemModel{

    return ArticlesItemModel(publishedAt, author, urlToImage, description,source?.toModel(),title!!, url, content)
}

fun SourceEntity.toModel() : SourceModel{
    return SourceModel(name, id)
}