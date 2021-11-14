package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Category;
import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ManufacturerRestControllerTest {

    private static final String MANUFACTURER_URL = "/api/manufacturers";
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


    @DisplayName("Comprobamos que recibimos todos los manufacturer")
    @Test
    void findAllTest() {
        createDemoManufacturer();
        createDemoManufacturer();

        ResponseEntity<Manufacturer[]> result = testRestTemplate.getForEntity(MANUFACTURER_URL,Manufacturer[].class);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(result.hasBody());
        assertNotNull(result.getBody());

        List<Manufacturer> manufacturers = List.of(result.getBody());
        assertNotNull(manufacturers);
        assertTrue(manufacturers.size() >=2);
    }
    @DisplayName("Comprobamos que buscamos uno por ID ")
    @Test
    void findOneOkTest() {
        Manufacturer manufacturer = createDemoManufacturer();
        ResponseEntity<Manufacturer> response = testRestTemplate.getForEntity(MANUFACTURER_URL+"/"+manufacturer.getId(), Manufacturer.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());

        Manufacturer result = response.getBody();

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getId(), manufacturer.getId());
    }

    @Test
    @DisplayName("Buscamos por un ID que no existe ")
    void findOneNotFound() {
        ResponseEntity<Manufacturer> response =
                testRestTemplate.getForEntity(MANUFACTURER_URL + "/666", Manufacturer.class);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertFalse(response.hasBody());
    }

    @DisplayName("Comprobamos que se crea correctamente un manufacturer")
    @Test
    void createOkTest() {
        String json = """
                {
                    "name": "Manufacturer creado desde Rest Test",
                    "cif": "123455678966",
                    "num employess": 8,
                    "year": 2009
                }
                """;
        ResponseEntity<Manufacturer> response = testRestTemplate.postForEntity(MANUFACTURER_URL, createHttpRequest(json), Manufacturer.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());

        Manufacturer manufacturer = response.getBody();

        assertNotNull(manufacturer);
        assertEquals("Manufacturer creado desde Rest Test", manufacturer.getName());


    }


    @Test
    void update() {
        Manufacturer manufacturer = createDemoManufacturer();
        String json = String.format("""
                {
                    "id": %d,
                    "name": "Manufacturer EDITADO",
                    "cif": "2344635325G",
                   
                }
                """, manufacturer.getId());
        System.out.println(json);
        ResponseEntity<Manufacturer> response =
                testRestTemplate.exchange(MANUFACTURER_URL, HttpMethod.PUT, createHttpRequest(json), Manufacturer.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.hasBody());
        assertNotNull(response.getBody());

        Manufacturer responseManufacturer = response.getBody();

        assertEquals(manufacturer.getId(), responseManufacturer.getId());
        assertEquals("Manufacturer EDITADO", responseManufacturer.getName());
        assertNotEquals(responseManufacturer.getName(), manufacturer.getName());
    }
    @Test
    void updateBadRequest() {
        String json = """
                {
                    "id": &d,
                    "name": "Manufacturer EDITADO",
                   "cif": "2343235325G",
                    "numEmployees": 6550,
                    "year": 1644
                }
                """;
        ResponseEntity<Manufacturer> response =
                testRestTemplate.exchange(MANUFACTURER_URL, HttpMethod.PUT, createHttpRequest(json), Manufacturer.class);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deleteAll() {
        createDemoManufacturer();
        createDemoManufacturer();

        ResponseEntity<Manufacturer[]> response = testRestTemplate.getForEntity(MANUFACTURER_URL, Manufacturer[].class);

        assertNotNull(response.getBody());

        List<Manufacturer> manufacturers = List.of(response.getBody());
        assertTrue(manufacturers.size() >= 2);
        testRestTemplate.delete(MANUFACTURER_URL);
        response = testRestTemplate.getForEntity(MANUFACTURER_URL, Manufacturer[].class);
        assertNotNull(response.getBody());
        manufacturers = List.of(response.getBody());
        assertEquals(0, manufacturers.size());
    }
        @Test
        @Disabled
        void deleteByIdSuccess() {
            Manufacturer manufacturer =createDemoManufacturer();
            String archive = MANUFACTURER_URL + "/" + manufacturer.getId();
            ResponseEntity<Manufacturer> response = testRestTemplate.getForEntity(archive, Manufacturer.class);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(manufacturer.getId(), response.getBody().getId());

            testRestTemplate.delete(archive);

            ResponseEntity<Manufacturer> response2 = testRestTemplate.getForEntity(archive, Manufacturer.class);

            assertEquals(404, response2.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
            assertFalse(response2.hasBody());

        }

    @DisplayName("comprobamos que no borra con Id null")
    @Test
    @Disabled
    void deleteByIdNullTest() {
        Manufacturer manufacturer = createDemoManufacturer();
        String archive = MANUFACTURER_URL + "/9999" + manufacturer.getId();
        ResponseEntity<Manufacturer> response = testRestTemplate.getForEntity(archive, Manufacturer.class);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ResponseEntity<Manufacturer> response2 = testRestTemplate.getForEntity(archive, Manufacturer.class);
        testRestTemplate.delete(archive);

        assertFalse(archive.isEmpty());


    }

    private Manufacturer createDemoManufacturer(){
        String json = """
                {
                    "name": "Manufacturer de prueba",
                    "cif": "1234556789",
                    "num employess": 5,
                    "year": 2012
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Manufacturer> response =
                testRestTemplate.postForEntity(MANUFACTURER_URL, request, Manufacturer.class);
        return response.getBody();
    }
    private HttpEntity<String> createHttpRequest(String json){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(json, headers);
    }
}