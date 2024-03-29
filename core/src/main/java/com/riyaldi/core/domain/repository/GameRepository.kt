package com.riyaldi.core.domain.repository

import com.riyaldi.core.data.Resource
import com.riyaldi.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(): Flow<Resource<List<Game>>>
    fun getFavoriteGames(): Flow<List<Game>>
    fun getDetailGame(id: Int): Flow<Resource<Game>>
    fun setFavoriteGame(game: Game)
    suspend fun searchGames(query: String): Resource<List<Game>>
    suspend fun insertGame(game: Game)
}