package com.riyaldi.gamekuy.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.riyaldi.gamekuy.MainActivity
import com.riyaldi.gamekuy.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_TIME_OUT = 1500L
    }

    private var _activitySplashBinding: ActivitySplashBinding? = null
    private val binding get() = _activitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.splashTextview?.visibility = View.GONE

        binding?.splashLottie?.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                binding?.splashTextview?.visibility = View.VISIBLE

                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }, SPLASH_TIME_OUT)
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationRepeat(animation: Animator?) {}

        })
    }
}