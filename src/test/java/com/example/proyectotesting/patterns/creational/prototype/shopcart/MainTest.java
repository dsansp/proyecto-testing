package com.example.proyectotesting.patterns.creational.prototype.shopcart;

import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.patterns.behavioral.strategy.ShopCart;
import com.example.proyectotesting.patterns.structural.adapter.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

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