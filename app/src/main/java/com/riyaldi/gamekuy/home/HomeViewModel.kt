package com.riyaldi.gamekuy.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.riyaldi.core.domain.usecase.GameUseCaseImpl

class HomeViewModel @ViewModelInject constructor(gameUseCase: GameUseCaseImpl): ViewModel() {
    val game = gameUseCase.getGames().asLiveData()
}