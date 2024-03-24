package com.myapp.weather.activity

import ViewPagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.myapp.weather.R
import com.myapp.weather.databinding.ActivityAppBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(MainFragment())
        adapter.addFragment(ForecastByNameFragment())
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2

    }
}

