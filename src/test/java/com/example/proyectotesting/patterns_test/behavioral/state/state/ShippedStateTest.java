package com.example.proyectotesting.patterns_test.behavioral.state.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

class ShippedStateTest {

    Order order;
    ShippedState shippedState;

    @BeforeEach
    void setUp() {
        order = mock(Order.class);
        shippedState = new ShippedState();

        doNothing().when(order).setState(new DeliveredState());
        doNothing().when(order).setState(new ProcessingState());
    }

    @Test
    void next() {
    }

    @Test
    void previous() {
    }
}