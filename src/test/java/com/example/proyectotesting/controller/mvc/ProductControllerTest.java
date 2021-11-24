package com.example.proyectotesting.controller.mvc;

import static org.junit.jupiter.api.Assertions.*;

import com.example.proyectotesting.entities.Manufacturer;
import com.example.proyectotesting.entities.Product;
import com.example.proyectotesting.repository.ManufacturerRepository;
import com.example.proyectotesting.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    ManufacturerRepository manufacturerRepository;
    @Autowired
    ProductRepository repository;
    @Nested
    class borrar {
        @DisplayName("ProductControllerMVC-Borrar un producto en concreto")
        @Test
        void borrarProductoIdOk() throws Exception {
            Manufacturer manufacturer = new Manufacturer("Sampletester", "A0001", 150, 95);
            manufacturerRepository.save(manufacturer);
            Product product = new Product("sample2", "sample", 3, 45.5, manufacturer);
            product.setId(23L);
            repository.save(product);
            try {
                Product productDelete = new Product("sampleDelte", "sample", 3, 45.5, manufacturer);
                repository.save(productDelete);
                System.out.println(repository.findAll());
                mvc.perform(get("/products/23/delete"))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("/products"));
            } catch (Exception error) {
                System.out.println("No hay productos en la base de datos");
                error.printStackTrace();
                assertTrue(false);
            }
        }
@DisplayName("ProductControllerMVC-Borrar Todos los productos")
        @Test
        void borrarProductos() throws Exception {
            Manufacturer manufacturer = new Manufacturer("Sampletester", "A0001", 150, 95);
            manufacturerRepository.save(manufacturer);
            Product product = new Product("sample3", "sample", 5, 45.5, manufacturer);
            repository.save(product);
            Product product2 = new Product("sample4", "sample", 95, 15.5, manufacturer);
            repository.save(product2);
            mvc.perform(get("/products/delete/all"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/products"));

        }
    }

@DisplayName("ProductControllerMVC-Obtener una lista")
    @Test
    void obtenerLista() throws Exception {
        mvc.perform(get("/products/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("products"))
                .andExpect(view().name("product-list")) // comprobar los atributos cargados en el modelo
                .andExpect(forwardedUrl("/WEB-INF/views/product-list.jsp"));

    }
 @DisplayName("ProductControllerMVC-Obtener un formulario")
    @Test
    void obtenerFormulario() throws Exception {
        mvc.perform(get("/products/new/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeExists("manufacturers"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(forwardedUrl("/WEB-INF/views/product-edit.jsp"));
    }
@DisplayName("ProductControllerMVC-Crear un producto")
    @Test
    void crearProducto() throws Exception {
        mvc.perform(
                        post("/products")
                                .param("name", "producto prueba")
                                .param("description", "Descripción producto")
                                .param("price", "99.88")
                                .param("quantity", "5")
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));


    }
    @DisplayName("ProductControllerMVC-Editar un prooducto encontrado")
    @Test
    void editarProductoFound() throws Exception {
        Manufacturer manufacturer = new Manufacturer("Sampletester", "A0001", 150, 95);
        manufacturerRepository.save(manufacturer);
        Product product = new Product("sample", "sample", 3, 45.5, manufacturer);
        product.setId(20L);
        repository.save(product);
        System.out.println(repository.findAll());
        mvc.perform(get("/products/20/edit/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(MockMvcResultMatchers.view().name("product-edit"))
                .andExpect(forwardedUrl("/WEB-INF/views/product-edit.jsp"));
    }
    @DisplayName("ProductControllerMVC- Editar un producto no encontrado")
    @Test
    void editarNotPresentTest() throws Exception {
        mvc.perform(get("/products/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/products"));
    }

    /**
     * generamos un producto de prueba y lo encontramos
     * @throws Exception
     */
    @DisplayName("ProductControllerMVC- ver un producto, encontrándolo")
    @Test
    void verProductoFound() throws Exception {

        Manufacturer manufacturer = new Manufacturer("Sampletester", "A0001", 150, 95);
        manufacturerRepository.save(manufacturer);
        Product product = new Product("sample3", "sample", 5, 45.5, manufacturer);
        product.setId(18L);
        repository.save(product);
     ;
        System.out.println(repository.findAll());
        mvc.perform(get("/products/18/view"))
                .andExpect(status().isOk())
.andExpect( forwardedUrl("/WEB-INF/views/product-view.jsp"));

    }
    @DisplayName("ProductControllerMVC- En caso de no encontrar un producto")
    @Test
    void verProductoNotFound() throws Exception {
        mvc.perform(get("/products/9999/view"))
                .andExpect(status().is3xxRedirection());



    }

    /**
     * Generamos un fabricante de prueba y comprobamos que lo encuentra
     * @throws Exception
     */
    @DisplayName("ProductControllerMVC-Buscar formulario por fabricante")
    @Test
    void formWithManufacturer() throws Exception {
        Manufacturer manufacturer = new Manufacturer("Sampletester", "A0001", 150, 95);
        manufacturer.setId(14L);
        manufacturerRepository.save(manufacturer);
        mvc.perform(get("/products/new/manufacturer/14"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("manufacturer"))
                .andExpect( forwardedUrl("/WEB-INF/views/product-edit-withmanufacturer.jsp"));

    }
}