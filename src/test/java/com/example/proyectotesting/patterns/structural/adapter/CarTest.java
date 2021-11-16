package com.example.proyectotesting.patterns.structural.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
    }


    @DisplayName("Comprobar que se guarda una velocidad")
    @Test
    void setSpeed() {
        car.setSpeed(130D);
        assertEquals(130D, car.getSpeed());
    }
    @DisplayName("Comprobar se lee una velocidad")
    @Test
    void getSpeed() {
        //car = new Car();  ya se inición en el beforeEach
        //car.getSpeed();   no se está guardando en ninguna variable, no hace falta
        car.setSpeed(120D);
        double result=car.getSpeed();
        //assertNotNull(result);
        assertEquals(120D,result);
    }
    @DisplayName("Comprobar que se aumenta la velocidad")
    @Test
    void speedUpTest() {
        double faster = car.getSpeed();
        car.speedUp(5D); //Corregido que no estaba entrando en speedUp
        //assertTrue(car.getSpeed() == car.getSpeed()+faster);
        assertEquals(faster+5D, car.getSpeed()); //Corregido para que compruebe el valor de la velocidad + 5
    }

}