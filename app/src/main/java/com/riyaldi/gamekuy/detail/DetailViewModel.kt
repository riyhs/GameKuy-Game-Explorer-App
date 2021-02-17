package com.riyaldi.gamekuy.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.riyaldi.core.domain.model.Game
import com.riyaldi.core.domain.usecase.GameUseCase

class DetailViewModel @ViewModelInject constructor(private val gameUseCase: GameUseCase): ViewModel() {
    fun getDetailFilm(id: Int) = gameUseCase.getDetailGame(id).asLiveData()
}