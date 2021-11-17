package com.example.proyectotesting.selenium;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;

public class BaseTest {
    //Driver del navegador
    WebDriver webdriver;
    JavascriptExecutor js;




    @BeforeEach
    void setup() {
        //esta es la ruta del proyecto
        String dir = System.getProperty("user.dir");
        String driverUrl = "/driver/chromedriver.exe";
        String url = dir + driverUrl;
        System.setProperty("webdriver.chrome.driver", url);
        //Driver de Google Chrome
        webdriver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        webdriver.quit();

    }



}
