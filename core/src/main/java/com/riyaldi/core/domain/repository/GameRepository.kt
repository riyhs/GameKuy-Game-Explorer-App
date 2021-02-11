package com.riyaldi.core.domain.repository

import com.riyaldi.core.domain.model.GameEntity

interface GameRepository {
    fun getGames(): List<GameEntity>
}