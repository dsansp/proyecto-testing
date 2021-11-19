package com.example.proyectotesting.controller.mvc;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ManufacturerControllerTest {

    @Autowired
    MockMvc mvc;


    @Test
    void list() throws Exception {
        mvc.perform(get("/manufacturers")) // url a testear: http://localhost:8082/manufacturer
                .andExpect(status().isOk()) // estado HTTP de la respuesta 200
                .andExpect(model().attributeExists("manufacturers")) // comprobar los atributos cargados en el modelo
                .andExpect( forwardedUrl("/WEB-INF/views/manufacturer-list.jsp")); // vista que se mostrará
    }

//    @Test
//    void view() throws Exception {
//        mvc.perform(get("/manufacturer-view")
//                andEx
//        );
//
//    }

    @Test
    void loadForm() {
    }

    @Test
    void showForm() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }
}