package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Category;
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
class CategoryRestControllerTest {

    private static final String Category_URL = "/api/category";
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    @Test
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }


    @Nested
    public class findTests {
        @DisplayName("Comprobando si al buscar la categoria se encuentran todos")
        @Test
        void findAllTest() {
            //Data para Categories
            createDataCategories();
            createDataCategories();


            ResponseEntity<Category[]> respuesta = testRestTemplate.getForEntity(Category_URL, Category[].class);

            assertEquals(200, respuesta.getStatusCodeValue());
            assertEquals(HttpStatus.OK, respuesta.getStatusCode());
            assertTrue(respuesta.hasBody());
            assertNotNull(respuesta.getBody());

            List<Category> categories = List.of(respuesta.getBody());

            assertNotNull(categories);
            assertTrue(categories.size() >= 2);


        }

        @DisplayName("Comprobamos si encuentra una categoria")
        @Test
        void findOneCategoryTest() {
            Category category = createDataCategories();

            ResponseEntity<Category> respuesta =
                    testRestTemplate.getForEntity(Category_URL + "/" + category.getId(), Category.class);

            assertEquals(200, respuesta.getStatusCodeValue());
            assertEquals(HttpStatus.OK, respuesta.getStatusCode());
            assertTrue(respuesta.hasBody());

            Category responseCategory = respuesta.getBody();

            assertNotNull(responseCategory);
            assertNotNull(responseCategory.getId());
            assertEquals(category.getId(), responseCategory.getId());

        }

        @DisplayName("Comprobamos que no encuentra el id que le pusimos")
        @Test
        void findOneDontFound() {
            ResponseEntity<Category> responseNotFound =
                    testRestTemplate.getForEntity(Category_URL + "/777", Category.class);

            assertEquals(404, responseNotFound.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, responseNotFound.getStatusCode());
            assertFalse(responseNotFound.hasBody());
        }


    }





        @DisplayName("Comprobamos que se crea correctamente la categoria")
        @Test
        void createSuccesTest() {
            String json = """
                    {
                        "name": "Categoria creada desde Rest Test",
                        "color": "Color maravilloso"
                    }
                    """;
            ResponseEntity<Category> respuesta = testRestTemplate.postForEntity(Category_URL, crearHttpRequest(json), Category.class);

            assertEquals(200, respuesta.getStatusCodeValue());
            assertEquals(HttpStatus.OK, respuesta.getStatusCode());
            assertTrue(respuesta.hasBody());

            Category category = respuesta.getBody();

            assertNotNull(category);
            assertEquals("Categoria creada desde Rest Test", category.getName());


        }

        //Este test no haria falta ya que hace cobertura

//        @Disabled
//        @DisplayName("comprobamos que no crea una categoria porque hay un badrequest")
//        @Test
//        void createNullTest() {
//            String json = """
//                    {   "id": null,
//                        "name": "Categoria BadRequest",
//                        "color": "Color maravilloso"
//                    }
//                    """;
//            ResponseEntity<Category> respuesta = testRestTemplate.postForEntity(Category_URL, crearHttpRequest(json), Category.class);
//
//            assertEquals(409, respuesta.getStatusCodeValue());
//            assertEquals(HttpStatus.CONFLICT, respuesta.getStatusCode());
//            assertFalse(respuesta.hasBody());
//        }

        @Test
        void createResponse400Test(){

            String json = """
                {
                    "id": 5,
                    "name": "Category creado desde JUnit",
                    "color": "color example"
                }
                """;
// Id no debia ser nula
            ResponseEntity<Category> response =
                    testRestTemplate.postForEntity(Category_URL, crearHttpRequest(json), Category.class);

            assertEquals(400, response.getStatusCodeValue());
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertFalse(response.hasBody());
        }




    @Nested
    class updateTest{

        @DisplayName("comprobamos  que al hacer update de esa categoria se modifica")
        @Test
        void updateOkTest() {

            Category category = createDataCategories();
            String json = String.format("""
                {
                    "id": %d,
                    "name": "Category Modified",
                    "color": "Green"
                  
                }
                """, category.getId());
            System.out.println(json);

            ResponseEntity<Category> response =
                    testRestTemplate.exchange(Category_URL, HttpMethod.PUT, crearHttpRequest(json), Category.class);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.hasBody());
            assertNotNull(response.getBody());

            Category responseProduct = response.getBody();

            assertEquals (category.getId(),responseProduct.getId() );
            assertEquals("Category Modified", responseProduct.getName());
            assertNotEquals(responseProduct.getName(), category.getName());
        }

        @DisplayName("comprobamos si al hacer update se mantiene la id null")
        @Test
        void updateNullTest() {
            Category product = createDataCategories();
            String json = String.format("""
                {
                    "id": null,
                    "name": "Nueva Categoria Nula",
                    "color": "un nuevo color"
                    
                }
                """, product.getId());
            System.out.println(json);
            ResponseEntity<Category> response =
                    testRestTemplate.exchange(Category_URL, HttpMethod.PUT, crearHttpRequest(json), Category.class);
            assertEquals(400, response.getStatusCodeValue());
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @DisplayName("comprobar si al hacer update no encuentra la id que le hemos indicado")
        @Test
        void updateIdNotFoundTest() {
            Category product = createDataCategories();
            String json = String.format("""
                {
                    "id": 80,
                    "name": "Category n80",
                    "color": "color example"
                   
                }
                """, product.getId());
            System.out.println(json);
            ResponseEntity<Category> response =
                    testRestTemplate.exchange(Category_URL, HttpMethod.PUT, crearHttpRequest(json), Category.class);
            assertEquals(404, response.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }



    }

    @Nested
    class deletedTest{
        @DisplayName("comprobamos que borra correctamente con una Id")
        @Test
        void deleteByIdSuccesTest() {
            Category category = createDataCategories();
            String archive = Category_URL + "/" + category.getId();
            ResponseEntity<Category> response = testRestTemplate.getForEntity(archive, Category.class);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(category.getId(), response.getBody().getId());

            testRestTemplate.delete(archive);

            ResponseEntity<Category> response2 = testRestTemplate.getForEntity(archive, Category.class);

            assertEquals(404, response2.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
            assertFalse(response2.hasBody());

        }
        @DisplayName("comprobamos que no se borra una categoria con una  Id null")
        @Test
        void deleteNullIdTest() {
            Category category = createDataCategories();

            String archive = Category_URL + "/800" + category.getId();

            ResponseEntity<Category> response = testRestTemplate.getForEntity(archive, Category.class);

            assertEquals(404, response.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

            ResponseEntity<Category> response2 = testRestTemplate.getForEntity(archive, Category.class);
            testRestTemplate.delete(archive);




        }

        @DisplayName("comprobamos que borramos todas las categorias ")
        @Test
        void deleteAllSuccess() {
            createDataCategories();
            createDataCategories();

            ResponseEntity<Category[]> response = testRestTemplate.getForEntity(Category_URL, Category[].class);

            assertNotNull(response.getBody());

            List<Category> categories = List.of(response.getBody());

            assertTrue(categories.size() >= 2);

            testRestTemplate.delete(Category_URL);
            response = testRestTemplate.getForEntity(Category_URL, Category[].class);

            assertNotNull(response.getBody());

            categories = List.of(response.getBody());
            assertEquals(0, categories.size());
        }
    }



    private Category createDataCategories() {
        String json = """
                {
                    "name": "Categoria creada desde Rest",
                    "color": "Color maravilloso"
                              
                }
                """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Category> response =
                testRestTemplate.postForEntity(Category_URL, request, Category.class);
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


