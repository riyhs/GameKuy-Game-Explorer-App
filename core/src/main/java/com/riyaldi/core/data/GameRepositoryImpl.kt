package com.riyaldi.core.data

import com.riyaldi.core.data.source.remote.RemoteDataSource
import com.riyaldi.core.data.source.remote.network.ApiResponse
import com.riyaldi.core.data.source.remote.response.Result
import com.riyaldi.core.domain.model.GameEntity
import com.riyaldi.core.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : GameRepository {
    override fun getGames(): Flow<ApiResponse<List<Result>>> {
        return remoteDataSource.getAllGames()
    }
}