package com.example.fetchrewards.api

import com.example.fetchrewards.FetchItem
import retrofit2.Response
import retrofit2.http.GET

interface FetchRequest {

    @GET("hiring.json")
    suspend fun getItems() : List<FetchItem>
}