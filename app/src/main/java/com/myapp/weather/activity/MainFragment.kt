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
        val dateFormat = SimpleDateFormat("HH:mm ")
        binding.currentTime.text = dateFormat.format(Date())
        viewModel.loadWeather()
        viewModel.data.observe(viewLifecycleOwner) { state ->
            binding.apply {
                binding.progress.isVisible = state.loading
                binding.errorGroup.isVisible = state.error

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
