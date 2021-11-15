package com.example.proyectotesting.patterns.behavioral.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

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
}