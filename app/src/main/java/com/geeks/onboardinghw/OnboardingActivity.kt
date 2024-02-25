package com.geeks.onboardinghw

import MainActivity
import OnboardingPagerAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var skipButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val sharedPreferences = getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val isFirstTime = sharedPreferences.getBoolean("isFirstTime", true)

        if (!isFirstTime) {
            startMainActivity()
            return
        }

        viewPager = findViewById(R.id.viewPager)
        skipButton = findViewById(R.id.skipButton)

        val adapter = OnboardingPagerAdapter(this)
        viewPager.adapter = adapter

        skipButton.setOnClickListener {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        val sharedPreferences = getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isFirstTime", false)
        editor.apply()

        val intent = Intent(this@OnboardingActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

