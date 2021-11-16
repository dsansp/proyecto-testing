package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Category;
import com.example.proyectotesting.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@DisplayName("Comprobando test con las  operaciones CRUD sobre la entidad de Category")
class CategoryServiceImplTest {


         @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            this.service = new CategoryServiceImpl(categoryRepository);
            System.out.println("Ejecutando test con Mockito");
        }

        @Mock //dependencia
        CategoryRepository categoryRepository;

        @InjectMocks//el que depende de la dependencia
        CategoryServiceImpl service;


        @AfterEach
        void tearDown() {
            System.out.println("Finalizando test con Mockito");
        }


        @DisplayName("findAll()->Comprobamos que se encuentran todas las categorias")
        @Test
        void findAllTest() {
            List<Category> categories = Arrays.asList(
                    new Category("Category1", "Blue"),
                    new Category("Category2", "Green"),
                    new Category("Category3", "Red")
            );

            when(categoryRepository.findAll()).thenReturn(categories);

            List<Category> result = service.findAll();

            assertEquals(3, result.size());
            verify(categoryRepository).findAll();
        }


        @DisplayName("findOneOptional()->Comprobando que no hay una categoria null")
        @Test
        void findOneNullTest() {
            Optional<Category> categoryOpt = service.findOne((Long) null);
            assertTrue(categoryOpt.isEmpty());
        }

        @DisplayName("Comprobando que no hay un id con numero 0 en categorias")
        @Test
        void findOneIdCeroTest() {
            Optional<Category> categoryOpt = service.findOne(0L);
            assertEquals(Optional.empty(), categoryOpt);
        }

        @DisplayName("Comprobando que no hay un id 5 que sea negativo en categorias")
        @Test
        void findOneNegativeIdTest() {
            Optional<Category> categoryOpt = service.findOne(-5L);
            assertEquals(Optional.empty(), categoryOpt);
        }

        @DisplayName("Comprobamos que en la DB de categorias estan los id que pedimos ")
        @Test
        void findByIdTest() {
            List<Category> categories = new ArrayList<>();
            Category c1 = new Category("Category Moderna", "Color Vintage");
            Category c2 = new Category("Category Antigua", "Color White and Black");

            System.out.println(categories + "\n");

            c1.setId(1L);
            c2.setId(2L);

            categories.add(c1);
            categories.add(c2);

            assertEquals(2, categories.size());

            System.out.println("Category 1 :" + c1 + "\n" +
                    "Category 2: " + c2);
            Optional<Category> categoryOpt1 = service.findOne(1L);
            Optional<Category> categoryOpt2 = service.findOne(2L);

            assertNotNull(categoryOpt1);
            assertNotNull(categoryOpt2);
            assertTrue(categoryOpt1.isEmpty());
            assertTrue(categoryOpt2.isEmpty());

        }


        @DisplayName("Comprobamos que no exite ninguna id negativa")
        @Test
        void existsByIdNegativeTest() {

            when(categoryRepository.existsById(-1L)).thenReturn(false);
            boolean category = service.existsById(-1L);
            assertFalse(category);

        }

        @DisplayName("Comprobamos que no existe ninguna id que enmpiece por 0")
        @Test
        void existByIdZeroTest() {
            when(categoryRepository.existsById(0L)).thenReturn(false);
            boolean category = service.existsById(0L);
            assertFalse(category);

        }

        @DisplayName("Comprobamos que existe el id 1")
        @Test
        void existsByIdSuccessTest() {
            when(categoryRepository.existsById(1L)).thenReturn(true);
            boolean category = service.existsById(1L);
            assertTrue(category);
        }

        @DisplayName("Comprobamos que no existe una categoria con id 50")
        @Test
        void existById50Test() {
            when(categoryRepository.existsById(50L)).thenReturn(false);
            boolean category = service.existsById(50L);
            assertFalse(category);
        }

        @DisplayName("Comprobando que no existe ningun color null")
        @Test
        void findByColorNullTest() {
            Optional<Category> categoryOpt = service.findOne((String) null);
            assertEquals(Optional.empty(), categoryOpt);

        }

        @DisplayName("Comprobamos que se encuentra el color que queremos")
        @Test
        void findByColorSuccessTest() {
            Optional<Category> categoryOpt = service.findOne("Blue");
            when(categoryRepository.findByColor("Blue")).thenReturn(categoryOpt);
            assertNotNull(categoryOpt);
            verify(categoryRepository).findByColor("Blue");
        }


        @DisplayName("Comprobando que se guardan correctamente las categorias")
        @Test
        void saveSuccessTest() {
            Category c1 = new Category("Category 1", "Color White");
            when(categoryRepository.save(any())).thenReturn(c1);
            Category result = service.save(c1);
            assertNotNull(result);
            assertEquals("Category 1", result.getName());
            verify(categoryRepository).save(c1);
        }

        @DisplayName("Comprobamos que no se puede guardar una categoria null")
        @Test
        void saveNullTest() {

            when(categoryRepository.save(any())).thenReturn(null);
            Category result = service.save(null);
            assertNull(result);

        }

        @DisplayName("count()->Contando el numero de categorias")
        @Test
        void count() {
            when(categoryRepository.count()).thenReturn(2L);
            long result = service.count();
            assertEquals(2L, result);
            verify(categoryRepository).count();
        }

        @DisplayName("Comprobamos que no se borra un id null")
        @Test
        void deleteByIdNullTest() {
            service.deleteById(null);
            boolean result = service.deleteById(null);
            assertFalse(result);

        }

        @DisplayName("Comprobamos que no se borra con el id de 40 ya que no deberia existir")
        @Test
        void notDeleteById40Test() {
            when(categoryRepository.existsById(40L)).thenReturn(false);
            boolean category = service.existsById(40L);
            assertFalse(category);
        }

        @DisplayName("Comprobamos que se borra la categoria con id 1")
        @Test
        void deleteByIdSuccessTest() {
            service.deleteById(1L);
            boolean result = service.deleteById(1L);
            List<Category> categoryDelete = service.findAll();
            assertEquals(0, categoryDelete.size());

        }

        @DisplayName("Comprobamos que se borran todas las categorias")
        @Test
        void deleteAll() {
            List<Category> categories = service.findAll();
            boolean result = service.deleteAll();
            assertTrue(result);
            assertEquals(0, categories.size());
            verify(categoryRepository).deleteAll();

        }

        @DisplayName("Comprobamos que arroja una excepcion al no escontrar categorias")
        @Test
        void deleteAllExceptionTest() {

            doThrow(RuntimeException.class).when(categoryRepository).deleteAll();

            boolean result = service.deleteAll();
            assertThrows(Exception.class, () -> categoryRepository.deleteAll());
            verify(categoryRepository, times(2)).deleteAll();
            assertFalse(result);


        }


    }