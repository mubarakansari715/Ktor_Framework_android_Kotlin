package com.mubarakansari.ktor_framework_android_kotlin.DaggerHilt.ui

import com.mubarakansari.ktor_framework_android_kotlin.Model.Post

sealed class ApiState {

    object Empty : ApiState()
    class Failure(val message: Throwable) : ApiState()
    class Success(val data: List<Post>) : ApiState()
    object Loading : ApiState()
}
