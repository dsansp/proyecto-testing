package com.example.proyectotesting.patterns.creational.prototype.shopcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void classProduct() {
        Product product = new Product();
        Long valueId = product.id;
        String valueName = product.name;
        Double valuePrice = product.price;

        assertNull(valueId);
        assertNull(valueName);
        assertNull(valuePrice);
    }

}