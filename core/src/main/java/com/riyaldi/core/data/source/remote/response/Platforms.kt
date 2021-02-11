package com.riyaldi.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Platforms(
    @SerializedName("platforms")
    val platforms: List<Platform>
)