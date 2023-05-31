package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    private var currentNumber: Double = 0.0
    private var currentOperator: String? = null
    private var operand: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewResult = findViewById(R.id.textViewResult)
    }

    fun onDigitClick(view: View) {
        val button = view as Button
        val digit = button.text.toString()

        textViewResult.append(digit)
    }

    fun onDecimalClick(view: View) {
        if (!textViewResult.text.contains('.')) {
            textViewResult.append(".")
        }
    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        val operator = button.text.toString()

        if (textViewResult.text.isNotEmpty()) {
            currentNumber = textViewResult.text.toString().toDouble()
        }

        currentOperator = operator
        textViewResult.text = ""
    }

    fun onClearClick(view: View) {
        textViewResult.text = ""
        currentNumber = 0.0
        currentOperator = null
        operand = 0.0
    }

    fun onEqualsClick(view: View) {
        if (textViewResult.text.isNotEmpty()) {
            operand = textViewResult.text.toString().toDouble()

            val result = when (currentOperator) {
                "+" -> currentNumber + operand
                "-" -> currentNumber - operand
                "*" -> currentNumber * operand
                "/" -> {
                    if (operand != 0.0) {
                        currentNumber / operand
                    } else {
                        Toast.makeText(this, "Ошибка: Деление на ноль", Toast.LENGTH_SHORT).show()
                        return
                    }
                }

                "%" -> currentNumber * (operand / 100)
                else -> return
            }

            textViewResult.text = result.toString()
        }
    }

    fun onChangeSignClick(view: View) {
        if (textViewResult.text.isNotEmpty()) {
            val currentValue = textViewResult.text.toString().toDouble()
            val newValue = -currentValue
            textViewResult.text = newValue.toString()
        }
    }
}

