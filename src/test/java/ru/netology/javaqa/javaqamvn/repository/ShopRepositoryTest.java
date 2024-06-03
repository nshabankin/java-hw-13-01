package ru.netology.javaqa.javaqamvn.repository;

import org.junit.jupiter.api.Test;
import ru.netology.javaqa.javaqamvn.domain.Product;
import ru.netology.javaqa.javaqamvn.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {

    // объект класса для тестов
    ShopRepository repo = new ShopRepository();

    // тестовые данные
    Product product1 = new Product(1, "Product 1", 100);
    Product product2 = new Product(2, "Product 2", 200);


    @Test
    public void shouldRemoveExistingProduct() {

        // тестовые действия
        repo.add(product1);
        repo.add(product2);
        repo.removeById(1);

        // ожидаемый результат
        Product[] expected = {product2};

        // фактический результат
        Product[] actual = repo.findAll();

        // проверка на соответствие
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenRemovingNonExistingProduct() {

        // подготовительные действия
        repo.add(product1);
        repo.add(product2);

        // тестовые действия
        Exception exception = assertThrows(NotFoundException.class, () ->
                repo.removeById(3));

        // ожидаемый результат
        String expected = "Element with id: 3 not found";

        // фактический результат
        String actual = exception.getMessage();

        // проверка на соответствие
        assertEquals(expected, actual);
    }
}
