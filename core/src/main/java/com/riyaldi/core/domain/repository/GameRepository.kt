package com.riyaldi.core.domain.repository

import com.riyaldi.core.data.Resource
import com.riyaldi.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(): Flow<Resource<List<Game>>>
}