package com.riyaldi.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id : Int,
    val name : String,
    val description : String,
    val released : String,
    val bgImage : String,
    val metaScore : Int,
    val platforms : String,
    val genres : String,
    val clip: String,
    val isFavorite: Boolean
) : Parcelable
