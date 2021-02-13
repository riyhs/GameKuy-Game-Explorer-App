package com.riyaldi.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Platforms(
    @SerializedName("platform")
    val platform: Platform
)