package com.riyaldi.core.domain.usecase

import com.riyaldi.core.domain.model.Game
import com.riyaldi.core.domain.repository.GameRepository
import javax.inject.Inject

class GameUseCaseImpl @Inject constructor(private val gameRepository: GameRepository): GameUseCase {
    override fun getGames() = gameRepository.getGames()
    override fun getDetailGame(id: Int) = gameRepository.getDetailGame(id)
    override fun setFavoriteGame(game: Game) = gameRepository.setFavoriteGame(game)
}