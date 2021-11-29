package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Category;
import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.DirectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DirectionServiceImplTest {
    DirectionService directionService;
    DirectionRepository directionRepository;

    @BeforeEach
    void setUp() {
        directionRepository = mock(DirectionRepository.class);
        this.directionService = new DirectionServiceImpl(directionRepository);
    }

    @Test
    void DirectionClassTest(){
        Direction direction = new Direction();

        Manufacturer made = new Manufacturer();

        direction.setManufacturer(made);

        assertEquals(made,direction.getManufacturer());
        assertNotNull(direction);

        direction.toString();



    }





    @DisplayName("Comprobar que se encuentran todas las direcciones")
    @Nested
    class findTest {
        @DisplayName("Comprobar que se encuentran todas")
        @Test
        void findAllTest() {
            List<Direction> directions = Arrays.asList(
                    new Direction("León y Castillo", "35003", "Las Palmas de G.C.", "España"),
                    new Direction("Mesa y López", "35007","Las Palmas de G.C.", "España"));

            when(directionRepository.findAll()).thenReturn(directions);
            List<Direction> result = directionService.findAll();

            assertNotNull(result);
            assertEquals(2, result.size());
            verify(directionRepository).findAll();
        }
        @DisplayName("Comprobar que la lista de direcciones está vacía")
        @Test
        void findAllEmptyTest() {
            List<Direction> directions = Arrays.asList(
                    new Direction("León y Castillo", "35003", "Las Palmas de G.C.", "España"),
                    new Direction("Mesa y López", "35007","Las Palmas de G.C.", "España"));

            directionService.deleteAll();
            List<Direction> result = directionService.findAll();

            assertEquals(0, result.size());
        }



        @DisplayName("Comprobar está vacío con  Id nula")
        @Test
        void findOneNullTest() {
            Optional<Direction> direction = directionService.findOne(null);

            assertEquals(Optional.empty(), direction);
        }

        @DisplayName("Comprobar que está vacío con Id negativa")
        @Test
        void findOneNegativeTest() {
            Optional<Direction> direction = directionService.findOne(-3L);

            assertEquals(Optional.empty(), direction);
        }

        @DisplayName("Comprobar que está vacío con Id cero")
        @Test
        void findOneZeroTest() {
            Optional<Direction> direction = directionService.findOne(0L);

            assertEquals(Optional.empty(), direction);
        }

        @Test
        void findOneExceptionsTest() {

            doThrow(IllegalArgumentException.class).when(directionRepository).findById(30L);

            Optional<Direction>resultopt = directionService.findOne(30L);
            assertThrows(IllegalArgumentException.class, () -> directionRepository.findById(30L));

            assertNotNull(resultopt);
        }

    }

    @DisplayName("Comprobar que existe una dirección")
    @Nested
    class existTest {
        @DisplayName("Comprobar que encuentra una Id")
        @Test
        void existsByIdOkTest() {
            when(directionRepository.existsById(1L)).thenReturn(true);
            boolean direction = directionService.existsById(1L);
            assertTrue(direction);
        }

        @DisplayName("Comprobar que no encuentra una Id no guardada")
        @Test
        void NotexistsByIdTest() {
            when(directionRepository.existsById(99L)).thenReturn(false);
            boolean direction = directionService.existsById(99L);
            assertFalse(direction);
        }
    }

    @DisplayName("Comprobar que guarda una dirección")
    @Nested
    class saveTest {

        @DisplayName("Comprobar que guarda bien una dirección")
        @Test
        void saveOKTest() {
            Direction direccion1 = new Direction("León y Castillo", "35003", "Las Palmas de G.C.", "España");
            when(directionRepository.save(any())).thenReturn(direccion1);
            Direction result = directionService.save(direccion1);
            assertNotNull(result);
            assertEquals("León y Castillo", result.getStreet());
            assertEquals("35003", result.getPostalCode());
            assertEquals("Las Palmas de G.C.", result.getCity());
            assertEquals("España", result.getCountry());
        }

        @DisplayName("Comprobar que no guarda con una dirección nula")
        @Test
        void saveNullTest() {
            when(directionRepository.save(any())).thenReturn(null);

            Direction result = directionService.save(null);

            assertNull(directionService.save(null));
        }
    }

    @DisplayName("Comprobar que cuenta direcciones")
    @Nested
    class countTests {

        @DisplayName("Comprobar que cuenta direcciones")
        @Test
        void countOKTest() {
            when(directionRepository.count()).thenReturn(2L);
            Long result = directionService.count();
            assertNotNull(result);
            assertEquals(2, result);
        }

        @DisplayName("Comprobar cuando cuenta lista de dircciones vacía")
        @Test
        void countNullTest() {
            when(directionRepository.count()).thenReturn(0L);
            Long result = directionService.count();
            assertNotNull(result);
            assertEquals(0, result);
        }
    }

    @DisplayName("Comprobar que borra direcciones")
    @Nested
    class deleteTests {

        @DisplayName("Comprobar que no borra con una Id nula")
        @Test
        void deleteByIdNullTest() {
            directionService.deleteById(null);
            boolean result = directionService.deleteById(null);
            assertFalse(result);
        }

        @DisplayName("Comprobar que  borra por una Id")
        @Test
        void deleteByIdOkTest() {

            Direction direction1 = new Direction("León y Castillo", "35003", "Las Palmas de G.C.", "España");

            Optional<Direction> directionoptional = Optional.of(direction1);
            when(directionRepository.findById(0L)).thenReturn(directionoptional);

            boolean ok = directionService.deleteById(0L);
            assertTrue(ok);
        }

        @DisplayName("Comprobar que borra todas las direcciones")
        @Test
        void deleteAllOk() {
            boolean result = directionService.deleteAll();
            assertTrue(result);
            verify(directionRepository).deleteAll();
        }
        @DisplayName("Comprobar la excepción al borrar todas las direcciones")
        @Test
        void deleteAllExceptionTest() {
            doThrow(RuntimeException.class).when(directionRepository).deleteAll();

            boolean result = directionService.deleteAll();
            assertThrows(Exception.class, () -> directionRepository.deleteAll());
            verify(directionRepository, times(2)).deleteAll();
            assertFalse(result);

        }
    }

    @DisplayName("Comprobar que encuentra por ciudad y pais")
    @Test
    void findByCityAndCountryTest() {
        List<Direction> directions = Arrays.asList(
                new Direction("León y Castillo", "2801", "Madrid", "España"),
                new Direction("Velazquez", "28967", "Madrid", "Alemania"),
                new Direction("Paseo de la Castellana", "28080", "Madrid", "España"));

        when(directionRepository.findByCityAndCountry(any(),any())).thenReturn(directions);
        List<Direction> result =  directionService.findByCityAndCountry("Madrid", "España");

        assertNotNull(result);
        assertEquals("Paseo de la Castellana", result.get(2).getStreet());
        verify(directionRepository).findByCityAndCountry("Madrid","España");
    }

    @DisplayName("Comprobar que no encuentra por ciudad y pais si está vacío")
    @Test
    void findNullByCityAndCountryTest() {
        List<Direction> directions = new ArrayList<>();

        when(directionRepository.findByCityAndCountry(null,null)).thenReturn(directions);
        List<Direction> result =  directionService.findByCityAndCountry(null, null);

        assertNotNull(result);
    }

}