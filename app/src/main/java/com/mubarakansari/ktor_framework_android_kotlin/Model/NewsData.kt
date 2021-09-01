package com.example.a1september.Model

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)