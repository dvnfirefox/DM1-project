package com.example.tic_tac_toe

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var case0: Button
    private lateinit var case1: Button
    private lateinit var case2: Button
    private lateinit var case3: Button
    private lateinit var case4: Button
    private lateinit var case5: Button
    private lateinit var case6: Button
    private lateinit var case7: Button
    private lateinit var case8: Button
    private lateinit var start: Button
    private var grid = arrayOf(0,0,0,0,0,0,0,0,0)
    private var lastPlay = "X"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        case0 = findViewById(R.id.button0)
        case1 = findViewById(R.id.button1)
        case2 = findViewById(R.id.button2)
        case3 = findViewById(R.id.button3)
        case4 = findViewById(R.id.button4)
        case5 = findViewById(R.id.button5)
        case6 = findViewById(R.id.button6)
        case7 = findViewById(R.id.button7)
        case8 = findViewById(R.id.button8)
        start = findViewById(R.id.start)

        case0.setOnClickListener {
            gridClick(case0,0)
        }
        case1.setOnClickListener {
            gridClick(case1,1)
        }
        case2.setOnClickListener {
            gridClick(case2,2)
        }
        case3.setOnClickListener {
            gridClick(case3,3)
        }
        case4.setOnClickListener {
            gridClick(case4,4)
        }
        case5.setOnClickListener {
            gridClick(case5,5)
        }
        case6.setOnClickListener {
            gridClick(case6,6)
        }
        case7.setOnClickListener {
            gridClick(case7,7)
        }
        case8.setOnClickListener {
            gridClick(case8,8)
        }
        start.setOnClickListener {
            grid = arrayOf(0,0,0,0,0,0,0,0,0)
            case0.setText("")
            case1.setText("")
            case2.setText("")
            case3.setText("")
            case4.setText("")
            case5.setText("")
            case6.setText("")
            case7.setText("")
            case8.setText("")

        }

    }
    private fun gridClick(button: Button, index: Int) {
        if(grid[index] == 0 && lastPlay == "X" ) {
            button.setText("O")
            grid[index] = 1
            lastPlay = "O"
        }else if(grid[index] == 0 && lastPlay == "O" ) {
            button.setText("X")
            grid[index] = 1
            lastPlay = "X"

        }

    }


}