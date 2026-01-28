package com.skillbox.fibonacci;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FibonacciRepositoryTest extends PostgresTestContainerInitializer {

    @Autowired
    FibonacciRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Необходимо сохранить новое число Фибоначчи в базе данных.")
    public void testShouldSaveNewFibonacciNumber() {

        FibonacciNumber number = new FibonacciNumber(5, 5);

        repository.save(number);
        entityManager.flush();
        entityManager.detach(number);


        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT index, value FROM fibonacci_number WHERE index = 5",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );

        assertEquals(1, actual.size());
        assertEquals(5, actual.get(0).getIndex());
        assertEquals(5, actual.get(0).getValue());
    }

    @Test
    @DisplayName("Необходимо найти число Фибоначчи по индексу")
    public void testSouldFindByIndex() {
        FibonacciNumber number = new FibonacciNumber(6, 8);
        repository.save(number);
        entityManager.flush();
        entityManager.detach(number);

        Optional<FibonacciNumber> found = repository.findByIndex(6);

        assertTrue(found.isPresent());
        assertEquals(6, found.get().getIndex());
        assertEquals(8, found.get().getValue());
    }

    @Test
    @DisplayName("Не следует создавать дубликаты для одного и того же индекса.")
    public void testShouldNotCreateDuplicates() {

        FibonacciNumber number = new FibonacciNumber(7, 13);
        repository.save(number);
        entityManager.flush();
        entityManager.clear();


        number.setValue(21);
        repository.save(number);
        entityManager.flush();

        Optional<FibonacciNumber> updated = repository.findByIndex(7);
        assertTrue(updated.isPresent());
        assertEquals(21, updated.get().getValue());
    }

    @Test
    @DisplayName("Должен возвращать пустое необязательное значение, если число не найдено")
    public void shouldReturnEmptyWhenNotFound() {

        Optional<FibonacciNumber> found = repository.findByIndex(999);

        assertFalse(found.isPresent());
    }
}
