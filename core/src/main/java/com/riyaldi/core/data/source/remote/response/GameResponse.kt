package com.riyaldi.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("released")
    val released: String,
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("metacritic")
    val metacritic: Int,
    @SerializedName("platforms")
    val platforms: List<Platforms>?,
    @SerializedName("genres")
    val genres: List<Genre>?,
    @SerializedName("clip")
    val clip: Clip?,
    @SerializedName("publishers")
    val publishers: List<Publisher>,
    @SerializedName("description_raw")
    val descriptionRaw: String
)