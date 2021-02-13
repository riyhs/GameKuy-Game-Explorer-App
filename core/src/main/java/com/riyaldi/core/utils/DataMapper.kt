package com.riyaldi.core.utils

import com.riyaldi.core.data.source.local.entity.GameEntity
import com.riyaldi.core.data.source.remote.response.GameResponse
import com.riyaldi.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()

        input.map {
            val platforms = getPlatformName(it)
            val genres = getGenreName(it)
            val game = GameEntity(
                    id = it.id,
                    name = it.name,
                    description = "",
                    released = it.released,
                    bgImage = it.backgroundImage,
                    metaScore = it.metacritic,
                    platforms = platforms,
                    genres = genres,
                    clip = it.clip.clip,
                    isFavorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
            input.map {
                Game(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        released = it.released,
                        bgImage = it.bgImage,
                        metaScore = it.metaScore,
                        platforms = it.platforms,
                        genres = it.genres,
                        clip = it.clip,
                        isFavorite = it.isFavorite
                )
            }

    fun mapDomainToEntity(input: Game): GameEntity = GameEntity(
            id = input.id,
            name = input.name,
            description = input.description,
            released = input.released,
            bgImage = input.bgImage,
            metaScore = input.metaScore,
            platforms = input.platforms,
            genres = input.genres,
            clip = input.clip,
            isFavorite = input.isFavorite
    )

    private fun getPlatformName(data: GameResponse): String {
        val result = StringBuilder().append("")

        for (i in data.platforms.indices) {
            if (i < data.platforms.size - 1) {
                result.append("${data.platforms[i].platform.name}, ")
            } else {
                result.append(data.platforms[i].platform.name)
            }
        }

        return result.toString()
    }

    private fun getGenreName(data: GameResponse): String {
        val result = StringBuilder().append("")

        for (i in data.genres.indices) {
            if (i < data.genres.size - 1) {
                result.append("${data.genres[i].name}, ")
            } else {
                result.append(data.genres[i].name)
            }
        }

        return result.toString()
    }
}