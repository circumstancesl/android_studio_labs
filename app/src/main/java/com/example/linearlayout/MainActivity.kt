package com.example.linearlayout

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val TAG = "Flickr cats"
        val button: Button = findViewById(R.id.btnHTTP)
        val buttonOkCats: Button = findViewById(R.id.btnOkHTTP)

        button.setOnClickListener {
            val url = URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1")

            Thread {
                val connection = url.openConnection() as HttpURLConnection

                try {
                    val data = connection.inputStream.bufferedReader().readText()
                    connection.disconnect()
                    Log.d(TAG, data)
                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
            }.start()
        }

        buttonOkCats.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val client = OkHttpClient()
                val request = Request.Builder().url("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1").build()

                try {
                    val response: Response = client.newCall(request).execute()
                    val data = response.body?.string()
                    Log.i(TAG, data?: "No response")
                }
                catch (e: Exception) {
                    Log.e(TAG,"Error: ${e.message}")
                }
            }
        }
    }
}