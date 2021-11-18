package com.example.proyectotesting.service;

import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    private ProductRepository productRepository;
    private ProductService productService;
    @BeforeEach
    protected void setUp() {
        productRepository = mock(ProductRepository.class);
        this.productService = new ProductServiceImpl(productRepository);
        System.out.println("Ejecutando el test con Mockito");
    }
    @AfterEach
    void tearDown() {
        System.out.println("Finalizando el test con mockito");
    }
    /**
     * comprobamos que cuenta los productos
     */
    @DisplayName("comprobamos que cuenta los productos")
    @Test
    void count() {
        when(productRepository.count()).thenReturn(0L);
        Long result = productService.count();
        assertNotNull(result);
        assertEquals(0, result);
        verify(productRepository).count();
    }
        /**
         * comprobamos que es capaz de encontrar todos los registros con un findAll
         */
        @Nested
     class findTest {
            @Test
            @DisplayName("comprobamos que es capaz de encontrar todos los registros")
            public void findAllTest() {
                Manufacturer made = new Manufacturer();
                List<Product> products = new ArrayList();
                products.add(new Product("Balón", "futbol", 2, 10.99, made));
                products.add(new Product("Balón2", "basket", 3, 16.99, made));
                when(productRepository.findAll()).thenReturn(products);
                List<Product> result = productService.findAll();
                assertNotNull(result);
                assertEquals(2, result.size());
                List<Product> check = new ArrayList<>();
                assertSame(check.getClass(), result.getClass());
                verify(productRepository).findAll();
            }

            /**
             * comprobamos que no encuentra con Id nula
             */
            @DisplayName("comprobamos que no  encuentra con Id nula")
            @Test
            void findOneNullIdTest() {
                Optional<Product> product = productService.findOne(null);
                assertEquals(Optional.empty(), product);
            }

            /**
             * comprobamos que no  encuentra con Id negativa
             */
            @DisplayName("comprobamos que no  encuentra con Id negativa")
            @Test
            void findNegativeIdTest() {
                Optional<Product> product = productService.findOne(-3L);
                assertEquals(Optional.empty(), product);
                assertFalse(product.isPresent());
                assertTrue(product.isEmpty());
            }

            /**
             * comprobamos que no encuentra con Id cero
             */
            @DisplayName("comprobamos que no  encuentra con Id cero")
            @Test
            void findZeroIdTest() {
                Optional<Product> product = productService.findOne(0L);
                assertEquals(Optional.empty(), product);
            }

            @Test
            void OptionalfindByIdTest() {
                List<Product> products = new ArrayList<>();
                Manufacturer made = new Manufacturer();

                Product product1 = new Product("Balón", "futbol", 2, 10.99, made);
                product1.setId(1L);
                products.add(product1);
                System.out.println(products + "\n");
                assertEquals(1, products.size());
                System.out.println(product1);
                Optional<Product> product = productService.findOne(1L);
                assertNotNull(product);
                assertTrue(product.isEmpty());
            }

            /**
             * comprobamos que encuentra una Id existente
             */
            @DisplayName("comprobamos que  encuentra una Id")
            @Test
            void existsByIdOkTest() {
                Manufacturer made = new Manufacturer();
                List<Product> products = Arrays.asList(
                        new Product("Balón", "futbol", 2, 10.99, made),
                        new Product("Balón2", "basket", 3, 16.99, made));
                when(productRepository.existsById(1L)).thenReturn(true);
                Boolean product = productService.existsById(1L);
                assertNotNull(product);
                assertTrue(product);
            }

            /**
             * comprobamos que no encuentra una Id inexistente
             */
            @DisplayName("comprobamos que no encuentra una Id que no existe")
            @Test
            void NotexistsByIdTest() {
                Manufacturer made = new Manufacturer();
                List<Product> products = Arrays.asList(
                        new Product("Balón", "futbol", 2, 10.99, made),
                        new Product("Balón2", "basket", 3, 16.99, made));
                when(productRepository.existsById(99L)).thenReturn(false);
                Boolean product = productService.existsById(99L);
                assertFalse(product);
            }

            /**
             * comprobamos que si encuentra el precio menor nulo devuelve empty
             */
            @DisplayName("comprobamos que si encuentra el precio menor nulo devuelve empty")
            @Test
            void findByPriceBetweenNullTest() {
                List<Product> products = productService.findByPriceBetween(null, 25D);
                assertTrue(products.isEmpty());
            }

            /**
             * comprobamos que si encuentra el precio mayor nulo devuelve empty
             */
            @DisplayName("comprobamos que si encuentra el precio mayor nulo devuelve empty")
            @Test
            void findByPriceMaxNullTest() {
                List<Product> products = productService.findByPriceBetween(2D, null);
                assertTrue(products.isEmpty());
            }

            /**
             * comprobamos que no encuentra con  precio menor negativo
             */
            @DisplayName("comprobamos que devuelve empty si el precio menor es negativo")
            @Test
            void findByPricMinNegTest() {
                List<Product> products = productService.findByPriceBetween(-2D, 35D);
                assertTrue(products.isEmpty());
            }

            /**
             * comprobamos que no encuentra con  precio mayor negativo
             */
            @DisplayName("comprobamos que devuelve empty si el precio mayor es negativo")
            @Test
            void findByPricMaxNegTest() {
                List<Product> products = productService.findByPriceBetween(2D, -35D);
                assertTrue(products.isEmpty());
            }

            /**
             * comprobamos que no encuentra precios con precio menor por encima del mayor
             */
            @DisplayName("comprobamos que no encuentra precios con precio menor por encima del mayor y devuelve empty")
            @Test
            void findByPriceMinUpperMaxTest() {
                List<Product> products = productService.findByPriceBetween(82D, 15D);
                assertTrue(products.isEmpty());
            }

            /**
             * comprobamos que no encuentra con precio menor mayor que el superior
             */
            @DisplayName("comprobamos que no encuentra con precio menor mayor que el superior")
            @Test
            void findByPricMinMaxTest() {
                List<Product> products = new ArrayList<>();
                products.add(new Product("Balón", "futbol", 2, 10.99, new Manufacturer()));
                when(productService.findByPriceBetween(5D, 25D)).thenReturn(products);
                List<Product> result = productService.findByPriceBetween(5D, 25D);
                assertNotNull(result.get(0).getQuantity());
                assertFalse(result.isEmpty());
            }

            /**
             * comprobamos que no encuentra productos con manufacturer nulo
             */
            @DisplayName("comprobamos que no encuentra productos con manufacturer nulo")
            @Test
            void findByManufacturerNullTest() {
                List<Product> manufacturer = productService.findByManufacturer(null);
                assertTrue(manufacturer.isEmpty());
            }

            /**
             * comprobamos que no encuentra productos con manufacturer vacio
             */
            @DisplayName("comprobamos que no encuentra productos con manufacturer nulo")
            @Test
            void findByManufacturerEmptyTest() {
                List<Product> manufacturer = productService.findByManufacturer("");
                assertTrue(manufacturer.isEmpty());
            }

            /**
             * comprobamos que no encuentra productos con manufacturer correcto
             */
            @DisplayName("comprobamos que no encuentra productos con manufacturer nulo")
            @Test
            void findByManufacturerOkTest() {
                when(productRepository.findByManufacturerName("Artengo")).thenReturn(new ArrayList<Product>());
                List<Product> manufacturer = productService.findByManufacturer("Artengo");
                assertNotNull(manufacturer);
                verify(productRepository).findByManufacturerName("Artengo");
            }

            /**
             * comprobamos que no calcula los portes con producto nulo
             */
            @DisplayName("comprobamos que no calcula los portes con producto nulo")
            @Test
            void calculateShippingCostNullTest() {
                Manufacturer made = new Manufacturer();
                Product product1 = new Product("Balón", "futbol", 2, 10.99, made);
                Direction direction1 = new Direction(null, "33010", "León", "Spain");
                Double shipping = productService.calculateShippingCost(null, direction1);
                assertEquals(0d, shipping);
            }

            /**
             * comprobamos que devuelve 0 si todos los parámetros son nulos
             */
            @Test
            @DisplayName("comprobamos que devuelve 0 si todos los parámetros son nulos")
            void nullAllTest() {
                Double result = productService.calculateShippingCost(null, null);
                assertEquals(0, result);
            }

            /**
             * comprobamos que devuelve 0 si la dirección es nula
             */
            @Test
            @DisplayName("comprobamos que devuelve 0 si la dirección es nula")
            void nullDirectionTest() {
                Manufacturer made = new Manufacturer();
                Product product1 = new Product("Balón", "futbol", 2, 10.99, made);
                Double result = productService.calculateShippingCost(product1, null);
                assertEquals(0, result);
            }

            /**
             * comprobamos que devuelve 0 si el pains es nulo
             */
            @Test
            @DisplayName("comprobamos que devuelve 0 si el pains es nulo")
            void nullCountryTest() {
                Manufacturer made = new Manufacturer();
                Product product1 = new Product("Balón", "futbol", 2, 10.99, made);
                Direction direction1 = new Direction(null, "33010", "León", null);
                Double result = productService.calculateShippingCost(product1, direction1);
                assertEquals(0, result);
            }

            /**
             * comprobamos que calcula los portes con producto en Spain
             */
            @DisplayName("comprobamos que calcula los portes con producto en Spain")
            @Test
            void calculateShippingCostSpainTest() {
                Manufacturer made = new Manufacturer();
                Product product1 = new Product("Balón", "futbol", 2, 10.99, made);
                Direction direction1 = new Direction("Calle melancolia", "33010", "León", "Spain");
                Double shipping = productService.calculateShippingCost(product1, direction1);
                assertEquals(2.99d, shipping);
            }

            /**
             * comprobamos que calcula los portes con producto fuera de Spain
             */
            @DisplayName("comprobamos que calcula los portes con producto fuera de Spain")
            @Test
            void calculateShippingCostPOutSpainTest() {
                Double cost1 = 2.99;
                Double calculo = (cost1 += 20);
                Manufacturer made = new Manufacturer();
                Product product1 = new Product("Balón", "futbol", 2, 10.99, made);
                Direction direction1 = new Direction("liberty strees", "33010", "utah", "USA");
                Double shipping = productService.calculateShippingCost(product1, direction1);
                assertEquals(calculo, shipping);
            }
        }
        /**
         * comprobamos que no guarda con un producto nulo
         */
        @Nested
        class saveTest {
            @DisplayName("comprobamos que no guarda con un producto nulo")
            @Test
            void saveNullTest() {
                assertNull(productService.save(null));}
            /**
             * comprobamos que guarda bien un producto
             */
            @DisplayName("comprobamos que guarda bien un producto")
            @Test
            void saveOkTest() {
                Manufacturer made = new Manufacturer();
                Product product1 = new Product("Balón Test", "futbol 7", 5, 18.99, made);
                when(productRepository.save(any())).thenReturn(product1);
                Product result = productService.save(product1);
                assertNotNull(result);
                assertEquals("Balón Test", result.getName());
                verify(productRepository).save(product1);
            }
        }
        @Nested
        class deleteTests {
            /**
             * comprobamos que no borra con una Id nula
             */
            @DisplayName("comprobamos que no borra con una Id nula")
            @Test
            void deleteByIdNullTest() {
                assertFalse(productService.deleteById(null));
            }
            /**
             * comprobamos que borra con una Id correcta"
             */
            @DisplayName("comprobamos que borra con una Id")
            @Test
            void deleteByIdOkTest() {
                when(productRepository.existsById(1L)).thenReturn(true);
                doNothing().when(productRepository).deleteById(1L);
                assertTrue(productService.deleteById(1L));
                verify(productRepository).existsById(1L);
                verify(productRepository).deleteById(1L);
            }
            /**
             * comprobamos que no borra con una Id que no debe existir
             */
            @DisplayName("comprobamos que no borra con una Id que no debe existir")
            @Test
            void deleteByIdOutOfBoundsTest() {
                when(productRepository.existsById(99L)).thenReturn(false);
                assertFalse(productService.deleteById(99L));
                verify(productRepository).existsById(99L);
            }
            /**
             * comprobamos que devuelve una excepción al intentar  borrar todos los productos
             */

            @DisplayName("comprobamos que devuelve una excepción al intentar  borrar todos los productos")
            @Test
            void deleteIdExceptTest() {
                doThrow(new RuntimeException()).when(productRepository).deleteById(1L);
                when(productRepository.existsById(1L)).thenReturn(true);
                assertFalse(productService.deleteById(1L));
                verify(productRepository).existsById(1L);
            }
            /**
             * comprobamos que  borra todos los productos
             */
            @DisplayName("comprobamos que  borra todos los productos")
            @Test
            void deleteAllOk() {
                productService.deleteAll();
                List<Product> borrado = productService.findAll();
                boolean result = productService.deleteAll();
                assertTrue(result);
                assertEquals(0, borrado.size());
                verify(productRepository, times(2)).deleteAll();
            }
            /**
             * comprobamos que devuelve una excepción al intentar  borrar todos los productos
             */
            @DisplayName("comprobamos que devuelve una excepción al intentar  borrar todos los productos")
            @Test
            void deleteAllExceptTest() {
                doThrow(RuntimeException.class).when(productRepository).deleteAll();
                boolean result = productService.deleteAll();
                assertThrows(Exception.class, () -> productRepository.deleteAll());
                verify(productRepository, times(2)).deleteAll();
                assertFalse(result);
            }
        }
    }