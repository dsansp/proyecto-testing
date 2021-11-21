
package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.repository.ManufacturerRepository;
import com.example.proyectotesting.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ManufacturerServiceImplTest {
    ManufacturerService manufacturerService;
    ManufacturerRepository manufacturerRepository;
    ProductRepository productRepository;



    @BeforeEach
    void setUp() {
        manufacturerRepository = mock(ManufacturerRepository.class);
        this.manufacturerService = new ManufacturerServiceImpl(manufacturerRepository, productRepository);

    }

    @Test
    void count() {
        when(manufacturerRepository.count()).thenReturn(2L);
        Long result= manufacturerService.count();

        assertNotNull(result);
        assertEquals(2,result);
    }
    @DisplayName("Comprobar cuando cuenta lista de manufacturers vacía")
    @Test
    void countNullTest() {
        when(manufacturerRepository.count()).thenReturn(0L);
        Long result = manufacturerService.count();
        assertNotNull(result);
        assertEquals(0, result);
    }

    @Test
    void findAllTest() {
        List<Manufacturer> manufacturers = Arrays.asList(
                new Manufacturer("name1","cif1", 5,2021),
                new Manufacturer("name2", "cif2", 3, 2019));

        when(manufacturerRepository.findAll()).thenReturn(manufacturers);

        List<Manufacturer> result = manufacturerService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(manufacturerRepository).findAll();
    }

    @Test
    void findOneIdTest() {
        List<Manufacturer> manufacturers = new ArrayList<>();

        Manufacturer manufacturer1 = new Manufacturer("name1", "12345", 4, 2007);
        manufacturer1.setId(1L);
        manufacturers.add(manufacturer1);

        assertEquals(1, manufacturers.size());

        Optional<Manufacturer> manufacturer = manufacturerService.findOne(1L);
        assertNotNull(manufacturer);
        assertTrue(manufacturer.isEmpty());
    }

    @Test
    void findOneNullTest() {
        Optional<Manufacturer> manufacturer = manufacturerService.findOne((Long) null);
        assertTrue(manufacturer.isEmpty());
    }

    @Test
    void findByYear() {
        List<Manufacturer> manufacturers1 = Arrays.asList(
                new Manufacturer("Adidas", "123456A", 55000, 1936),
                new Manufacturer("Reebok", "12345678C", 65000, 1936)
        );
        List<Manufacturer> manufacturers2 = Arrays.asList(
                new Manufacturer("Nike", "1234567B", 78000, 1946),
                new Manufacturer("Puma", "123456789D", 35000, 1946)
        );

        when(manufacturerRepository.findByYear(1936)).thenReturn(manufacturers1);
        when(manufacturerRepository.findByYear(1946)).thenReturn(manufacturers2);
        List<Manufacturer> manufacturersOne = manufacturerService.findByYear(1936);
        List<Manufacturer> manufacturersTwo = manufacturerService.findByYear(1946);
        assertAll(
                () -> assertFalse(manufacturersOne.isEmpty()),
                () -> assertEquals("Adidas", manufacturersOne.get(0).getName()),
                () -> assertEquals("123456A", manufacturersOne.get(0).getCif()),
                () -> assertEquals(55000, manufacturersOne.get(0).getNumEmployees()),
                () -> assertEquals("Reebok", manufacturersOne.get(1).getName()),
                () -> assertEquals("12345678C", manufacturersOne.get(1).getCif()),
                () -> assertEquals(65000, manufacturersOne.get(1).getNumEmployees()),
                () -> assertFalse(manufacturersTwo.isEmpty()),
                () -> assertEquals("Nike", manufacturersTwo.get(0).getName()),
                () -> assertEquals("1234567B", manufacturersTwo.get(0).getCif()),
                () -> assertEquals(78000, manufacturersTwo.get(0).getNumEmployees()),
                () -> assertEquals("Puma", manufacturersTwo.get(1).getName()),
                () -> assertEquals("123456789D", manufacturersTwo.get(1).getCif()),
                () -> assertEquals(35000, manufacturersTwo.get(1).getNumEmployees())
        );
        verify(manufacturerRepository).findByYear(1936);
        verify(manufacturerRepository).findByYear(1946);
    }

    @Test
    void findYearNullTest() {

        List<Manufacturer> manufacturer =
                manufacturerService.findByYear(null);
        assertTrue(manufacturer.isEmpty());
    }

    @Test
    void findYearEmptyTest() {

        List<Manufacturer> manufacturer =
                manufacturerService.findByYear(0);
        assertTrue(manufacturer.isEmpty());
    }

    @Test
    void saveOKTest() {
        when(manufacturerRepository.save(any(Manufacturer.class)))
                .thenReturn(new Manufacturer());
        Manufacturer manufacturer = manufacturerService.save(new Manufacturer());
        assertNotNull(manufacturer);

    }

    @Test
    void saveNullTest() {
        assertNull(manufacturerService.save(null));
    }



    @Test
    @DisplayName("Borrar un fabricante por id")
    void deleteById() {
        doThrow(RuntimeException.class).when(manufacturerRepository).deleteById(anyLong());
        boolean result = manufacturerService.deleteById(anyLong());
        assertFalse(result);
        assertThrows(Exception.class, () -> manufacturerRepository.deleteById(anyLong()));

        verify(manufacturerRepository,times(1)).deleteById(anyLong());
    }
    @Test
    @DisplayName("Borrar un fabricante de id nulo")
    void deleteNullTest(){
        boolean result = manufacturerService.deleteById(null);
        assertFalse(result);
    }

    @Test
    @DisplayName("Borrar un fabricante de id no incluido en la base de datos")
    void deleteNotContainsTest(){
        doThrow(RuntimeException.class).when(manufacturerRepository).deleteById(anyLong());
        boolean result = manufacturerService.deleteById(999L);
        assertFalse(result);
        assertThrows(Exception.class, () -> manufacturerRepository.deleteById(899L));
        verify(manufacturerRepository).deleteById(anyLong());
    }

    @Test
    void deleteByIdExceptionTest() {

        List<Manufacturer> manufacturers = new ArrayList<>();

        Manufacturer manufacturer1 = new Manufacturer("name1", "12345", 4, 2007);
        manufacturer1.setId(1L);
        manufacturers.add(manufacturer1);

         doThrow(RuntimeException.class).when(manufacturerRepository).deleteById(1L);

            boolean result = manufacturerService.deleteById(1L);
            assertThrows(Exception.class, () -> manufacturerRepository.deleteById(1L));


            verify(manufacturerRepository, times(1)).deleteById(1L);
            assertFalse(result);


    }

    @DisplayName("Comprobar que no borra con una Id nula")
    @Test
    void deleteByIdNullTest() {
        manufacturerService.deleteById(null);
        boolean result = manufacturerService.deleteById(null);
        assertFalse(result);
    }

    @Test
    void findManufacturerByCountryTest() {

        List<Manufacturer> manufacturers = manufacturerService.findManufacturerByCountry("España");
        assertNotNull(manufacturers);
        verify(manufacturerRepository).findManufacturerByDirectionCountry("España");

    }

    @Test
    void findManufacturerByCountryNullTest() {
        List<Manufacturer> manufacturers = manufacturerService.findManufacturerByCountry(null);
        assertTrue(manufacturers.isEmpty());
        //Comentada esta linea porque creo que no hace falta
        //verify(manufacturerRepository).findManufacturerByDirectionCountry(null);

    }

    @Test
    void deleteAllOK() {
        boolean result = manufacturerService.deleteAll();
        assertTrue(result);
        verify(manufacturerRepository).deleteAll();
    }

    @DisplayName("Comprobamos que arroja una excepcion al no escontrar manufacterers")
    @Test
    void deleteAllExceptionTest() {

        doThrow(RuntimeException.class).when(manufacturerRepository).deleteAll();

        boolean result = manufacturerService.deleteAll();
        assertThrows(Exception.class, () -> manufacturerRepository.deleteAll());
        verify(manufacturerRepository, times(2)).deleteAll();
        assertFalse(result);
    }



}


