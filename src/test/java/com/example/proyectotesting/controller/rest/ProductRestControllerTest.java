package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.CategoryRepository;
import com.example.proyectotesting.repository.ManufacturerRepository;
import com.example.proyectotesting.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductRestControllerTest {
    private static final String PRODUCTSURL = "/api/products";
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);


    }

    /**
     * Comprobamos que creando una base de datos demo, findAll es capaz de encontrar todos los
     * test. Esperamos recibir un código 200 como respuesta (Status Ok)
     */

    @Nested
    class findTests {
        @DisplayName("Comprobación buscar todos los productos")
        @Test
        void findAllTest() {
            createDemoProduct();
            createDemoProduct();
            createDemoProduct();

            ResponseEntity<Product[]> result = testRestTemplate.getForEntity(PRODUCTSURL, Product[].class);
            assertEquals(200, result.getStatusCodeValue());
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertTrue(result.hasBody());
            assertNotNull(result.getBody());

            List<Product> products = List.of(result.getBody());
            assertNotNull(products);
            assertTrue(products.size() >= 3);

        }

        /**
         * Comprobamos que creando una base de datos demo, findById es capaz de encontrar un
         * test en concreto. Esperamos recibir un código 200 como respuesta (Status Ok)
         */
        @DisplayName("comprobamos buscar uno por ID de forma correcta")
        @Test
        void findOneOkTest() {
            Product product = createDemoProduct();
            ResponseEntity<Product> response = testRestTemplate.getForEntity(PRODUCTSURL + "/" + product.getId(), Product.class);
            assertEquals(200, response.getStatusCodeValue());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.hasBody());
            Product replyBody = response.getBody();
            assertNotNull(replyBody);
            assertNotNull(replyBody.getId());
            assertEquals(replyBody.getId(), product.getId());

        }

        /**
         * Comprobamos que findById no es capaz de encontrar un
         * test en concreto. Esperamos recibir un código 404 como respuesta (Status Not Found)
         */
        @DisplayName("comprobamos buscar uno Not Found")
        @Test
        void findOneNotFoundTest() {
            ResponseEntity<Product> badDemo = testRestTemplate.getForEntity(PRODUCTSURL + "/88888", Product.class);
            assertEquals(404, badDemo.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, badDemo.getStatusCode());
            assertFalse(badDemo.hasBody());


        }

        /**
         * Comprobamos que findById no es capaz de encontrar un
         * test en concreto con id cero. Esperamos recibir un código 404 como respuesta (Status Not Found)
         */
        @DisplayName("comprobamos buscar uno con Id cero y no lo encuentra")
        @Test
        void findOneZeroTest() {
            ResponseEntity<Product> badDemo = testRestTemplate.getForEntity(PRODUCTSURL + "/0", Product.class);
            assertEquals(404, badDemo.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, badDemo.getStatusCode());
            assertFalse(badDemo.hasBody());


        }
    }

    /**
     * Comprobamos que create  es capaz de crear un objeto.
     * Esperamos recibir un código 201 como respuesta (Status creado)
     */
    @Nested
    class saveTest {
        @DisplayName("comprobamos que crea correctamente")
        @Test
        void createOkTest() {
            String json = """
                    {
                        "name": "Product Test",
                        "description": "description example",
                        "quantity": 9,
                        "price": 9.99
                    }
                    """;
            ResponseEntity<Product> response = testRestTemplate.postForEntity(PRODUCTSURL, createHttpRequest(json), Product.class);
            assertEquals(201, response.getStatusCodeValue());
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertTrue(response.hasBody());
            Product product = response.getBody();
            assertNotNull(product);
            assertEquals("Product Test", product.getName());

        }

        /**
         * Comprobamos que Created no crea un nuevo producto cuando encuentra una ID.
         * Esperamos recibir un código 400 como respuesta (BadRequest)
         */
        @DisplayName("comprobamos que no crea con una badrequest")
        @Test
        void createBadRequestTest() {
            String json = """
                    {
                        "id": 5,
                        "name": "Product BadRequest",
                        "description": "description example",
                        "quantity": 9,
                        "price": 9.99
                    }
                    """;
            ResponseEntity<Product> response = testRestTemplate.postForEntity(PRODUCTSURL, createHttpRequest(json), Product.class);
            assertEquals(400, response.getStatusCodeValue());
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertFalse(response.hasBody());
        }

        /**
         * Comprobamos que Update modifica  un producto cuando encuentra una ID.
         * Esperamos recibir un código 200 como respuesta (Ok)
         */
        @DisplayName("comprobamos que se modifica")
        @Test
        void updateOkTest() {

            Product product = createDemoProduct();
            String json = String.format("""
                    {
                        "id": %d,
                        "name": "Product modified",
                        "description": "description example",
                        "quantity": 8,
                        "price": 8.99
                    }
                    """, product.getId());
            System.out.println(json);
            ResponseEntity<Product> response =
                    testRestTemplate.exchange(PRODUCTSURL, HttpMethod.PUT, createHttpRequest(json), Product.class);
            assertEquals(200, response.getStatusCodeValue());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.hasBody());
            assertNotNull(response.getBody());
            Product responseProduct = response.getBody();
            assertEquals(product.getId(), responseProduct.getId());
            assertEquals("Product modified", responseProduct.getName());
            assertNotEquals(responseProduct.getName(), product.getName());
        }

        /**
         * Comprobamos que Update no modifica un producto cuando no encuentra una ID.
         * Esperamos recibir un código 400 como respuesta (BadRequest)
         */
        @DisplayName("comprobamos update con Id null")
        @Test
        void updateNullTest() {
            Product product = createDemoProduct();
            String json = String.format("""
                    {
                        "id": null,
                        "name": "Product modified",
                        "description": "description example",
                        "quantity": 8,
                        "price": 8.99
                    }
                    """, product.getId());
            System.out.println(json);
            ResponseEntity<Product> response =
                    testRestTemplate.exchange(PRODUCTSURL, HttpMethod.PUT, createHttpRequest(json), Product.class);
            assertEquals(400, response.getStatusCodeValue());
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        /**
         * Comprobamos que Update no modifica un producto cuando no encuentra una ID fuera de rango.
         * Esperamos recibir un código 400 como respuesta (BadRequest)
         */
        @DisplayName("comprobamos update con Id no encontrada")
        @Test
        void updateIdNotFoundTest() {
            Product product = createDemoProduct();
            String json = String.format("""
                    {
                        "id": 999999,
                        "name": "Product modified",
                        "description": "description example",
                        "quantity": 8,
                        "price": 8.99
                    }
                    """, product.getId());
            System.out.println(json);
            ResponseEntity<Product> response =
                    testRestTemplate.exchange(PRODUCTSURL, HttpMethod.PUT, createHttpRequest(json), Product.class);
            assertEquals(404, response.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }

    }

    /**
     * Comprobamos que DeleteId borra un producto cuando encuentra una ID.
     * Esperamos recibir un código 200 como respuesta (Ok) y
     * luego verificamos que se borro.
     */
    @Nested
    class deteleTests {
        @DisplayName("comprobamos que borra correctamente con una Id")
        @Test
        void deleteByIdTestOk() {
            Product product = createDemoProduct();
            String archive = PRODUCTSURL + "/" + product.getId();
            ResponseEntity<Product> response = testRestTemplate.getForEntity(archive, Product.class);
            assertEquals(200, response.getStatusCodeValue());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(product.getId(), response.getBody().getId());
            testRestTemplate.delete(archive);
            ResponseEntity<Product> response2 = testRestTemplate.getForEntity(archive, Product.class);
            assertEquals(404, response2.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
            assertFalse(response2.hasBody());

        }

        /**
         * Comprobamos que DeleteId no borra un producto cuando encuentra una ID nula.
         * Esperamos recibir un código 404 como respuesta (Not Found)
         */
        @DisplayName("comprobamos que no borra con Id null")
        @Test
        void deleteByIdNullTest() {
            Product product = createDemoProduct();
            String archive = PRODUCTSURL + "/9999" + product.getId();
            ResponseEntity<Product> response = testRestTemplate.getForEntity(archive, Product.class);
            assertEquals(404, response.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            ResponseEntity<Product> response2 = testRestTemplate.getForEntity(archive, Product.class);
            testRestTemplate.delete(archive);

            assertFalse(archive.isEmpty());


        }

        /**
         * Comprobamos que DeleteAll borra todos los productos
         * y luego verificamos que se borros.
         */
        @DisplayName("comprobamos que borramos todos")
        @Test
        void deleteAll() {
            createDemoProduct();
            createDemoProduct();
            createDemoProduct();
            ResponseEntity<Product[]> response = testRestTemplate.getForEntity(PRODUCTSURL, Product[].class);
            assertNotNull(response.getBody());
            List<Product> products = List.of(response.getBody());
            assertTrue(products.size() >= 3);
            testRestTemplate.delete(PRODUCTSURL);
            response = testRestTemplate.getForEntity(PRODUCTSURL, Product[].class);
            assertNotNull(response.getBody());
            products = List.of(response.getBody());
            assertEquals(0, products.size());
        }


    }

    private Product createDemoProduct() {
        String json = """
                {
                    "name": "Product de prueba",
                    "description": "description check",
                    "quantity": 5,
                    "price": 9.99
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Product> response =
                testRestTemplate.postForEntity(PRODUCTSURL, request, Product.class);
        return response.getBody();
    }

    private HttpEntity<String> createHttpRequest(String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        return request;
    }
}
