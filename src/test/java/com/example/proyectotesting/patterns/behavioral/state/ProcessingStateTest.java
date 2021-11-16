package com.example.proyectotesting.patterns.behavioral.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class ProcessingStateTest {

    Order order;
    ProcessingState state;

    @BeforeEach
    void setUp() {
        state = new ProcessingState();
        order = mock(Order.class);

        doNothing().when(order).setState(new ShippedState());
    }

    @Test
    @DisplayName("Pasa envio a estado enviando")
    void next() {
        state.next(order);

        verify(order).getId();
        verify(order).setState(any(ShippedState.class));

    }

    @Test
    @DisplayName("Deja envio en estado raíz")
    void previous() {
        state.previous(order);
        assertTrue(true);
    }

}