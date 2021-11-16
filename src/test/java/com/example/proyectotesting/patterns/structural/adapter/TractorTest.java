package com.example.proyectotesting.patterns.structural.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TractorTest {
    Tractor tractor;


    @BeforeEach
    void setUp(){
        tractor = new Tractor();}

    @Test
    void setSpeed() {
        tractor.setSpeed(130);
        assertEquals(130,tractor.getSpeed());
    }

    @Test
    @DisplayName("Cambia primera marchar")
    void changeMode1() {
        tractor.changeMode(1);
        assertEquals(5,tractor.getSpeed());
    }
    @Test
    @DisplayName("Cambia a segunda marcha")
    void changeMode2() {
        tractor.changeMode(2);
        assertEquals(15,tractor.getSpeed());
    }
    @Test
    @DisplayName("No cambia a ninguna marcha")
    void changeModeNull() {
        tractor.setSpeed(0);
        tractor.changeMode(0);
        assertEquals(0,tractor.getSpeed());
    }

}