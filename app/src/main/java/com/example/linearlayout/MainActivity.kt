package com.example.linearlayout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val colorList: ArrayList<ColorData> = ArrayList()
        colorList.add(ColorData("Red", "#FF0000"))
        colorList.add(ColorData("Green", "#00FF00"))
        colorList.add(ColorData("Blue", "#0000FF"))
        colorList.add(ColorData("White", "#FFFFFF"))
        colorList.add(ColorData("Black", "#000000"))
        colorList.add((ColorData("Pink", "#FFC0CB")))

        val adapter = Adapter(this, colorList)
        recyclerView.adapter = adapter
    }

    data class ColorData (
        val colorName: String,
        val colorHex: String
    )

    class Adapter(private val context: Context, private val list: ArrayList<ColorData>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val image: View = view.findViewById(R.id.view)
            val text: TextView = view.findViewById(R.id.text_view)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.rview_item,parent,false)

            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.count()
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val colorData = list[position]
            holder.text.text = colorData.colorName
            holder.image.setBackgroundColor(android.graphics.Color.parseColor(colorData.colorHex))
        }
    }
}