package com.riyaldi.gamekuy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.riyaldi.core.data.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.games.observe(this, {games ->
            when(games) {
                is Resource.Success -> {
                    Log.d("MainActivity", "Success")
                    Log.d("MainActivityResponse", games.data.toString())
                }
                is Resource.Error -> {
                    Log.d("MainActivity", "Errr")
                }
                is Resource.Loading -> {
                    Log.d("MainActivity", "Loading")
                }
            }
        })

    }
}