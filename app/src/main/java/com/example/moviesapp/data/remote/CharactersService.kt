package com.example.moviesapp.data.remote

import com.example.moviesapp.data.remote.dto.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): CharactersResponse




}