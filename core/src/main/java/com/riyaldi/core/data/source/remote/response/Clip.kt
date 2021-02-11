package com.riyaldi.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class Clip(
    @SerializedName("clip")
    val clip: String
)