package com.example.proyectotesting.patterns.creational.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmpleadoFactoryTest {

    EmpleadoFactory empleadoFactory;

    @BeforeEach
    void setUp() {
        empleadoFactory = new EmpleadoFactory();
    }

    @Test
    void getEmpleadoMECANICOTest() {
        Empleado empleado1;
           // Empleado return = empleadoFactory.
    }

    @Test
    void getEmpleadoPROGRAMADORTest() {
        Empleado empleado1;
          //  Empleado return = empleadoFactory.
    }

    @Test
    void getEmpleadoIllegalArgumentExceptionTest() {
        Empleado empleado1;
           // Empleado return = empleadoFactory.
    }
}