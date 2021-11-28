package selenium;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Disabled
public class ManufacturerEditTest extends BaseTest {


    private static final String manufacturersURL = "https://proyectogrupo1testing.herokuapp.com/manufacturers";
    private static final String editURL = "https://proyectogrupo1testing.herokuapp.com/products/14/edit";
    private static final String newURL = "https://proyectogrupo1testing.herokuapp.com/manufacturers/new";
    private static final String viewURL = "https://proyectogrupo1testing.herokuapp.com/manufacturers/14/view";
   // http://localhost:8082/manufacturers http://localhost:8082/manufacturers/1/edit


    private void newFabricante() {
        driver.get(newURL);
        driver.findElement(By.cssSelector("#name")).sendKeys("Test");
        driver.findElement(By.cssSelector("#cif")).sendKeys("2347675443");
        driver.findElement(By.cssSelector("#numEmployees")).sendKeys("66666");
        driver.findElement(By.cssSelector("#year")).sendKeys("2000");
        driver.findElement(By.cssSelector("#direction\\.street")).sendKeys("castellana 69");
        driver.findElement(By.cssSelector("#direction\\.postalCode")).sendKeys("13326");
        driver.findElement(By.cssSelector("#direction\\.city")).sendKeys("Montiel");
        driver.findElement(By.cssSelector("#direction\\.country")).sendKeys("Filipinas");
//seleccionar producto
        WebElement input = driver.findElement(By.xpath("/html/body/div/div/div/form/div[9]/select/option"));
       // js.executeScript("arguments[0].scrollIntoView();", input);
        js.executeScript("arguments[0].scrollIntoView();", input);

        input.click();

//guardar #products
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.submit();

    }

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

    /**
     * Acceder a edit fabricante pulsando Editar
     */

    @Test
    @DisplayName("Titulo del fabricante")
    void CheckTitleTestTextTest() {
    newFabricante();
        System.out.println("creado fabricante de prueba");
        driver.get(editURL);

        assertEquals("Fabricante 14", driver.
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
    @DisplayName("Seleccionar producto del menu")
    void adidasSelectProduct() {

        driver.get(editURL);

        WebElement input = driver.findElement(By.cssSelector("#products > option:nth-child(4)"));
        input.click();

        assertEquals(true, driver.findElement(By.cssSelector("#products > option:nth-child(4)")).isSelected());

        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.submit();

        //status = "passsed";
    }

    @Test
    @DisplayName("Seleccionar todos productos del menu")
    void adidasSelectAll() {

        driver.get(editURL);
        driver.manage().window().maximize();

        List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"products\"]"));

        options.clear();

        for (WebElement option: options) {
            Actions action = new Actions(driver);
            action.keyDown(Keys.CONTROL).click(option).perform();
        }
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.submit();
    }

    @Test
    @DisplayName("comprobar los titulos de el manufacturer")
    void AdidasDataTitles() {
        driver.get(editURL);
        driver.manage().window().maximize();

        List<WebElement> dataTitle = driver.findElements(By.cssSelector("h3"));
        assertEquals("Datos de fabricante",dataTitle.get(0).getText());
        assertEquals("Datos de dirección",dataTitle.get(1).getText());

        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.submit();
    }

    @Test
    @DisplayName("Comprobar manufacturer data edicion ")
    void editManufacturerAdidasData() {
        driver.get(editURL);

        driver.findElement(By.cssSelector("#name")).clear();
        driver.findElement(By.cssSelector("#name")).sendKeys("Test");
        driver.findElement(By.cssSelector("#cif")).clear();
        driver.findElement(By.cssSelector("#cif")).sendKeys("2347675443");
        driver.findElement(By.cssSelector("#numEmployees")).clear();
        driver.findElement(By.cssSelector("#numEmployees")).sendKeys("66666");
        driver.findElement(By.cssSelector("#year")).clear();
        driver.findElement(By.cssSelector("#year")).sendKeys("2000");

        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.submit();


        // check if data is edited in manufacturer list
        driver.get(manufacturersURL);
        assertEquals("Manufacturer List | Awesome App", driver.getTitle());

        assertEquals("Adidas Shoes Inc", driver.findElement(By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(1)")).getText());
        assertEquals("2347675443", driver.findElement(By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(2)")).getText());
        assertEquals("66666", driver.findElement(By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(3)")).getText());
        assertEquals("2000", driver.findElement(By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(4)")).getText());


    }

    @Test
    @DisplayName("Comprobar direction data edicion")
    void editManufacturerAdidasDirection() {
        driver.get(editURL);

        driver.findElement(By.cssSelector("#direction\\.street")).clear();
        driver.findElement(By.cssSelector("#direction\\.street")).sendKeys("castellana 69");
        driver.findElement(By.cssSelector("#direction\\.postalCode")).clear();
        driver.findElement(By.cssSelector("#direction\\.postalCode")).sendKeys("13326");
        driver.findElement(By.cssSelector("#direction\\.city")).clear();
        driver.findElement(By.cssSelector("#direction\\.city")).sendKeys("Montiel");
        driver.findElement(By.cssSelector("#direction\\.country")).clear();
        driver.findElement(By.cssSelector("#direction\\.country")).sendKeys("Filipinas");

        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.submit();


        // check if data is edited in manufacturer list
        driver.get(manufacturersURL);
        assertEquals("Manufacturer List | Awesome App", driver.getTitle());

        assertEquals("castellana 69", driver.findElement(By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(5)")).getText());
        assertEquals("Filipinas", driver.findElement(By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(6)")).getText());


    }
}
