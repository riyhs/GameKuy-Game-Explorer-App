package com.riyaldi.gamekuy.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.riyaldi.core.domain.model.Game
import com.riyaldi.core.domain.usecase.GameUseCase

class DetailViewModel @ViewModelInject constructor(private val gameUseCase: GameUseCase): ViewModel() {

    private var id : Int = 0

    fun setFilm(id: Int) {
        this.id = id
    }

    fun getDetailFilm() = gameUseCase.getDetailGame(id).asLiveData()
}