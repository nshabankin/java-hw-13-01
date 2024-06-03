package ru.netology.javaqa.javaqamvn.repository;

import ru.netology.javaqa.javaqamvn.domain.Product;
import ru.netology.javaqa.javaqamvn.exceptions.AlreadyExistsException;
import ru.netology.javaqa.javaqamvn.exceptions.NotFoundException;

public class ShopRepository {
    private Product[] products = new Product[0];

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    /**
     * Метод добавления товара в репозиторий с проверкой, если id уже существует
     *
     * @param product — добавляемый товар
     */
    public void add(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException(
                    "Product with id: " + product.getId() + " already exists"
            );
        }
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    // Добавить метод findById
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    // Обновленный и переименованный в removeById метод удаления по ID
    public void removeById(int id) {
        Product foundProduct = findById(id);
        if (foundProduct == null) {
            throw new NotFoundException(
                    "Element with id: " + id + " not found"
            );
        }

        Product[] tmp = new Product[products.length - 1];

        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }
}