package com.riyaldi.gamekuy.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.riyaldi.core.domain.model.Game
import com.riyaldi.gamekuy.databinding.ActivityDetailBinding
import java.lang.Math.abs

class DetailActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var binding: ActivityDetailBinding

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
            populateData(game)
            Toast.makeText(this, game.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun populateData(game: Game) {
        binding.collapsing.title = game.name

        binding.tvDetailDescription.text = game.description

        Glide.with(this)
            .load(game.bgImage)
            .into(binding.backdrop)
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