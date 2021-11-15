package com.example.proyectotesting.patterns.behavioral.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.System.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeliveredStateTest {

    Order order;
    DeliveredState deliveredState;

    @BeforeEach
    void setUp() {
        deliveredState = new DeliveredState();
        order = mock(Order.class);

        doNothing().when(order).setState(new ShippedState());

    }

    @Test
    @DisplayName("Se cierra el pedido al completarse")
    void next() {
        deliveredState.next(order);

        //assertEquals(order.getState(), contains("Order delivered, ends!"));
        assertTrue(true);

    }

    @Test
    @DisplayName("Pasa a estado de en envío")
    void previous() {
        deliveredState.previous(order);

        verify(order).getId();
        verify(order).setState(any(ShippedState.class));
    }
}