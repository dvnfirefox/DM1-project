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
    private val grid = Array(3) { Array(3) { 0 } }
    private var lastPlay = "X"
    private var win = false
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
            gridClick(case0,0,0)
        }
        case1.setOnClickListener {
            gridClick(case1,0,1)
        }
        case2.setOnClickListener {
            gridClick(case2,0,2)
        }
        case3.setOnClickListener {
            gridClick(case3,1,0)
        }
        case4.setOnClickListener {
            gridClick(case4,1,1)
        }
        case5.setOnClickListener {
            gridClick(case5,1,2)
        }
        case6.setOnClickListener {
            gridClick(case6,2,0)
        }
        case7.setOnClickListener {
            gridClick(case7,2,1)
        }
        case8.setOnClickListener {
            gridClick(case8,2,2)
        }
        start.setOnClickListener {
            reset()
        }
    }
    private fun reset(){
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                grid[row][col] = 0
            }
        }
        case0.setText("")
        case1.setText("")
        case2.setText("")
        case3.setText("")
        case4.setText("")
        case5.setText("")
        case6.setText("")
        case7.setText("")
        case8.setText("")
        win = false
    }
    private fun gridClick(button: Button, index0: Int, index1: Int) {
        if (grid[index0][index1] != 0 || win) {
            return
        }
        if(lastPlay == "X" ) {
            button.setText("O")
            grid[index0][index1] = 1
            lastPlay = "O"
        }else{
            button.setText("X")
            grid[index0][index1] = -1
            lastPlay = "X"
        }
        drawCheck()
        winCheck()
    }
    private fun drawCheck (){
        var gridFull = 0
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if(grid[row][col] != 0){
                    gridFull ++
                }
            }
        }
        if(gridFull == 9){
            Toast.makeText(this@MainActivity, "DRAW", Toast.LENGTH_SHORT).show()
        }
    }
    private fun winCheck(){
        for (row in grid.indices) {
            var count = 0
            for (col in grid[row].indices) {
                count = grid[row][col] + count
            }
            countCheck(count)
        }
        for (col in grid.indices) {
            var count = 0
            for (row in grid[col].indices) {
                count = grid[row][col] + count
            }
            countCheck(count)
        }
        var count = 0
        for(i in 2 downTo 0){
            count = count + grid[i][i]
        }
        countCheck(count)
        count = 0
        for(i in 2 downTo 0){
            count = count + grid[i][2-i]
        }
        countCheck(count)
    }
    private fun countCheck(count: Int){
        if(count == -3){
            Toast.makeText(this@MainActivity, "WIN Player 1", Toast.LENGTH_SHORT).show()
            win = true
            return
        }
        if(count == 3){
            Toast.makeText(this@MainActivity, "WIN Player 2", Toast.LENGTH_SHORT).show()
            win = true
            return
        }
    }

}