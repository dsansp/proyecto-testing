package com.example.proyectotesting.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

public class ProductListTest extends BaseTest{


    private static final String URL = "https://proyectogrupo1testing.herokuapp.com/products";
    @Disabled
    @Test
    void cargarPaginaTest(){
        webdriver.get(URL);
        webdriver.findElement(By.xpath("//*[@id=\"products-list\"]/tbody/tr[2]/td[7]/a[1]")).click();


    }


}
