package com.example.proyectotesting.controller.rest;


import com.example.proyectotesting.entities.Direction;
import com.example.proyectotesting.service.DirectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class DirectionRestControllerTest {
    private static final String Direction_URL = "/api/directions";
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

    @Nested
    public class findTests {
        @DisplayName("Comprobación buscar todas las direcciones")
        @Test
        void findAllTest() {
            createDataDirections();
            createDataDirections();

            ResponseEntity<Direction[]> respuesta = testRestTemplate.getForEntity(Direction_URL, Direction[].class);

            assertEquals(200, respuesta.getStatusCodeValue());
            assertEquals(HttpStatus.OK, respuesta.getStatusCode());
            assertTrue(respuesta.hasBody());
            assertNotNull(respuesta.getBody());

            List<Direction> directions = List.of(respuesta.getBody());

            assertNotNull(directions);
            assertTrue(directions.size() >= 2);
        }
        @DisplayName("Comprobación buscar una dirección por ID")
        @Test
        void findOneOKTest() {
            Direction direction = createDataDirections();
            //No se está guardando en el objeto direction y siempre es null
            ResponseEntity<Direction> respuesta = testRestTemplate.getForEntity(Direction_URL+"/"+ direction.getId(), Direction.class);

            System.out.println(direction.getStreet());

            assertEquals(200, respuesta.getStatusCodeValue());
            assertEquals(HttpStatus.OK, respuesta.getStatusCode());
            assertTrue(respuesta.hasBody());

            Direction replyBody= respuesta.getBody();

            assertNotNull(replyBody);
            assertNotNull(replyBody.getId());
            assertEquals(replyBody.getId(), direction.getId());
        }
        @DisplayName("Comprobación buscar sin ID Not Found")
        @Test
        void findOneNotFoundTest(){
            ResponseEntity<Direction> badDemo= testRestTemplate.getForEntity(Direction_URL +"/88888", Direction.class);
            assertEquals(404, badDemo.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, badDemo.getStatusCode());
            assertFalse(badDemo.hasBody());
        }
        @DisplayName("Comprobar buscar uno con Id cero Not Found")
        @Test
        void findOneZeroTest(){
            ResponseEntity<Direction> badDemo= testRestTemplate.getForEntity(Direction_URL +"/0", Direction.class);
            assertEquals(404, badDemo.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, badDemo.getStatusCode());
            assertFalse(badDemo.hasBody());
        }
    }

    @Nested
    class saveTest {
        @DisplayName("Comprobación crear una dirección")
        @Test
        void createOkTest() {
            String json = """
                {
                    "street": "Calle creada OK",
                    "postalCode": "35011",
                    "city": "Las Palmas de Gran Canaria",
                    "country": "España"
                }
                """;
            //Ejecutar petición HTTP
            ResponseEntity<Direction> respuesta =  testRestTemplate.postForEntity(Direction_URL, createHttpRequest(json), Direction.class);
            //Procesar respuestas y aserciones
            assertEquals(200, respuesta.getStatusCodeValue());
            assertEquals(HttpStatus.OK, respuesta.getStatusCode());
            assertTrue(respuesta.hasBody());
            Direction direction = respuesta.getBody();
            assertNotNull(direction);
            assertEquals("Calle creada OK", direction.getStreet());
        }
        @DisplayName("comprobamos que no crea con una badrequest")
        @Test
        void createBadRequestTest() {
            String json = """
                {
                    "id": 5,
                    "street": "Calle creada OK",
                    "postalCode": "35011",
                    "city": "Las Palmas de Gran Canaria",
                    "country": "España"
                }
                """;
            ResponseEntity<Direction> respuesta=  testRestTemplate.postForEntity(Direction_URL, createHttpRequest(json), Direction.class);
            assertEquals(400, respuesta.getStatusCodeValue());
            assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());
            assertFalse(respuesta.hasBody());
        }
    }
    @Nested
    class updateTest {
        @DisplayName("Comprobación actualizar una dirección")
        @Test
        void updateOKTest() {
            Direction direction = createDataDirections();
            String json = String.format("""
                {
                    "id": %d,
                    "street": "Calle actualizada con éxito",
                    "postalCode": "350112",
                    "city": "Las Palmas de Gran Canaria",
                    "country": "España"
                }
                """, direction.getId());

            //System.out.println(direction.getId());

            ResponseEntity<Direction> respuesta = testRestTemplate.exchange(Direction_URL, HttpMethod.PUT, createHttpRequest(json), Direction.class);

            assertEquals(200, respuesta.getStatusCodeValue());
            assertEquals(HttpStatus.OK, respuesta.getStatusCode());
            assertTrue(respuesta.hasBody());
            assertNotNull(respuesta.getBody());
            Direction responseDirection = respuesta.getBody();
            assertEquals (direction.getId(),responseDirection.getId() );
            assertEquals("Calle actualizada con éxito", responseDirection.getStreet());
            assertNotEquals(responseDirection.getStreet(), direction.getStreet());
        }

        @DisplayName("Comprobar actualización con Id null")
        @Test
        void updateNullTest() {
            Direction direction = createDataDirections();
            String json = """
                {
                    "id": null
                    "street": "Calle actualizada con éxito",
                    "postalCode": "350112",
                    "city": "Las Palmas de Gran Canaria",
                    "country": "España"
                }
                """;
            //Ejecutar petición HTTP
            ResponseEntity<Direction> respuesta = testRestTemplate.exchange(Direction_URL, HttpMethod.PUT, createHttpRequest(json), Direction.class);
            assertEquals(400, respuesta.getStatusCodeValue());
            assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());
        }

        @DisplayName("Comprobar que no actualiza con una Id no encontrada")
        @Test
        void updateIdNotFoundTest() {
            Direction direction = createDataDirections();
            String json = String.format("""
                {
                    "id": 999999,
                    "street": "Calle actualizada con éxito",
                    "postalCode": "350112",
                    "city": "Las Palmas de Gran Canaria",
                    "country": "España"
                }
                """, direction.getId());
            //System.out.println(json);
            ResponseEntity<Direction> respuesta = testRestTemplate.exchange(Direction_URL, HttpMethod.PUT, createHttpRequest(json), Direction.class);
            assertEquals(404, respuesta.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
        }
    }

    private Direction createDataDirections() {
        String json = """
                {
                    "street": "Calle generada por el REST",
                    "postalCode": "28080",
                    "city": "Santa Cruz de Tenerife",
                    "country": "España"
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Direction> response = testRestTemplate.postForEntity(Direction_URL, request, Direction.class);
        return response.getBody();
    }

    private HttpEntity<String> createHttpRequest(String json) {
        //Cabeceras para indicar que se envía un json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        //Crear petición HTTP
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        return request;
    }
}