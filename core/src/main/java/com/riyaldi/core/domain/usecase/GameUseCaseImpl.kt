package com.riyaldi.core.domain.usecase

import com.riyaldi.core.data.Resource
import com.riyaldi.core.data.source.local.entity.GameEntity
import com.riyaldi.core.domain.model.Game
import com.riyaldi.core.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameUseCaseImpl @Inject constructor(private val gameRepository: GameRepository): GameUseCase {
    override fun getGames() = gameRepository.getGames()
    override fun getFavoriteGames() = gameRepository.getFavoriteGames()
    override fun getDetailGame(id: Int) = gameRepository.getDetailGame(id)
    override fun setFavoriteGame(game: Game) = gameRepository.setFavoriteGame(game)
    override suspend fun searchGames(query: String): Resource<List<Game>> = gameRepository.searchGames(query)
    override suspend fun insertGame(game: Game) = gameRepository.insertGame(game)
}