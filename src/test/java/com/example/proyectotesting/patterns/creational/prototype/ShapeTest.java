package com.example.proyectotesting.patterns.creational.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
 class ShapeTest extends Shape {
    String color;

    protected ShapeTest(String color) {
        super(color);
    }

     public Shape copy(){
        Shape shape = null;
        return (shape);
     }

    @BeforeEach
    void setUp() {
    }

    @Test
    void getColorTest() {
        String colorReturn;
        ShapeTest shapeTest = new ShapeTest("rojo");
        colorReturn = shapeTest.getColor();
        assertEquals("rojo", colorReturn);
    }

    @Test
    void setColorTest() {
    }

    @Test
    void copyTest() {
    }
}