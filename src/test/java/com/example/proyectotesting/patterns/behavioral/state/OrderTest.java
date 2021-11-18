
package com.example.proyectotesting.patterns.behavioral.state;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class OrderTest {
    Order order;

    @BeforeEach
    void setUp() {
        order = new Order(1L, LocalDateTime.now());
    }

    @Test
    @DisplayName("Comprueba que pasa de estado el pedido")
    void nextState() {
        order.setState(mock(ProcessingState.class));
        doNothing().when(order.getState()).next(order);

        order.nextState();

        verify(order.getState()).next(order);
    }

    @Test
    @DisplayName("Comprueba que el estado pasa al anterior")
    void previousState() {
        order.setState(mock(ProcessingState.class));
        doNothing().when(order.getState()).previous(order);

        order.previousState();
        verify(order.getState()).previous(order);
    }
    @Test
    void setId() {
        order.setId(2L);
        assertEquals(2L,order.getId());
    }

    @Test
    void setDate() {
        LocalDateTime time = LocalDateTime.now();
        order.setDate(time);
        assertEquals(time,order.getDate());
    }

    @Test
    void setProducts() {
        List<Product> products = new ArrayList<>();
        order.setProducts(products);
        assertEquals(products,order.getProducts());
    }

    @Test
    void setShippedState() {
        OrderState state = new ShippedState();
        order.setState(state);
        assertEquals(state, order.getState());
    }

}



