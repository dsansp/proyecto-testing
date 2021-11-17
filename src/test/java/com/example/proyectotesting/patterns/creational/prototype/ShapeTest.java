package com.example.proyectotesting.patterns.creational.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ShapeTest {
    private String color;
    @BeforeEach
    void setUp() {
    }

    @Test
    void getColor() {
        Circle newCircle = new Circle("red",1 );
        String color = newCircle.getColor();

        assertEquals("red", color);
    }

    @Test
    void setColor() {
        Circle newCircle = new Circle("red",1 );
        newCircle.setColor("verde");
        String color = newCircle.getColor();
        assertEquals("verde", color);
    }

}