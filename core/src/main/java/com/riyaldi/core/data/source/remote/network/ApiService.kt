package com.riyaldi.core.data.source.remote.network

import com.riyaldi.core.data.source.remote.response.GameResponse
import com.riyaldi.core.data.source.remote.response.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("games")
    suspend fun getGames(): GamesResponse

    @GET("games/{gameId}")
    suspend fun getDetailGame(@Path("gameId") gameId: String): GameResponse
}