package com.riyaldi.gamekuy.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.riyaldi.core.data.Resource
import com.riyaldi.core.domain.model.Game
import com.riyaldi.gamekuy.R
import com.riyaldi.gamekuy.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private val detailViewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataGame: Game

    companion object {
        const val EXTRA_GAME = "extra_game"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        binding.appbar.addOnOffsetChangedListener(this)

        val game = intent.getParcelableExtra<Game>(EXTRA_GAME)

        if (game != null) {
            var isFav = true

            lifecycleScope.launch {
                isFav = detailViewModel.isFavorite(game.id) as Boolean
            }
            Toast.makeText(this@DetailActivity, isFav.toString(), Toast.LENGTH_SHORT).show()

            if (isFav) {
                var isChanged = false

                detailViewModel.getDetailFilm(game.id).observe(this, { detailGame ->
                    when (detailGame) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            dataGame = detailGame.data as Game

                            if (dataGame.isFavorite != isFav && !isChanged) {
                                detailViewModel.setFavoriteGame(dataGame)
                                setFavoriteState(dataGame.isFavorite)
                                isChanged = true
                            }

                            populateData(dataGame)
                            showLoading(false)

                            setFavoriteState(dataGame.isFavorite)
                            binding.fabAddFavorite.setOnClickListener {
                                detailViewModel.setFavoriteGame(dataGame)
                                setFavoriteState(dataGame.isFavorite)
                            }
                        }
                        is Resource.Error -> {
                            Toast.makeText(this, "error : ${detailGame.message}", Toast.LENGTH_SHORT).show()
                            showLoading(false)
                        }
                    }
                })
            } else {
                detailViewModel.getDetailFilm(game.id).observe(this, { detailGame ->
                    when (detailGame) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            dataGame = detailGame.data as Game

                            populateData(dataGame)
                            showLoading(false)

                            setFavoriteState(dataGame.isFavorite)
                            binding.fabAddFavorite.setOnClickListener {
                                detailViewModel.setFavoriteGame(dataGame)
                                setFavoriteState(dataGame.isFavorite)
                            }
                        }
                        is Resource.Error -> {
                            Toast.makeText(this, "error : ${detailGame.message}", Toast.LENGTH_SHORT).show()
                            showLoading(false)
                        }
                    }
                })
            }
        }
    }

    private fun setFavoriteState(isFav: Boolean) {
        if (isFav) binding.fabAddFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_filled))
        else binding.fabAddFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
    }

    private fun populateData(game: Game) {
        binding.collapsing.title = game.name

        binding.tvDetailDescription.text = game.description

        Glide.with(this)
            .load(game.bgImage)
            .into(binding.backdrop)
    }

    private fun showLoading(state: Boolean) {
        binding.pbDetail.isVisible = state
        binding.appbar.isVisible = !state
        binding.nsvDetail.isVisible = !state
    }

    private val percentageToShowImage = 20
    private var mMaxScrollSize = 0
    private var mIsImageHidden = false

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        if (mMaxScrollSize == 0) mMaxScrollSize = appBarLayout?.totalScrollRange ?: 0

        val currentScrollPercentage: Int = (abs(verticalOffset) * 100 / mMaxScrollSize)

        if (currentScrollPercentage >= percentageToShowImage) {
            if (!mIsImageHidden) {
                mIsImageHidden = true
            }
        }

        if (currentScrollPercentage < percentageToShowImage) {
            if (mIsImageHidden) {
                mIsImageHidden = false
            }
        }
    }
}