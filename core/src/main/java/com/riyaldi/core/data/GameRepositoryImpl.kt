package com.riyaldi.core.data

import com.riyaldi.core.data.source.local.LocalDataSource
import com.riyaldi.core.data.source.remote.RemoteDataSource
import com.riyaldi.core.data.source.remote.network.ApiResponse
import com.riyaldi.core.data.source.remote.response.GameResponse
import com.riyaldi.core.domain.model.Game
import com.riyaldi.core.domain.repository.GameRepository
import com.riyaldi.core.utils.AppExecutors
import com.riyaldi.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : GameRepository {
    override fun getGames(): Flow<Resource<List<Game>>> =
        object : NetworkResourceBound<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGames().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>): Boolean =
                data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGames(gameList)
            }
        }.asFlow()

    override fun getDetailGame(id: Int): Flow<Resource<Game>> =
        object : NetworkResourceBound<Game, GameResponse>() {
            override fun loadFromDB(): Flow<Game> {
                return localDataSource.getGameById(id).map {
                    DataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Game): Boolean {
                return data.description == "" || data.description.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> =
                remoteDataSource.getDetailGame(id)

            override suspend fun saveCallResult(data: GameResponse) {
                val gameDetail = DataMapper.mapResponseToEntity(data)
                appExecutors.diskIO().execute { localDataSource.editGame(gameDetail) }
            }
        }.asFlow()

    override fun setFavoriteGame(game: Game) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        gameEntity.isFavorite = !gameEntity.isFavorite
        appExecutors.diskIO().execute { localDataSource.editGame(gameEntity) }
    }

}