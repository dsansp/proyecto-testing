package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Category;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled
class DirectionRestControllerTest {
    private static final String Direction_URL = "/api/direccion";
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

            //assertEquals(200, respuesta.getStatusCodeValue());
            //assertEquals(HttpStatus.OK, respuesta.getStatusCode());
            //assertTrue(respuesta.hasBody());
            //assertNotNull(respuesta.getBody());

            //List<Direction> directions = List.of(respuesta.getBody());

            //assertNotNull(directions);
            //assertTrue(directions.size() >= 2);
        }
        @DisplayName("Comprobación buscar una dirección por ID")
        @Test
        void findOne() {
        }
    }

    @DisplayName("Comprobación crear una dirección")
    @Test
    void create() {
    }

    @DisplayName("Comprobación actualizar una dirección")
    @Test
    void update() {
    }

    private Direction createDataDirections() {
        String json = """
                {
                    "street": "Calle generada por el REST",
                    "postalCode": "28080",
                    "city": "Santa Cruz de Tenerife,
                    "country": "España"
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Direction> response =
                testRestTemplate.postForEntity(Direction_URL, request, Direction.class);
        return response.getBody();
    }

    private HttpEntity<String> crearHttpRequest(String json) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        return request;
    }
}