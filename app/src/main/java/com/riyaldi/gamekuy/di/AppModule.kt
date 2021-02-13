package com.riyaldi.gamekuy.di

import com.riyaldi.core.domain.usecase.GameUseCase
import com.riyaldi.core.domain.usecase.GameUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideGameUseCase(gameRepository: GameUseCaseImpl): GameUseCase

}