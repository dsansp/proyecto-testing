package com.example.proyectotesting.selenium;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManufacturerEditTest extends BaseTest {


    private static final String manufacturersURL = "http://localhost:8082/manufacturers";
    private static final String editURL = "http://localhost:8082/manufacturers/1/edit";


    /**
     * Acceder desde la lista de fabricantes pulsando Editar
    */
    @Test
    @DisplayName("Click boton editar")
    void buttonEditar() {
        driver.get(manufacturersURL);
        WebElement button = driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[8]/a[2]"));
        button.click();

    }

    @Test
    @DisplayName("Titulo del fabricante")
    void CheckTitleAdidasTextTest() {

        driver.get(editURL);

        assertEquals("Fabricante 1", driver.
                findElement(By.cssSelector("h2")).getText());
        assertEquals("Manufacturer Edition | Aswesome App", driver.getTitle());

    }

    @Test
    @DisplayName("Click boton guardar/salir ")
    void buttonSaveAndExit() {
        driver.get(editURL);
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.submit();

        assertEquals("Manufacturer List | Awesome App", driver.getTitle());
    }

    @Test
    @DisplayName("Seleccionar producto zapatillas del menu")
    void adidasSelectZapatillas() {

        driver.get(editURL);

        WebElement input = driver.findElement(By.xpath("//option[@value='13']"));
        input.click();

        assertEquals(true, driver.findElement(By.xpath("//option[@value='13']")).isSelected());

        //status = "passsed";
    }
    @Test
    @DisplayName("Seleccionar todos productos del menu")
    void adidasSelectAll() {

        driver.get(editURL);
        driver.manage().window().maximize();

        WebElement multiSelect = driver.findElement(By.xpath("//*[@id=\"products\"]"));
        js.executeScript("arguments[0].scrollIntoView();", multiSelect);
        multiSelect.click();

        // equivalente xpath: //select[@id='cars']/option
        List<WebElement> options = driver.findElements(By.cssSelector("#products option"));

        for (WebElement option: options) {
            Actions action = new Actions(driver);
            action.keyDown(Keys.CONTROL).click(option).perform();
        }
    }

}
