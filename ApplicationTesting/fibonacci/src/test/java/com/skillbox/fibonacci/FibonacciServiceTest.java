package com.skillbox.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FibonacciServiceTest {


    @Mock
    private FibonacciRepository repository;

    @Mock
    private FibonacciCalculator calculator;

    private FibonacciService service;

    @BeforeEach
    void setUp() {
        service = new FibonacciService(repository, calculator);
    }

    @Test
    @DisplayName("Должен вернуть число из БД, если оно там есть")
    public void testShouldReturnNumberFromDbWhenExists() {

        int index = 5;
        FibonacciNumber expectedNumber = new FibonacciNumber(index, 5);
        when(repository.findByIndex(index)).thenReturn(Optional.of(expectedNumber));

        FibonacciNumber result = service.fibonacciNumber(index);

        assertEquals(expectedNumber, result);
        verify(repository).findByIndex(index);
        verifyNoInteractions(calculator);
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Должен рассчитать и сохранить число, если его нет в БД")
    public void testShouldCalculateAndSaveNumberWhenNotInDb() {

        int index = 6;
        when(repository.findByIndex(index)).thenReturn(Optional.empty());
        when(calculator.getFibonacciNumber(index)).thenReturn(8);

        FibonacciNumber result = service.fibonacciNumber(index);

        assertEquals(index, result.getIndex());
        assertEquals(8, result.getValue());
        verify(repository).findByIndex(index);
        verify(calculator).getFibonacciNumber(index);
        verify(repository).save(result);
    }

    @Test
    @DisplayName("Должен выбросить исключение при индексе меньше 1")
    public void testShouldThrowExceptionForIndexLessThan1() {

        int invalidIndex = 0;

        assertThrows(IllegalArgumentException.class, () -> {
            service.fibonacciNumber(invalidIndex);
        });

        verifyNoInteractions(repository);
        verifyNoInteractions(calculator);
    }

    @Test
    @DisplayName("Должен выбросить исключение при отрицательном индексе")
    public void testShouldThrowExceptionForNegativeIndex() {
        int invalidIndex = -5;

        assertThrows(IllegalArgumentException.class, () -> {
            service.fibonacciNumber(invalidIndex);
        });

        verifyNoInteractions(repository);
        verifyNoInteractions(calculator);
    }
}
