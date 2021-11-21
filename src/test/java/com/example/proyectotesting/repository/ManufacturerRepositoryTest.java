package com.example.proyectotesting.repository;

import com.example.proyectotesting.entities.Manufacturer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturerRepositoryTest {

    @Autowired
    ManufacturerRepository repository;

    @Test
    void findByYearOKTest() {

        List<Manufacturer> manufacturers1 = repository.findByYear(1949);
        List<Manufacturer> manufacturers2 = repository.findByYear(1977);

        assertAll(
                () -> assertEquals(2,repository.count()),
                () -> assertEquals(1L, manufacturers1.get(0).getId()),
                () -> assertEquals("Adidas", manufacturers1.get(0).getName()),
                () -> assertEquals("2343235325G", manufacturers1.get(0).getCif()),
                () -> assertEquals(60000, manufacturers1.get(0).getNumEmployees()),
                () -> assertEquals("Nike", manufacturers2.get(0).getName()),
                () -> assertEquals("2343235325G", manufacturers2.get(0).getCif()),
                () -> assertEquals(60000, manufacturers2.get(0).getNumEmployees())
        );

    }

    @Test
    void findByYearNullTest() {
        List<Manufacturer> manufacturers1 = repository.findByYear(null);

        assertEquals(2,repository.count());
        assertTrue(manufacturers1.isEmpty());
    }

    @Test
    void findManufacturerByDirectionCountry() {
        List<Manufacturer> manufacturers = repository.findManufacturerByDirectionCountry("Spain");
        assertAll(
                () -> assertEquals(2,repository.count()),
                () -> assertEquals(1L, manufacturers.get(0).getId()),
                () -> assertEquals("Adidas", manufacturers.get(0).getName()),
                () -> assertEquals("2343235325G", manufacturers.get(0).getCif()),
                () -> assertEquals(60000, manufacturers.get(0).getNumEmployees()),
                () -> assertEquals("Nike", manufacturers.get(1).getName()),
                () -> assertEquals("2343235325G", manufacturers.get(1).getCif()),
                () -> assertEquals(60000, manufacturers.get(1).getNumEmployees())
        );
    }
    @Test
    void findManufacturerByDirectionCountryNotContainsTest() {
        List<Manufacturer> manufacturers = repository.findManufacturerByDirectionCountry("Belgium");
        assertEquals(2,repository.count());
        assertTrue(manufacturers.isEmpty());
    }
}