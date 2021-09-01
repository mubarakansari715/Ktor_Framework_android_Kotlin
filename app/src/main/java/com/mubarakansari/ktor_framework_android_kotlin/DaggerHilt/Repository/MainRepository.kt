package com.mubarakansari.ktor_framework_android_kotlin.DaggerHilt.Repository

import com.mubarakansari.ktor_framework_android_kotlin.Model.Post
import com.mubarakansari.ktor_framework_android_kotlin.Network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    fun getPost(): Flow<List<Post>> = flow {
        emit(apiService.getAllData())
    }.flowOn(Dispatchers.IO)

}