package com.example.nextfilm.data.repository

import com.example.nextfilm.data.sources.remote.MyApi

class MyRepository(
    // resolve the problem with the constructor
    private val api : MyApi
) {
    suspend fun doNetWorkCall(){


        //The problem is,How can we get MyApi in this repository to make calls
    }
}