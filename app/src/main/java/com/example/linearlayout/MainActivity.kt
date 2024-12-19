package com.example.linearlayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ForecastAdapter
    private var forecastData: List<ForecastItem>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.r_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val weatherStore = WeatherStore()
        val recyclerView: RecyclerView = findViewById(R.id.r_view)
        adapter= ForecastAdapter(ForecastDiffCallback())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        if (weatherStore.weathers != null) {
            adapter.submitList(weatherStore.weathers)
        }
        else
        {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(OpenWeatherMapService::class.java)

            val apiKey = resources.getString(R.string.key)
            val city = "Шклов"
            val units = "metric"

            val call = service.getForecast(city, units, apiKey)

            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)

            call.enqueue(object : Callback<Forecast> {
                override fun onResponse(call: retrofit2.Call<Forecast>, response: Response<Forecast>) {
                    if (response.isSuccessful) {
                        val forecast = response.body()
                        forecast?.apply {
                            weatherStore.weathers = list
                            adapter.submitList(list)
                        }
                    }
                }

                override fun onFailure(call: retrofit2.Call<Forecast>, t: Throwable) {
                }
            })
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (forecastData != null) {
            outState.putSerializable("forecastData", forecastData as Serializable)
        }
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        forecastData = savedInstanceState?.getSerializable("forecastData") as? List<ForecastItem>
        adapter.submitList(forecastData)
    }
}