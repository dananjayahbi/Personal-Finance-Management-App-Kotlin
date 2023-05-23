package com.example.bottomnav

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class HashCalTesting {

    private lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = Calculator()
    }

    @Test
    fun testAddition() {
        val result = calculator.add(5.0, 3.0)
        assertEquals(8.0, result, 0.0)
    }

    @Test
    fun testSubtraction() {
        val result = calculator.subtract(7.0, 2.0)
        assertEquals(5.0, result, 0.0)
    }

    @Test
    fun testMultiplication() {
        val result = calculator.multiply(4.0, 6.0)
        assertEquals(24.0, result, 0.0)
    }

    @Test
    fun testDivision() {
        val result = calculator.divide(10.0, 2.0)
        assertEquals(5.0, result, 0.0)
    }

    @Test
    fun testDivisionByZero() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.divide(10.0, 0.0)
        }
    }

    @Test
    fun testInvalidOperator() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.calculate('@', 5.0, 3.0)
        }
    }
}