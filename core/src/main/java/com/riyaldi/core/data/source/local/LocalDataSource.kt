package com.riyaldi.core.data.source.local

import com.riyaldi.core.data.source.local.entity.GameEntity
import com.riyaldi.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val gameDao: GameDao) {
    fun getAllGames(): Flow<List<GameEntity>> = gameDao.getAllGames()

    fun getFavoriteGames(): Flow<List<GameEntity>> = gameDao.getFavoriteGames()

    fun getGameById(id: Int): Flow<GameEntity>? = gameDao.getGameById(id)

    suspend fun insertAllGames(games: List<GameEntity>) = gameDao.insertAllGames(games)

    suspend fun insertGame(game: GameEntity) = gameDao.insertGame(game)

    fun editGame(game: GameEntity) = gameDao.updateGame(game)
}
