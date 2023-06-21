package com.myapp.weather.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.myapp.weather.databinding.FragmentMainBinding
import com.myapp.weather.viewmodel.ViewModel
import java.text.SimpleDateFormat
import java.util.Date


class MainFragment : Fragment() {
    val viewModel: ViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater)
        val swipeRefresher = binding.postsSwipeRefresh

        viewModel.loadWeather()
        viewModel.data.observe(viewLifecycleOwner) { state ->
            binding.apply {
                progress.isVisible = state.loading
                errorGroup.isVisible = state.error
                val dateFormat = SimpleDateFormat("HH:mm ")
                currentTime.text = dateFormat.format(Date()).toString()
                currentTemperature.text = state.weatherForecast?.main?.temp.toString()
            }
            binding.retryButton.setOnClickListener {
                viewModel.loadWeather()
            }
            swipeRefresher.setOnRefreshListener {
                swipeRefresher.isRefreshing = true
                viewModel.loadWeather()
                swipeRefresher.isRefreshing = false
            }

        }
        return binding.root
    }
}
