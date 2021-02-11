package com.riyaldi.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Platform(
    @SerializedName("name")
    val name: String
)