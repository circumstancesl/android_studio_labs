package com.example.linearlayout

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val editText: EditText = findViewById(R.id.edit_text)
        val checkBox: CheckBox = findViewById(R.id.check_box)
        val button: Button = findViewById(R.id.button)
        val textView: TextView = findViewById(R.id.text_view)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        editText.setText("")

        button.setOnClickListener {
            if (checkBox.isChecked) {
                val inputText = editText.text.toString()
                textView.text = inputText

                progressBar.progress += 10
            }
        }
    }
}