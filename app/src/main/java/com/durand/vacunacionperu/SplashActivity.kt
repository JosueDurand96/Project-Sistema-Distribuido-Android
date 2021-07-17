package com.durand.vacunacionperu

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator() //add this
        fadeIn.duration = 2000

        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator() //and this
        fadeOut.startOffset = 1000
        fadeOut.duration = 2000

        val animation = AnimationSet(true) //change to false
        animation.addAnimation(fadeIn)
        animation.addAnimation(fadeOut)
        logoImageView.animation = animation

        val durationSplash = 3000
        Handler().postDelayed({
            setupWindowAnimations()

        }, durationSplash.toLong())

    }
    private fun setupWindowAnimations() {
        val i = Intent(this, LoginActivity::class.java)
        val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this)
        startActivity(i, transitionActivityOptions.toBundle())
    }
}