package com.riyaldi.core.domain.usecase

import com.riyaldi.core.data.Resource
import com.riyaldi.core.data.source.local.entity.GameEntity
import com.riyaldi.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getGames(): Flow<Resource<List<Game>>>
    fun getFavoriteGames(): Flow<List<Game>>
    fun getDetailGame(id: Int): Flow<Resource<Game>>
    fun setFavoriteGame(game: Game)
}