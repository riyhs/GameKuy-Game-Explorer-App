package com.riyaldi.core.data.source.remote.network

import com.riyaldi.core.data.source.remote.response.GamesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("games")
    suspend fun getGames(): GamesResponse
}