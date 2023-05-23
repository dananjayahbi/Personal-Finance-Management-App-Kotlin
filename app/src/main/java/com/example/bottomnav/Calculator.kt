package com.example.bottomnav

class Calculator {
    fun add(number1: Double, number2: Double): Double {
        return number1 + number2
    }

    fun subtract(number1: Double, number2: Double): Double {
        return number1 - number2
    }

    fun multiply(number1: Double, number2: Double): Double {
        return number1 * number2
    }

    fun divide(number1: Double, number2: Double): Double {
        if (number2 == 0.0) {
            throw IllegalArgumentException("Cannot divide by zero")
        }
        return number1 / number2
    }

    fun calculate(operator: Char, number1: Double, number2: Double): Double {
        return when (operator) {
            '+' -> add(number1, number2)
            '-' -> subtract(number1, number2)
            '*' -> multiply(number1, number2)
            '/' -> divide(number1, number2)
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
}