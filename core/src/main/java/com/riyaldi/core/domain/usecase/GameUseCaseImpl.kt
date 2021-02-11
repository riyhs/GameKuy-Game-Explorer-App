package com.riyaldi.core.domain.usecase

import com.riyaldi.core.domain.model.GameEntity
import com.riyaldi.core.domain.repository.GameRepository

class GameUseCaseImpl(private val gameRepository: GameRepository): GameUseCase {
    override fun getGames(): List<GameEntity> {
        return gameRepository.getGames()
    }
}