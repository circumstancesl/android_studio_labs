package com.example.linearlayout

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        val text: EditText = findViewById(R.id.edit_text)
        val buttonBlack: Button = findViewById(R.id.button_black)
        val buttonRed: Button = findViewById(R.id.button_red)
        val button8: Button = findViewById(R.id.button_8sp)
        val button24: Button = findViewById(R.id.button_24sp)
        val buttonWhite: Button = findViewById(R.id.button_white)
        val buttonYellow: Button = findViewById(R.id.button_yellow)
        text.setText("")

        buttonBlack.setOnClickListener {
            text.setTextColor(Color.BLACK)
        }

        buttonRed.setOnClickListener {
            text.setTextColor(Color.RED)
        }

        button8.setOnClickListener {
            text.textSize = 8F
        }

        button24.setOnClickListener {
            text.textSize = 24F
        }

        buttonWhite.setOnClickListener {
            text.setBackgroundColor(Color.WHITE)
        }

        buttonYellow.setOnClickListener {
            text.setBackgroundColor(Color.YELLOW)
        }
    }
}