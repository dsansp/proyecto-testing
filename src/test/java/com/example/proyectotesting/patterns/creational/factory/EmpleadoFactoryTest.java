package com.example.proyectotesting.patterns.creational.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmpleadoFactoryTest {

    EmpleadoFactory empleadoFactory;

    @BeforeEach
    void setUp() {
        empleadoFactory = new EmpleadoFactory();
    }

    @Test
    void getEmpleadoMECANICOTest() {

        EmpleadoType empleadoType = EmpleadoType.valueOf("MECANICO");

        EmpleadoFactory empleadofactory = new EmpleadoFactory();

        Empleado empleado1 = empleadofactory.getEmpleado(EmpleadoType.MECANICO);

        empleadofactory.getEmpleado(empleadoType);

        assertNotNull(empleadoType);
        assertNotNull(empleado1);

    }

    @Test
    void getEmpleadoPROGRAMADORTest() {
        EmpleadoType empleadoType = EmpleadoType.valueOf("PROGRAMADOR");

        EmpleadoFactory empleadofactory = new EmpleadoFactory();

        Empleado empleado1 = empleadofactory.getEmpleado(EmpleadoType.PROGRAMADOR);

        assertNotNull(empleado1);
        assertNotNull(empleadoType);

    }

    @Disabled
    @Test
    void getEmpleadoIllegalArgumentExceptionTest() {


        EmpleadoFactory empleadofactory = new EmpleadoFactory();


        assertThrows(
                IllegalArgumentException.class,
                () -> empleadofactory.getEmpleado(null));

    }

}
