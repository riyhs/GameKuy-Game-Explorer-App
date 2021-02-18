package com.riyaldi.gamekuy.di

import com.riyaldi.core.domain.usecase.GameUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface FavoriteModuleDependencies {
    fun gameUseCase(): GameUseCase
}