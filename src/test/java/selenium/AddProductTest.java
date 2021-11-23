package selenium;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddProductTest extends BaseTest{

    private static final String URL1 = "http://localhost:8082/products";
    private static final String URL2 = "http://localhost:8082/products/new";

    @Test
    void testTitle(){
        driver.get(URL1);

        String title = driver.getTitle();
        assertEquals("Product List | Awesome App",title);

        WebElement h1 = driver.findElement(By.tagName("h1"));
        String h1Text = h1.getText();
        assertEquals("Products Directory", h1Text);
    }

    /**
     * Añadimos un fabriante pulsando en el botón "AÑADIR FABRICANTE"
     */

    @Test
    void addBuilderButtonTestOK() {
        driver.get(URL1);

        String title1 = driver.getTitle();
        assertEquals("Product List | Awesome App",title1);

        WebElement addElement = driver.findElement(By.xpath("/html/body/div/p[2]/a[1]"));
        assertEquals("btn btn-primary", addElement.getAttribute("class"));
        addElement.click();
        sleep();

        String title2 = driver.getTitle();
        assertEquals("Product Edition | Aswesome App",title2);
    }

    @Test
    void testTitle2(){
        driver.get(URL2);

        String title2 = driver.getTitle();
        assertEquals("Product Edition | Aswesome App",title2);

       WebElement h2 = driver.findElement(By.tagName("h2"));
       String h1Text = h2.getText();
       assertEquals("Product", h1Text);
    }

    @Test
    void TextBoxInputNameTestOK(){
        driver.get(URL2);

        WebElement inputName = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        inputName.sendKeys("INDITEX");
        sleep();

        inputName = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        String inputValue = inputName.getAttribute("value");
        assertEquals("INDITEX", inputValue);
    }

    @Test
    void TextBoxInputDescriptionTestOK(){
        driver.get(URL2);

        WebElement inputDescription = driver.findElement(By.cssSelector("#description"));
        inputDescription.sendKeys("Tienda de ropa");
        sleep();

        inputDescription = driver.findElement(By.cssSelector("#description"));
        String inputValue = inputDescription.getAttribute("value");
        assertEquals("Tienda de ropa", inputValue);
    }

    @Test
    void TextBoxInputPrecioTestOK(){
        driver.get(URL2);

        WebElement inputPrecio = driver.findElement(By.id("price"));
        inputPrecio.sendKeys("25");
        sleep();

        inputPrecio = driver.findElement(By.id("price"));
        String inputValue = inputPrecio.getAttribute("value");
        assertEquals("25", inputValue);
    }

    @Test
    void TextBoxInputCantidadTestOK(){
        driver.get(URL2);

        WebElement inputCantidad = driver.findElement(By.xpath("//*[@id=\"quantity\"]"));
        inputCantidad.sendKeys("25");
        sleep();

        inputCantidad = driver.findElement(By.xpath("//*[@id=\"quantity\"]"));
        String inputValue = inputCantidad.getAttribute("value");
        assertEquals("25", inputValue);
    }

    private void sleep() {
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
