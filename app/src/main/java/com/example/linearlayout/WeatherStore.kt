package com.example.linearlayout

class WeatherStore {
    var weathers: List<ForecastItem>? = null
    fun updateWeathers(newWeathers: List<ForecastItem>) {
        weathers = newWeathers
    }
}