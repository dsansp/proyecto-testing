package com.example.proyectotesting.patterns_test.behavioral.state.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

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
    void next() {
        state.next(order);

        verify(order).getId();
        verify(order).setState(any(ShippedState.class));

    }

    @Test
    void previous() {
        state.previous(order);
    }
}