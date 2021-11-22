package com.example.proyectotesting.controller.mvc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ManufacturerControllerTest {

    @Autowired
    MockMvc mvc;


    @DisplayName("findAll()->Comprobando que se encuentran los manufacturer")
    @Test
    void list() throws Exception {
        mvc.perform(get("/manufacturers")) // url a testear: http://localhost:8082/manufacturer
                .andExpect(status().isOk()) // estado HTTP de la respuesta 200
                .andExpect(model().attributeExists("manufacturers")) // comprobar los atributos cargados en el modelo
                .andExpect( forwardedUrl("/WEB-INF/views/manufacturer-list.jsp")); // vista que se mostrará
    }

    @DisplayName("findOne()->Comprobando que se muestra un manufacturer")
    @Test
    void view() throws Exception {
        mvc.perform(get("/manufacturers/1/view"))
                .andExpect(model().attributeExists("manufacturer"))
                .andExpect(forwardedUrl("/WEB-INF/views/manufacturer-view.jsp"));

    }

    @DisplayName("Comprobamos que no se encuentra el manufacturer 80")
    @Test
    void viewNotFound() throws Exception {
        mvc.perform(get("/manufacturers/80/view"))
                .andExpect(redirectedUrl("/manufacturers"))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Comprobamos que se encuentra un manufacturer en el formulario")
    @Test
    void loadForm() throws Exception {
        mvc.perform(get("/manufacturers/1/edit"))
                .andExpect(forwardedUrl("/WEB-INF/views/manufacturer-edit.jsp"))
                .andExpect(model().attributeExists("manufacturer"))
                .andExpect(model().attributeExists("products"))
                .andExpect(redirectedUrl("manufacturer-edit"));
    }



    @DisplayName("Comprobando que se muestra el manufacturer en el formulario")
    @Test
    void showFormManufacturer() throws Exception{
        mvc.perform(get("/manufacturers/new"))
                .andExpect( forwardedUrl("/WEB-INF/views/manufacturer-edit.jsp"))
                .andExpect(model().attributeExists("manufacturer"))
                .andExpect(model().attributeExists("products"));
    }




    @DisplayName("Comprobando que se crea un manufacturer y se guarda en DB")
    @Test
    void save() throws Exception {
        mvc.perform(
                post("/manufacturers")
                        .param("name", "manufacturer prueba")
                        .param("cif","567845H")
                        .param("numEmployees", "400")
                        .param("year","2020")
        ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/manufacturers"));


    }




    @DisplayName("deleteById()->Comprobamos que se elimina un manufacturer")
    @Test
    void delete() throws Exception {
        mvc.perform(get("/manufacturers/1/delete", "1L").accept("id"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/manufacturers"));
    }

    @DisplayName("findAll()->Comprobando que se borran todos los manufacrturer")
    @Test
    void deleteAll() throws Exception {
        mvc.perform(get("/manufacturers/delete/all"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/manufacturers"));



    }
}