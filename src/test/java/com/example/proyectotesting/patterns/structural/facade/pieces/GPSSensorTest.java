package com.example.proyectotesting.patterns.structural.facade.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GPSSensorTest {

    GPSSensor gpsSensor;

    @BeforeEach
    void setUp() {
        gpsSensor = new GPSSensor();
    }
    @DisplayName("Verificamos que els gpscSensor arranca")
    @Test
    void start() {
        try {
            gpsSensor.start();
        }catch(Exception error){
            error.printStackTrace();
            assertTrue(false);
        }
    }
    @DisplayName("Verificamos que els gpsSensor se detiene")
    @Test
    void stop() {
        try {
            gpsSensor.stop();
            assertTrue(true);
        }catch(Exception error){
            error.printStackTrace();
            assertTrue(false);
        }
    }
}