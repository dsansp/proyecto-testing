package com.example.proyectotesting.patterns_test.behavioral.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ShippedStateTest {

    Order order;
    ShippedState shippedState;

    @BeforeEach
    void setUp() {
        order = mock(Order.class);
        shippedState = new ShippedState();

        doNothing().when(order).setState(new DeliveredState());
        doNothing().when(order).setState(new ProcessingState());
        when(order.getId()).thenReturn(1L);
    }

    @Test
    @DisplayName("Pasa el envio a estado entregado")
    void next() {
        shippedState.next(order);

        verify(order).getId();
        verify(order).setState(any(DeliveredState.class));

    }

    @Test
    @DisplayName("El envio se encuentra en proceso")
    void previous() {
        shippedState.previous(order);

        verify(order).getId();
        verify(order).setState(any(ProcessingState.class));
    }
}