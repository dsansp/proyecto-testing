package com.example.proyectotesting.patterns_test.creational.prototype.shopcart;

import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.patterns.behavioral.strategy.ShopCart;
import com.example.proyectotesting.patterns.structural.adapter.Main;
import com.example.proyectotesting.repository.DirectionRepository;
import com.example.proyectotesting.service.DirectionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MainTest {
    Product juego;
    ShopCart cart;
    @BeforeEach
    void setUp() {
        juego = mock(Product.class);
        this.cart = new ShopCart();
    }

    @Test
    void main() {
        //when((cart.addProduct(juego))).thenReturn(true);

        Main.main(new String[] {});

        //assertTrue(cart.addProduct(juego));
    }
}