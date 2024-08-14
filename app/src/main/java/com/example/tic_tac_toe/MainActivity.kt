package com.example.tic_tac_toe

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var case1: Button
    private lateinit var case2: Button
    private lateinit var case3: Button
    private lateinit var case4: Button
    private lateinit var case5: Button
    private lateinit var case6: Button
    private lateinit var case7: Button
    private lateinit var case8: Button
    private lateinit var case9: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        case1 = findViewById(R.id.button1)
        case2 = findViewById(R.id.button2)
        case3 = findViewById(R.id.button3)
        case4 = findViewById(R.id.button4)
        case5 = findViewById(R.id.button5)
        case6 = findViewById(R.id.button6)
        case7 = findViewById(R.id.button7)
        case8 = findViewById(R.id.button8)
        case9 = findViewById(R.id.button9)
    }
}