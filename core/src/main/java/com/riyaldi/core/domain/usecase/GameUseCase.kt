package com.riyaldi.core.domain.usecase

import com.riyaldi.core.domain.model.GameEntity

interface GameUseCase {
    fun getGames(): List<GameEntity>
}