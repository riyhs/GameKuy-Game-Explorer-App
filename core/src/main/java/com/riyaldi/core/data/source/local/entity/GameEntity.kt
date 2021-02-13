package com.riyaldi.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_game")
data class GameEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "description")
    val description : String,

    @ColumnInfo(name = "released")
    val released : String,

    @ColumnInfo(name = "bgImage")
    val bgImage : String,

    @ColumnInfo(name = "metaScore")
    val metaScore : Int,

    @ColumnInfo(name = "platforms")
    val platforms : String,

    @ColumnInfo(name = "genres")
    val genres : String,

    @ColumnInfo(name = "clip")
    val clip: String,

    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean = false
) : Parcelable
