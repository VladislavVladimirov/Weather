package com.myapp.weather.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.myapp.weather.R
import com.myapp.weather.databinding.FragmentMainBinding
import com.myapp.weather.utils.DataFormatter.formatTemp
import com.myapp.weather.utils.DataFormatter.getDayOfWeek
import com.myapp.weather.utils.DataFormatter.getDescription
import com.myapp.weather.utils.DataFormatter.getHumidity
import com.myapp.weather.utils.DataFormatter.getPressure
import com.myapp.weather.utils.DataFormatter.getTime
import com.myapp.weather.utils.DataFormatter.getVisibility
import com.myapp.weather.utils.DataFormatter.getWind
import com.myapp.weather.utils.DataFormatter.setWeatherIcon
import com.myapp.weather.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: ViewModel by activityViewModels()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater)
        val swipeRefresher = binding.weatherSwipeRefresh
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        getLocation()
        viewModel.data.observe(viewLifecycleOwner) { state ->

            binding.apply {
                if (state.error) {
                    Snackbar.make(
                        binding.root,
                        getText(R.string.error_loading),
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Ок") {
                            getLocation()
                            viewModel.loadWeatherByGps(viewModel.getLat(), viewModel.getLon())
                        }
                        .show()
                }
                allInfo.isGone = state.error || state.loading
                progress.isVisible = state.loading
                if (state.weatherForecast5Days == null || state.weatherForecastDaily == null) {
                    allInfo.isGone = true
                } else {
                    allInfo.isVisible = true
                }
                cityName.text = state.weatherForecastDaily?.name
                currentTemperature.text = formatTemp(state.weatherForecastDaily?.main?.temp)
                lowestTemperature.text = formatTemp(state.weatherForecastDaily?.main?.temp_min)
                highestTemperature.text = formatTemp(state.weatherForecastDaily?.main?.temp_max)
                weatherDescription.text = getDescription(state)
                currentHumidity.text = getHumidity(state)
                currentPressure.text = getPressure(state)
                windSpeed.text = getWind(state)
                feelsLike.text = buildString {
                    append(getString(R.string.feels_like))
                    append(formatTemp(state.weatherForecastDaily?.main?.feels_like))
                }
                currentVisibility.text = getVisibility(state)
                currentSunrise.text = getTime(state.weatherForecastDaily?.sys?.sunrise)
                currentSunset.text = getTime(state.weatherForecastDaily?.sys?.sunset)

                day1Name.text = getDayOfWeek(state.weatherForecast5Days?.list?.get(8)?.dt_txt)
                day2Name.text = getDayOfWeek(state.weatherForecast5Days?.list?.get(16)?.dt_txt)
                day3Name.text = getDayOfWeek(state.weatherForecast5Days?.list?.get(24)?.dt_txt)
                day4Name.text = getDayOfWeek(state.weatherForecast5Days?.list?.get(32)?.dt_txt)
                day5Name.text = getDayOfWeek(state.weatherForecast5Days?.list?.get(39)?.dt_txt)

                day1HighTemp.text =
                    formatTemp(state.weatherForecast5Days?.list?.get(8)?.main?.temp_max)
                day2HighTemp.text =
                    formatTemp(state.weatherForecast5Days?.list?.get(16)?.main?.temp_max)
                day3HighTemp.text =
                    formatTemp(state.weatherForecast5Days?.list?.get(24)?.main?.temp_max)
                day4HighTemp.text =
                    formatTemp(state.weatherForecast5Days?.list?.get(32)?.main?.temp_max)
                day5HighTemp.text =
                    formatTemp(state.weatherForecast5Days?.list?.get(39)?.main?.temp_max)

                day1LowTemp.text = formatTemp(state.weatherForecast5Days?.list?.get(4)?.main?.temp)
                day2LowTemp.text = formatTemp(state.weatherForecast5Days?.list?.get(12)?.main?.temp)
                day3LowTemp.text = formatTemp(state.weatherForecast5Days?.list?.get(20)?.main?.temp)
                day4LowTemp.text = formatTemp(state.weatherForecast5Days?.list?.get(28)?.main?.temp)
                day5LowTemp.text = formatTemp(state.weatherForecast5Days?.list?.get(36)?.main?.temp)

                setWeatherIcon(
                    day1Icon,
                    state.weatherForecast5Days?.list?.get(8)?.weather?.get(0)?.description.toString()
                )
                setWeatherIcon(
                    day2Icon,
                    state.weatherForecast5Days?.list?.get(16)?.weather?.get(0)?.description.toString()
                )
                setWeatherIcon(
                    day3Icon,
                    state.weatherForecast5Days?.list?.get(24)?.weather?.get(0)?.description.toString()
                )
                setWeatherIcon(
                    day4Icon,
                    state.weatherForecast5Days?.list?.get(32)?.weather?.get(0)?.description.toString()
                )
                setWeatherIcon(
                    day5Icon,
                    state.weatherForecast5Days?.list?.get(39)?.weather?.get(0)?.description.toString()
                )

                swipeRefresher.setOnRefreshListener {
                    swipeRefresher.isRefreshing = true
                    getLocation()
                    viewModel.loadWeatherByGps(viewModel.getLat(), viewModel.getLon())
                    swipeRefresher.isRefreshing = false
                }
//                cityName.setOnClickListener {
//                    if (cityNameInput.visibility == View.GONE) {
//                        cityNameInput.visibility = View.VISIBLE
//                    } else {
//                        cityNameInput.visibility = View.GONE
//                    }
//                }
//                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//                    AndroidUtils.hideKeyboard(requireView())
//                    if (state.cityName != null) {
//                        cityNameInput.visibility = View.GONE
//                    }

            }
//                cityNameInput.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
//                    if (actionId == EditorInfo.IME_ACTION_DONE) {
//                        viewModel.loadWeatherByName(cityNameInput.text.toString().trim())
//                        state.cityName = cityNameInput.text.toString().trim()
//                        cityNameInput.visibility = View.GONE
//                        allInfo.visibility = View.VISIBLE
//
//                        return@OnEditorActionListener true
//                    }
//                    false
//                })
//            }

        }
        return binding.root
    }

    private fun getLocation() {
        val task = fusedLocationProviderClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }
        task.addOnSuccessListener {
            if (it != null) {
                viewModel.saveLat(it.latitude)
                viewModel.saveLon(it.longitude)
            }
        }
    }

}
