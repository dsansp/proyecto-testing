package com.example.proyectotesting.patterns.structural.decorator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitalPhotoTest {
    @Test
    void show() {
        DigitalPhoto digital= new DigitalPhoto();
        assertTrue(digital.show().contains("Normal photo without edit"));
    }

    @Test
    void cost() {
        DigitalPhoto digital= new DigitalPhoto();
        assertEquals(15,digital.cost());
    }

}