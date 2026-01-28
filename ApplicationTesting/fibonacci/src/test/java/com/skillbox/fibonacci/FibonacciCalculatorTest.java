package com.skillbox.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class FibonacciCalculatorTest {

    private FibonacciCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new FibonacciCalculator();
    }

    @Test
    @DisplayName("Должен вернуть 1 для первого числа")
    public void testShouldReturn1ForFirstNumber() {
        assertEquals(1, calculator.getFibonacciNumber(1));
    }

    @Test
    @DisplayName("Должен вернуть 1 для второго числа")
    public void shouldReturn1ForSecondNumber() {
        assertEquals(1, calculator.getFibonacciNumber(2));
    }

    @Test
    @DisplayName("Должен вернуть правильное число для номера 5")
    public void testShouldReturnCorrectValueFor5() {
        assertEquals(5, calculator.getFibonacciNumber(5));
    }

    @Test
    @DisplayName("Должен вернуть правильное число для номера 10")
    public void testShouldReturnCorrectValueFor10() {
        assertEquals(55, calculator.getFibonacciNumber(10));
    }

    @Test
    @DisplayName("Должен выбросить исключение для номера 0")
    public void testShouldThrowExceptionFor0() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.getFibonacciNumber(0));
    }

    @Test
    @DisplayName("Должен выбросить исключение для отрицательного номера")
    public void testShouldThrowExceptionForNegativeNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.getFibonacciNumber(-5));
    }

}
