package selenium;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteSeleniumTest extends BaseTest {


        private static final String URL = "http://localhost:8082/products";
        private static final String AddURL = "http://localhost:8082/products/new";
        private static final String ProductURL = "http://localhost:8082/products/9/view";

        //    private static final String URL = "http://localhost:8082/products";
        // Navegador
    /**
     * Probamos de pulsar un botón borrar dentro de un producto concreto, y leugo comprobamos que no existe en
     * los productos listados
     */
    @Test
    void deleteButtonProduct(){
        driver.manage().window().maximize();
        driver.get(ProductURL);
        WebElement h2 = driver.findElement(By.tagName("h2"));
        String h2Text = h2.getText();
        assertEquals("Producto 9", h2Text);
        driver.findElement(By.cssSelector("a.btn:nth-child(10)")).click();
        WebElement producto9= driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[7]/a[3]"));
        assertNotEquals("Mesa", producto9.getText());
    }

        /**
         * Probamos de pulsar el botón borrar productos, estando ya vacio el
         * campo de productos
         */
        @Test
        void deleteEmptyListTest() {
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

        /**
         * Probamos de pulsar el botón borrar todos los productos, habiendo
         * productos listados
         */


        @Test
        void deleteallTest() {
//        createDemoProduct();
//        createDemoProduct();
            driver.manage().window().maximize();
            driver.get(URL);
            WebElement h1 = driver.findElement(By.tagName("h1"));
            String h1Text = h1.getText();
            assertEquals("Products Directory", h1Text);
            int first = (driver.findElements(By.cssSelector("#products-list > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(7) > a:nth-child(2)")).size());
            driver.findElement(By.xpath("/html/body/div/p[2]/a[2]")).click();
            int resultdespues = driver.findElements(By.cssSelector("html body div.pt-5.container table#products-list.table.table-striped.table-bordered tbody tr td a.btn.btn-info")).size();

            assertEquals(0, resultdespues);

        }


//    void createDemoProduct() {
//        driver.get(AddURL);
//        WebElement h2 = driver.findElement(By.tagName("h2"));
//        String hText = h2.getText();
//        assertEquals("Product", hText);
//        driver.findElement(By.id("name")).sendKeys("Producto de prueba");
//        driver.findElement(By.id("description")).sendKeys("Descripción de prueba");
//        driver.findElement(By.id("price")).sendKeys("99,99");
//        driver.findElement(By.id("quantity")).sendKeys("10");
//        driver.findElement(By.cssSelector("#categories > option:nth-child(1)"));
//        driver.findElement(By.xpath("/html/body/div/div/div/form/div[7]/button")).click();
//    }
        /**
         * Probamos de pulsar un botón borrar de un producto concreto, existiendo en
         * los productos listados
         */
        @Test
        void deleteButtonMain() {
            driver.manage().window().maximize();
            driver.get(URL);
            WebElement h1 = driver.findElement(By.tagName("h1"));
            String h1Text = h1.getText();
            assertEquals("Products Directory", h1Text);
            //   createDemoProduct();

            List<WebElement> deleteButtons = driver.findElements(By.xpath("//span[contains(@class, 'btn btn-danger')]"));
            for(int i = deleteButtons.size(); i > 0; i--) { // for(int i = 0; i < deleteButtons.size(); i++){
                deleteButtons = driver.findElements(By.xpath("//span[contains(@class, 'btn btn-danger')]"));
                deleteButtons.get(0).click();
                assertEquals(i - 1, driver.findElements(By.cssSelector("#products-list > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(7) > a:nth-child(3)")).size());
            }

        }


    }