package com.mubarakansari.ktor_framework_android_kotlin.Network

import com.example.a1september.Model.NewsData
import com.mubarakansari.ktor_framework_android_kotlin.Model.Post
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import javax.inject.Inject

class ApiService @Inject constructor() {

    companion object{
        private const val API_KEY = "1b897cb15b75469c95a4f7a4d88f6a5e"
        const val URL = "https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=$API_KEY&pagesize=10"
    }
    private val client = HttpClient(Android) {
        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }

        install(JsonFeature) {
            serializer = GsonSerializer()
        }

        engine {
            connectTimeout = 100_000
            socketTimeout = 100_000
        }
    }

    suspend fun getAllData(): List<Post> {
        return client.get {
            url("https://jsonplaceholder.typicode.com/photos")
        }
    }
//    suspend fun getNews(page:Int): NewsData {
//        return client.get {
//            url("$URL&page=$page")
//        }
//    }
}