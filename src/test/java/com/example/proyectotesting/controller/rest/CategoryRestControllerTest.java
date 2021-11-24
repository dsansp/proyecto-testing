package com.example.proyectotesting.controller.rest;

import com.example.proyectotesting.entities.Category;
import com.example.proyectotesting.repository.CategoryRepository;
import com.example.proyectotesting.service.CategoryService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryRestControllerTest {

    private static final String Category_URL = "/api/category";
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @Mock//dependencia
    private CategoryService categoryService;

    @InjectMocks//dependiente del Mock
    private CategoryRestController categoryRestController;

    @BeforeEach
    @Test
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

        MockitoAnnotations.openMocks(this);
        this.categoryRestController = new CategoryRestController(categoryService);
    }

    /**
     * Clase que comprueba los test de find
     */

    @Nested
    public class findTests {

        /**
         * Método que comprueba  que se buscan todas las categorias
         * devuelve  como respuesta un OK que es el numero 200
         */

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

        /**
         * Método que comprueba  que se buscan una categoria
         * devuelve  como respuesta un OK que es el numero 200
         */

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

        /**
         * Método que comprueba  que cuando se busca una categoria que no se encuentra en el JSON
         * devuelve  como respuesta un NOT_FOUND que es el numero 404
         */

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


    /**
     * Método que comprueba  que se crea correctamente una categoria
     * devuelve  como respuesta un OK que es el numero 200
     */


    @DisplayName("Comprobamos que se crea correctamente la categoria")
    @Test
    void createSuccesTest() {
        String json = """
                {
                    "name": "Categoria creada desde Rest Test",
                    "color": "Color maravilloso"
                }
                """;
        ResponseEntity<Category> respuesta = testRestTemplate.postForEntity(Category_URL, createHttpRequest(json), Category.class);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertTrue(respuesta.hasBody());

        Category category = respuesta.getBody();

        assertNotNull(category);
        assertEquals("Categoria creada desde Rest Test", category.getName());


    }


    /**
     * Método que comprueba  que no se puede crear la categoria
     * devuelve  como respuesta un BAD_REQUEST que es el numero 400
     */

    @Test
    void createResponse400Test() {

        String json = """
                {
                    "id": 5,
                    "name": "Category creado desde JUnit",
                    "color": "color example"
                }
                """;

        ResponseEntity<Category> response =
                testRestTemplate.postForEntity(Category_URL, createHttpRequest(json), Category.class);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.hasBody());
    }


    /**
     * Clase que comprueba los test de update
     */

    @Nested
    class updateTest {

        /**
         * Método que comprueba que se puede actualizar una categoria
         * devuelve como respuesta un OK que es el numero 200
         */

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
                    testRestTemplate.exchange(Category_URL, HttpMethod.PUT, createHttpRequest(json), Category.class);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.hasBody());
            assertNotNull(response.getBody());

            Category responseCategory = response.getBody();

            assertEquals(category.getId(), responseCategory.getId());
            assertEquals("Category Modified", responseCategory.getName());
            assertNotEquals(responseCategory.getName(), category.getName());
        }

        /**
         * Método que comprueba que se no se puede actualizar una categoria null
         * devuelve como respuesta un BAD_REQUEST que es el numero 400
         */

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
                    testRestTemplate.exchange(Category_URL, HttpMethod.PUT, createHttpRequest(json), Category.class);
            assertEquals(400, response.getStatusCodeValue());
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }
        /**
         * Método que comprueba que se no se puede actualizar una categoria que no existe
         * devuelve como respuesta un NOT_FOUND que es el numero 404
         */

        @DisplayName("comprobar si al hacer update no encuentra la id que le hemos indicado")
        @Test
        void updateDontFoundIdTest() {
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
                    testRestTemplate.exchange(Category_URL, HttpMethod.PUT, createHttpRequest(json), Category.class);
            assertEquals(404, response.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }


    }

    /**
     * Clase que comprueba los test de delete
     */

    @Nested
    class deletedTest {
        /**
         * Método que comprueba que se puede borrar una categoria
         * devuelve como respuesta un OK que es el numero 202
         */

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
        /**
         * Método que comprueba que se no se puede borrar una categoria que es null
         * devuelve como respuesta un NOT_FOUND que es el numero 404
         */

        @DisplayName("comprobamos que no se borra una categoria con una  Id null")
        @Test
        void deleteNullIdTest() {
            Category category = createDataCategories();

            String archive = Category_URL + "/100" + category.getId();

            ResponseEntity<Category> response = testRestTemplate.getForEntity(archive, Category.class);

            assertEquals(404, response.getStatusCodeValue());
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

            ResponseEntity<Category> response2 = testRestTemplate.getForEntity(archive, Category.class);
            testRestTemplate.delete(archive);


        }

        @Test
        void noCoudntDeleteIdTest(){
            ResponseEntity<Category> response = deleteIdmockTest();

            assertEquals(409, response.getStatusCodeValue());

            assertEquals(HttpStatus.CONFLICT, response.getStatusCode());


        }

        private ResponseEntity<Category> deleteIdmockTest() {
            CategoryRepository categoryRepository = mock(CategoryRepository.class);

            doReturn(true).when(categoryService).existsById(null);
            doReturn(false).when(categoryService).deleteById(null);

            doThrow(RuntimeException.class).when(categoryRepository).deleteById(null);

            return categoryRestController.delete(null);

        }

        @Disabled
        @DisplayName("Comprobamos que no se borra las  categorias")
        @Test
        void deleteAllTest(){
            ResponseEntity<Category> response = testRestTemplate.exchange(Category_URL, HttpMethod.DELETE, createHttpRequest(null),Category.class);

            assertEquals(204, response.getStatusCodeValue());
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        }




        /**
         * Método que lanzará una excepcion 409 en el metodo de deleteAll()
         * devuelve como respuesta un CONFLICT que es el numero 409
         */
        @Test
        void noCoudntDeleteAllTest(){
            ResponseEntity<Category> response = deleteAllmockTest();
            assertEquals(409, response.getStatusCodeValue());
            assertEquals(HttpStatus.CONFLICT, response.getStatusCode());


        }

        private ResponseEntity<Category> deleteAllmockTest() {
            CategoryRepository categoryRepository = mock(CategoryRepository.class);

            doReturn(false).when(categoryService).deleteAll();
            doThrow(RuntimeException.class).when(categoryRepository).deleteById(null);

            return categoryRestController.deleteAll();

        }


    }

    /**
     * Método que crea una categoria por defecto
     *
     * @return createDataCategories()
     */

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

    /**
     *Método que crea una respuesta de HTTP
     *
     * @param json String json
     * @return createHttpRequest(String json)
     */
    private HttpEntity<String> createHttpRequest(String json) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(json, headers);
    }
}


