package com.riyaldi.gamekuy

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.riyaldi.core.domain.usecase.GameUseCase

class MainViewModel @ViewModelInject constructor(gameUseCase: GameUseCase): ViewModel() {
    val games = gameUseCase.getGames().asLiveData()
}