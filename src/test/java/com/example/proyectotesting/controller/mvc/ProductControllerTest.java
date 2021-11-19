package com.example.proyectotesting.controller.mvc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void obtenerLista() throws Exception {
        mvc.perform(get("/products/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("products"))
                .andExpect(view().name("product-list")) // comprobar los atributos cargados en el modelo
                .andExpect( forwardedUrl("/WEB-INF/views/product-list.jsp"));

    }

    @Test
    void obtenerFormulario() throws Exception {
        mvc.perform(get("/products/new/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeExists("manufacturers"))
                .andExpect(model().attributeExists("categories"))
                .andExpect( forwardedUrl("/WEB-INF/views/product-edit.jsp"));
    }

    @Test
    void crearProducto() {
    }

    @Test
    void verProducto() {
    }

    @Test
    void editarProducto() {
    }

    @Test
    void borrarProducto() {
    }

    @Test
    void borrarProductos() {
    }

    @Test
    void formWithManufacturer() {
    }
}