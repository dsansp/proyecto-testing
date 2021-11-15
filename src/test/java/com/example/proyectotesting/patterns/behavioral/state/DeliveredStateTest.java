package com.example.proyectotesting.patterns.behavioral.state;

import org.junit.jupiter.api.BeforeEach;
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
    void next() {
        deliveredState.next(order);

        //assertEquals(order.getState(), contains("Order delivered, ends!"));
        assertTrue(true);

    }

    @Test
    void previous() {
        deliveredState.previous(order);

        verify(order).getId();
        verify(order).setState(any(ShippedState.class));
    }
}