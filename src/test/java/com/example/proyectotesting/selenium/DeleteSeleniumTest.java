package com.example.proyectotesting.selenium;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteSeleniumTest extends BaseTest {


    private static final String URL = "https://proyectogrupo1testing.herokuapp.com/products";
    // Navegador
        WebDriver driver;

        @BeforeEach
        void setUp() {
            System.getenv().forEach((key, value) -> System.out.println(key + " " + value));
            System.getProperties().forEach((key, value) -> System.out.println(key + " " + value));

            if(System.getProperties().get("os.name").equals("Linux")){
                System.out.println("Configurando Navegador Chrome Headless para CI");
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            }else{
                System.out.println("Configurando Navegador Chrome desde carpeta drivers para testing en desarrollo");
                String dir = System.getProperty("user.dir"); // ruta del proyecto
                String driverUrl = "/drivers/chromedriver.exe";
                String url = dir + driverUrl;
                System.setProperty("webdriver.chrome.driver", url);
                driver = new ChromeDriver(); // Google Chrome
            }
        }

        @AfterEach
        void tearDown() {
            driver.quit();
        }

    /**
     * Probamos de pulsar el botón borrar productos, habiendo
     * productos listados
     */
        @Test
        void DeleteallTest() {
            driver.manage().window().maximize();
            driver.get(URL);
            WebElement h1 = driver.findElement(By.tagName("h1"));
            String h1Text = h1.getText();
            assertEquals("Products Directory", h1Text);
           int first= (driver.findElements(By.cssSelector("#products-list > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(7) > a:nth-child(2)")).size());
           assertTrue(first>=1);

            driver.findElement(By.xpath("/html/body/div/p[2]/a[2]")).click();
            int resultdespues = driver.findElements(By.cssSelector("html body div.pt-5.container table#products-list.table.table-striped.table-bordered tbody tr td a.btn.btn-info")).size();

            assertEquals(0, resultdespues);

        }

    /**
     * Probamos de pulsar el botón borrar productos, estando ya vacio el
     * campo de productos
     */
        @Test
        void DeleteEmptyListTest() {
            driver.manage().window().maximize();
            driver.get(URL);
            WebElement h1 = driver.findElement(By.tagName("h1"));
            String h1Text = h1.getText();
            assertEquals("Products Directory", h1Text);
            driver.findElement(By.xpath("/html/body/div/p[2]/a[2]")).click();
            int check = driver.findElements(By.cssSelector("html body div.pt-5.container table#products-list.table.table-striped.table-bordered tbody tr td a.btn.btn-info")).size();

            System.out.println(check);
            assertEquals(0, check);
            driver.findElement(By.xpath("/html/body/div/p[2]/a[2]")).click();

            int after = driver.findElements(By.cssSelector("html body div.pt-5.container table#products-list.table.table-striped.table-bordered tbody tr td a.btn.btn-info")).size();
            assertEquals(check, after);
            WebElement h1After = driver.findElement(By.tagName("h1"));
            String h1Text2 = h1After.getText();
            assertEquals("Products Directory", h1Text2);


        }


    }

