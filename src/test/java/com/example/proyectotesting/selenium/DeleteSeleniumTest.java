package com.example.proyectotesting.selenium;


import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;
public class DeleteSeleniumTest {


        // Navegador
        WebDriver driver;

        @BeforeEach
        void setUp() {
            String dir = System.getProperty("user.dir"); // ruta del proyecto
            String driverUrl = "/drivers/chromedriver.exe";
            String url = dir + driverUrl;
            System.setProperty("webdriver.chrome.driver", url);
            driver = new ChromeDriver(); // Google Chrome
        }

        @AfterEach
        void tearDown() {
            driver.quit();
        }

        @Test
        void DeleteallTest() {
            driver.manage().window().maximize();
            driver.get("http://localhost:8082/products");
            //   int resultBefore = driver.findElements(By.cssSelector("html body div.pt-5.container table#products-list.table.table-striped.table-bordered tbody tr td a.btn.btn-info")).size();

            assertTrue(driver.findElement(By.cssSelector("html body div.pt-5.container table#products-list.table.table-striped.table-bordered tbody tr td a.btn.btn-danger")).isDisplayed());


            int first= (driver.findElements(By.cssSelector("#products-list > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(7) > a:nth-child(2)")).size());
            System.out.println(first);

            driver.findElement(By.xpath("/html/body/div/p[2]/a[2]")).click();
            int resultdespues = driver.findElements(By.cssSelector("html body div.pt-5.container table#products-list.table.table-striped.table-bordered tbody tr td a.btn.btn-info")).size();

            System.out.println(resultdespues);
            assertEquals(0, resultdespues);

        }

        @Test
        void DeleteEmptyListTest() {
            driver.manage().window().maximize();
            driver.get("http://localhost:8082/products");
            driver.findElement(By.xpath("/html/body/div/p[2]/a[2]")).click();
            int check = driver.findElements(By.cssSelector("html body div.pt-5.container table#products-list.table.table-striped.table-bordered tbody tr td a.btn.btn-info")).size();

            System.out.println(check);
            assertEquals(0, check);
            driver.findElement(By.xpath("/html/body/div/p[2]/a[2]")).click();

            int after = driver.findElements(By.cssSelector("html body div.pt-5.container table#products-list.table.table-striped.table-bordered tbody tr td a.btn.btn-info")).size();
            assertEquals(check, after);


        }


    }

