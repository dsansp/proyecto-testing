package selenium;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteSeleniumTest extends BaseTest {


        private static final String URL = "https://proyectogrupo1testing.herokuapp.com/products";
        private static final String AddURL = "https://proyectogrupo1testing.herokuapp.com/products/new";
        private static final String ProductURL = "https://proyectogrupo1testing.herokuapp.com/products/14/view";

        //    private static final String URL = "http://localhost:8082/products"; "http://localhost:8082/products/new";"http://localhost:8082/products/9/view"
        // Navegador
    /**
     * Probamos de pulsar un botón borrar dentro de un producto concreto, y leugo comprobamos que no existe en
     * los productos listados
     */
    @Disabled
    @Test
    void deleteButtonProduct(){
        driver.manage().window().maximize();
        createDemoProduct();
        driver.get(ProductURL);

        WebElement h2 = driver.findElement(By.tagName("h2"));
        String h2Text = h2.getText();
        assertEquals("Producto 14", h2Text);
        driver.findElement(By.cssSelector("a.btn:nth-child(10)")).click();
        WebElement producto14= driver.findElement(By.xpath("/html/body/div/table/tbody/tr[7]/td[7]/a[3]"));
        assertNotEquals("producto de test", producto14.getText());
    }

    private void createDemoProduct() {
        driver.manage().window().maximize();
        driver.get(AddURL);
        WebElement inputName = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        inputName.sendKeys("producto de test");
        WebElement inputDescription = driver.findElement(By.cssSelector("#description"));
        inputDescription.sendKeys("prueba");
        WebElement inputPrecio = driver.findElement(By.id("price"));
        inputPrecio.sendKeys("25");
        WebElement inputCantidad = driver.findElement(By.xpath("//*[@id=\"quantity\"]"));
        inputCantidad.sendKeys("1");
        WebElement selector = driver.findElement(By.id("manufacturer"));
        WebElement selectorcat = driver.findElement(By.id("categories"));
        Select selectCategoria = new Select(selectorcat);

        selectCategoria.selectByVisibleText("Libros");
        WebElement button = driver.findElement(By.xpath("//*[@id=\"product\"]/div[7]/button"));
        button.submit();
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