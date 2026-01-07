package com.example.nextfilm.util

import android.os.Message

// isso aqui é pra lidar com erros e sucessos
sealed class Resource<T> (val data: T? = null, val message: String? = null) {

    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(data: T? = null, message: String): Resource<T>(data,message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}