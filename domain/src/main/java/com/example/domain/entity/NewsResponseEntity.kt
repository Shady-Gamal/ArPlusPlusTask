package com.example.domain.entity



data class NewsResponseEntity(

	val totalResults: Int? ,

	val articles: List<ArticlesItemEntity>?,

	val status: String?
)

data class ArticlesItemEntity(

	val publishedAt: String?,

	val author: String? ,

	val urlToImage: String? ,

	val description: String? ,

	val source: SourceEntity? ,

	val title: String? ,

	val url: String? ,

	val content: String?
)

data class SourceEntity(


	val name: String? ,

	val id: String?
)
