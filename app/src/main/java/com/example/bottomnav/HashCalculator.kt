package com.example.bottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class HashCalculator : AppCompatActivity() {
    lateinit var editTextNumber1: EditText
    lateinit var editTextNumber2: EditText
    lateinit var textViewResult: TextView
    private val calculator = Calculator()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hash_calculator)

        editTextNumber1 = findViewById(R.id.editTextNumber1)
        editTextNumber2 = findViewById(R.id.editTextNumber2)
        textViewResult = findViewById(R.id.textViewResult)

        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)

        buttonAdd.setOnClickListener {
            calculateResult('+')
        }

        buttonSubtract.setOnClickListener {
            calculateResult('-')
        }

        buttonMultiply.setOnClickListener {
            calculateResult('*')
        }

        buttonDivide.setOnClickListener {
            calculateResult('/')
        }
    }

    fun calculateResult(operator: Char) {

        val number1 = editTextNumber1.text.toString().toDouble()
        val number2 = editTextNumber2.text.toString().toDouble()

        val result: Double = calculator.calculate(operator, number1, number2)

        textViewResult.text = result.toString()
    }
}