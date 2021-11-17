package com.example.proyectotesting.patterns.creational.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CircleTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void copy() {
        int radius = 1;
        Circle newCircle = new Circle("red",radius );

        assertEquals(1,newCircle.radius);
        assertEquals("red", newCircle.getColor());

        Shape forma = newCircle.copy();
    }
}