package com.example.marvel.api

import com.example.marvel.models.MarvelResponse
import com.example.marvel.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {

    @GET("v1/public/characters")
    suspend fun getCharacter(
        @Query("name")
        name: String,
        @Query("ts")
        ts: Long,
        @Query("apikey")
        apiKey: String = Constants.API_KEY,
        @Query("hash")
        hash: String
    ): Response<MarvelResponse>

}