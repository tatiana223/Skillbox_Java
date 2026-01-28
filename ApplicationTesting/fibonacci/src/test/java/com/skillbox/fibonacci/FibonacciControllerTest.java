package com.skillbox.fibonacci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class FibonacciControllerTest extends PostgresTestContainerInitializer {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /fibonacci/{index} с допустимым индексом -> 200 и число Фибоначчи")
    public void testGetFibonacciNumber_ValidIndex_ReturnsOk() throws Exception {
        // Arrange
        int index = 5;
        String expectedJson = "{\"index\":5,\"value\":5}"; // Для 5-го числа Фибоначчи

        // Act & Assert
        mockMvc.perform(get("/fibonacci/{index}", index))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    @DisplayName("GET /fibonacci/{index} с индексом 1 -> 200 и значение 1")
    public void testGetFibonacciNumber_FirstIndex_ReturnsOne() throws Exception {

        mockMvc.perform(get("/fibonacci/{index}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(1));
    }

    @Test
    @DisplayName("GET /fibonacci/{index}  с индексом 2 -> 200 и значение 1")
    public void testGetFibonacciNumber_SecondIndex_ReturnsOne() throws Exception {
        mockMvc.perform(get("/fibonacci/{index}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(1));
    }

    @Test
    @DisplayName("GET /fibonacci/{index} с индексом 0 -> 400 и сообщение об ошибке")
    public void testGetFibonacciNumber_ZeroIndex_ReturnsBadRequest() throws Exception {

        mockMvc.perform(get("/fibonacci/{index}", 0))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Index should be greater or equal to 1"));
    }

    @Test
    @DisplayName("GET /fibonacci/{index} с индексом < 0 -> 400 и сообщение об ошибке")
    public void testGetFibonacciNumber_NegativeIndex_ReturnsBadRequest() throws Exception {

        mockMvc.perform(get("/fibonacci/{index}", -10))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Index should be greater or equal to 1"));
    }

    @Test
    @DisplayName("GET /fibonacci/{index} c большим индексом и правильным значением -> 200")
    public void testGetFibonacciNumber_LargeIndex_ReturnsCorrectValue() throws Exception {

        int index = 10;
        int expectedValue = 55; // 10-е число Фибоначчи

        mockMvc.perform(get("/fibonacci/{index}", index))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(expectedValue));
    }
}
