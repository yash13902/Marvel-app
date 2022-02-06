package com.example.marvel.repository

import com.example.marvel.api.RetrofitInstance

class MarvelRepository {

    suspend fun getCharacter(name: String, ts: Long, hash: String) =
        RetrofitInstance.api.getCharacter(name = name, ts = ts, hash = hash)

}