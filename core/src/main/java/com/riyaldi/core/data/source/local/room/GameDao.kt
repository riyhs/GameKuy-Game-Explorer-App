package com.riyaldi.core.data.source.local.room

import androidx.room.*
import com.riyaldi.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM tb_game")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM tb_game WHERE id = :id")
    fun getGameById(id: Int): Flow<GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: List<GameEntity>)

    @Update
    suspend fun updateGame(game: GameEntity)
}