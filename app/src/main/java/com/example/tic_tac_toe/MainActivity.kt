package com.example.tic_tac_toe

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat


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
    private lateinit var player1Name: String
    private lateinit var player2Name: String
    private lateinit var playerTurnView: TextView

    private val grid = Array(3) { Array(3) { 0 } }
    private var win = true
    private var firstStart = true
    private var turnCount = 0
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
        playerTurnView = findViewById(R.id.turnView)

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
            if(firstStart) initialStart() else reset()

        }
    }
    private fun initialStart(){
        val player1NameInput = findViewById<TextInputEditText>(R.id.player1Name)
        val player2NameInput = findViewById<TextInputEditText>(R.id.player2Name)
        player1Name = player1NameInput.text.toString()
        player2Name = player2NameInput.text.toString()
        if(player1Name.isNotEmpty() && player2Name.isNotEmpty()){
            start.text = "RESET"
            player1NameInput.isEnabled = false
            player2NameInput.isEnabled = false
            win = false
            firstStart = false
            playerTurn()

        }else{
            Toast.makeText(this@MainActivity, "Enter players name", Toast.LENGTH_LONG).show()
        }

    }
    private fun reset(){

        for (row in grid.indices) {
            for (col in grid[row].indices) {
                grid[row][col] = 0
            }
        }
        case0.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        case1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        case2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        case3.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        case4.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        case5.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        case6.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        case7.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        case8.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        win = false
        start.text = "RESET"
        turnCount = 0
        playerTurn()
    }
    private fun gridClick(button: Button, index0: Int, index1: Int) {
        if (grid[index0][index1] != 0 || win) {
            return
        }
        if(turnCount % 2 == 0 ) {
            val drawableTop = ContextCompat.getDrawable(this, R.drawable.cross)
            button.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null)
            grid[index0][index1] = 1
        }else{
            val drawableTop = ContextCompat.getDrawable(this, R.drawable.circle)
            button.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null)
            grid[index0][index1] = -1
        }
        turnCount++
        if(!winCheck()) drawCheck()
        playerTurn()
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
    private fun winCheck(): Boolean {
        for (row in grid.indices) {
            var count = 0
            for (col in grid[row].indices) {
                count = grid[row][col] + count
            }
            if(countCheck(count)) return true
        }
        for (col in grid.indices) {
            var count = 0
            for (row in grid[col].indices) {
                count = grid[row][col] + count
            }
            if(countCheck(count)) return true
        }
        var count = 0
        for(i in 2 downTo 0){
            count = count + grid[i][i]
        }
        if(countCheck(count)) return true
        count = 0
        for(i in 2 downTo 0){
            count = count + grid[i][2-i]
        }
        if(countCheck(count)) return true
        return false
    }
    private fun countCheck(count: Int): Boolean {
        var player1Win = "Winner is " + player1Name
        var player2Win = "Winner is " + player2Name
        if(count == 3){
            Toast.makeText(this@MainActivity,player1Win , Toast.LENGTH_SHORT).show()
            win = true
            start.text = "RESTART"
            return true
        }
        if(count == -3){
            Toast.makeText(this@MainActivity, player2Win, Toast.LENGTH_SHORT).show()
            win = true
            start.text = "RESTART"
            var player1Temp = player1Name
            player1Name = player2Name
            player2Name = player1Temp
            return true
        }
        return false
    }
    private fun playerTurn(){
        if(turnCount % 2 == 0){
            var turn = "current player: $player1Name"
            playerTurnView.text = turn
        }else{
            var turn = "current player: $player2Name"
            playerTurnView.text = turn
        }
    }

}