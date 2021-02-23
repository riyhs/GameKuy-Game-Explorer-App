package com.riyaldi.core.data

import com.riyaldi.core.data.source.local.LocalDataSource
import com.riyaldi.core.data.source.local.entity.GameEntity
import com.riyaldi.core.data.source.remote.RemoteDataSource
import com.riyaldi.core.data.source.remote.network.ApiResponse
import com.riyaldi.core.data.source.remote.response.GameResponse
import com.riyaldi.core.domain.model.Game
import com.riyaldi.core.domain.repository.GameRepository
import com.riyaldi.core.utils.AppExecutors
import com.riyaldi.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
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

                override fun shouldFetch(data: List<Game>?): Boolean =
                        data?.isEmpty() == true || data == null

                override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                        remoteDataSource.getAllGames()

                override suspend fun saveCallResult(data: List<GameResponse>) {
                    val gameList = DataMapper.mapResponsesToEntities(data)
                    localDataSource.insertAllGames(gameList)
                }
            }.asFlow() as Flow<Resource<List<Game>>>

    override fun getFavoriteGames(): Flow<List<Game>> {
        return localDataSource.getFavoriteGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    }

    override fun getDetailGame(id: Int): Flow<Resource<Game>> =
            object : NetworkResourceBound<Game, GameResponse>() {
                override fun loadFromDB(): Flow<Game?>? {
                    return localDataSource.getGameById(id)?.map { gameEntity: GameEntity? ->
                        if (gameEntity == null) {
                            return@map null
                        } else {
                            return@map DataMapper.mapEntityToDomain(gameEntity)
                        }
                    }
                }

                override fun shouldFetch(data: Game?): Boolean {
                    return data?.description == "" || data == null
                }

                override suspend fun createCall(): Flow<ApiResponse<GameResponse>> =
                        remoteDataSource.getDetailGame(id)

                override suspend fun saveCallResult(data: GameResponse) {
                    val gameDetail = DataMapper.mapResponseToEntity(data)
                    localDataSource.insertGame(gameDetail)
                }
            }.asFlow() as Flow<Resource<Game>>

    override suspend fun isFavorite(id: Int): Boolean? {

        return localDataSource.getGameById(id)?.first()?.isFavorite
    }

    override fun setFavoriteGame(game: Game) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        gameEntity.isFavorite = !gameEntity.isFavorite
        appExecutors.diskIO().execute { localDataSource.editGame(gameEntity) }
    }

    override suspend fun searchGames(query: String): Resource<List<Game>> {
        return when(val response = remoteDataSource.searchGame(query).first()) {
            is ApiResponse.Success -> {
                val gamesEntities = DataMapper.mapResponsesToEntities(response.data)
                val games = DataMapper.mapEntitiesToDomain(gamesEntities)
                Resource.Success(games)
            }
            is ApiResponse.Error -> {
                Resource.Error(response.errorMessage, null)
            }
            is ApiResponse.Empty -> {
                Resource.Error(response.toString(), null)
            }
        }
    }

    override suspend fun insertGame(game: Game) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.editGame(gameEntity) }
    }

}