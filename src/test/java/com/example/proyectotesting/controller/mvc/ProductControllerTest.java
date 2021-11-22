package com.example.proyectotesting.controller.mvc;

import static org.junit.jupiter.api.Assertions.*;

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
    ProductRepository repository;
    @Nested
    class borrar {
        @Test
        void borrarProductoIdOk() throws Exception {
            mvc.perform(get("/products/9/delete"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/products"));
        }


        @Test
        void borrarProductos() throws Exception {

            mvc.perform(get("/products/delete/all"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/products"));

        }
    }

@Nested
class obterner {
    @Test
    void obtenerLista() throws Exception {
        mvc.perform(get("/products/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("products"))
                .andExpect(view().name("product-list")) // comprobar los atributos cargados en el modelo
                .andExpect(forwardedUrl("/WEB-INF/views/product-list.jsp"));

    }

    @Test
    void obtenerFormulario() throws Exception {
        mvc.perform(get("/products/new/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeExists("manufacturers"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(forwardedUrl("/WEB-INF/views/product-edit.jsp"));
    }
}
@Nested
class crearUpdate {
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
    @Test
    void editarProductoFound() throws Exception {
        mvc.perform(get("/products/11/edit/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/product-edit.jsp"));


    }
}
@Nested
class found{
    @Test
    void verProductoFound() throws Exception {
        mvc.perform(get("/products/9/view"))
                .andExpect(status().isOk())
.andExpect( forwardedUrl("/WEB-INF/views/product-view.jsp"));

    }
    @Test
    void verProductoNotFound() throws Exception {
        mvc.perform(get("/products/9999/view"))
                .andExpect(status().is3xxRedirection());



    }


    }
    @Test
    void formWithManufacturer() throws Exception {
        mvc.perform(get("/products/new/manufacturer/1"))
                .andExpect(model().attributeExists("manufacturer"))
                .andExpect( forwardedUrl("/WEB-INF/views/product-edit-withmanufacturer.jsp"));

    }
}