package com.riyaldi.gamekuy.explore

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riyaldi.core.data.Resource
import com.riyaldi.core.domain.model.Game
import com.riyaldi.core.domain.usecase.GameUseCase

class ExploreViewModel @ViewModelInject constructor(private val gameUseCase: GameUseCase): ViewModel() {

    private var _games = MutableLiveData<Resource<List<Game>>>()
    val games: LiveData<Resource<List<Game>>> get() = _games

    suspend fun searchGames(query: String)  {
        _games.value = gameUseCase.searchGames(query)
    }
}
