package com.example.proyectotesting.patterns.structural.facade.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NFCSensorTest {
    NFCSensor nfcSensor;

    @BeforeEach
    void setUp() {
        nfcSensor = new NFCSensor();
    }
    @DisplayName("Verificamos que els nfcSensor arranca")
    @Test
    void start() {
        try {
            nfcSensor.start();
        }catch(Exception error){
            error.printStackTrace();
            assertTrue(false);
        }
    }
    @DisplayName("Verificamos que els nfcSensor se detiene")
    @Test
    void stop() {
        try {
            nfcSensor.stop();
            assertTrue(true);
        }catch(Exception error){
            error.printStackTrace();
            assertTrue(false);
        }
    }
}