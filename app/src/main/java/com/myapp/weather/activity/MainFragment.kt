package com.myapp.weather.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.myapp.weather.utils.DataFormatter
import com.myapp.weather.databinding.FragmentMainBinding
import com.myapp.weather.utils.DataFormatter.formatTemp
import com.myapp.weather.utils.DataFormatter.getDescription
import com.myapp.weather.utils.DataFormatter.getHumidity
import com.myapp.weather.utils.DataFormatter.getPressure
import com.myapp.weather.utils.DataFormatter.getWind
import com.myapp.weather.viewmodel.ViewModel




class MainFragment : Fragment() {
    val viewModel: ViewModel by activityViewModels()
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
                currentTime.text = DataFormatter.getDate()
                currentTemperature.text = formatTemp(state.weatherForecast?.main?.temp)
                weatherDescription.text = getDescription(state)
                currentHumidity.text = getHumidity(state)
                currentPressure.text = getPressure(state)
                windSpeed.text = getWind(state)
//                val iconId = getIconId(state)
//                if (iconId != null) {
//                    Glide.with(weatherStateIcon)
//                        .load("http://openweathermap.org/img/w/$iconId.png")
//                        .apply(RequestOptions.bitmapTransform(CircleCrop()))
//                        .timeout(10_000)
//                        .into(weatherStateIcon)
//                }

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
