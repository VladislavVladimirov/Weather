package com.myapp.weather.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.myapp.weather.databinding.FragmentMainBinding
import com.myapp.weather.utils.DataFormatter.formatTemp
import com.myapp.weather.utils.DataFormatter.getDescription
import com.myapp.weather.utils.DataFormatter.getHumidity
import com.myapp.weather.utils.DataFormatter.getPressure
import com.myapp.weather.utils.DataFormatter.getTime
import com.myapp.weather.utils.DataFormatter.getVisibility
import com.myapp.weather.utils.DataFormatter.getWind
import com.myapp.weather.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: ViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater)
        val swipeRefresher = binding.weatherSwipeRefresh

        viewModel.loadWeather()
        viewModel.data.observe(viewLifecycleOwner) { state ->
            binding.apply {
                errorGroup.isVisible = state.error
                allInfo.isGone = state.error || state.loading
                progress.isVisible = state.loading

                currentTemperature.text = formatTemp(state.weatherForecast?.main?.temp)
                weatherDescription.text = getDescription(state)
                currentHumidity.text = getHumidity(state)
                currentPressure.text = getPressure(state)
                windSpeed.text = getWind(state)
                feelsLike.text = buildString {
                    append("Ощущается как ")
                    append(state.weatherForecast?.main?.feelsLike)
                }
                currentVisibility.text = getVisibility(state)
                currentSunrise.text = getTime(state.weatherForecast?.sys?.sunrise, "HH:mm")
                currentSunset.text = getTime(state.weatherForecast?.sys?.sunset, "HH:mm")

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
