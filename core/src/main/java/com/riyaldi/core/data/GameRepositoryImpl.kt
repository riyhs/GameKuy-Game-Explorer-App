package com.riyaldi.core.data

import com.riyaldi.core.data.source.NetworkResourceBound
import com.riyaldi.core.data.source.remote.RemoteDataSource
import com.riyaldi.core.data.source.remote.network.ApiResponse
import com.riyaldi.core.data.source.remote.response.Result
import com.riyaldi.core.domain.model.GameEntity
import com.riyaldi.core.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : GameRepository {
    override fun getGames(): Flow<Resource<List<GameEntity>>> =
        object : NetworkResourceBound<List<GameEntity>, List<Result>>() {
            override fun loadFromDB(): Flow<List<GameEntity>> {
                return flow {
                    emit(listOf(GameEntity(99,"","","","",0,"","","",false)))
                }
            }

            override fun shouldFetch(data: List<GameEntity>): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<Result>>> =
                remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: List<Result>) {
            }
        }.asFlow()
}