package selenium;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddProductTest extends BaseTest{

    private static final String URL1 = "http://localhost:8082/products";
    private static final String URL2 = "http://localhost:8082/products/new";

    @Test
    @DisplayName("Comprobar el título de la página")
    void testTitle(){
        driver.get(URL1);

        String title = driver.getTitle();
        assertEquals("Product List | Awesome App",title);

        WebElement h1 = driver.findElement(By.tagName("h1"));
        String h1Text = h1.getText();
        assertEquals("Products Directory", h1Text);
    }

    /**
     * Añadimos un fabriante pulsando en el botón "AÑADIR PRODUCTO"
     */

    @Test
    @DisplayName("Comprobar que el botón AÑADIR PRODUCTO redirige a la url new")
    void addProductButtonTestOK() {
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
    @DisplayName("Comprobar el título de la página de nuevo producto ")
    void testTitle2(){
        driver.get(URL2);

        String title2 = driver.getTitle();
        assertEquals("Product Edition | Aswesome App",title2);

       WebElement h2 = driver.findElement(By.tagName("h2"));
       String h1Text = h2.getText();
       assertEquals("Product", h1Text);
    }

    @Test
    @DisplayName("Comprobar que se escribe el nombre en el TextBox")
    void TextBoxInputNameTestOK(){
        driver.get(URL2);

        WebElement inputName = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        inputName.sendKeys("Zapato negro");
        sleep();

        inputName = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        String inputValue = inputName.getAttribute("value");
        assertEquals("Zapato negro", inputValue);
    }

    @Test
    @DisplayName("Comprobar que se escribe la descripción en el TextBox")
    void TextBoxInputDescriptionTestOK(){
        driver.get(URL2);

        WebElement inputDescription = driver.findElement(By.cssSelector("#description"));
        inputDescription.sendKeys("Zapato de mujer negro con tacón medio");
        sleep();

        inputDescription = driver.findElement(By.cssSelector("#description"));
        String inputValue = inputDescription.getAttribute("value");
        assertEquals("Zapato de mujer negro con tacón medio", inputValue);
    }

    @Test
    @DisplayName("Comprobar que se escribe el precio en el TextBox")
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
    @DisplayName("Comprobar que se escribe la cantidad en el TextBox")
    void TextBoxInputCantidadTestOK(){
        driver.get(URL2);

        WebElement inputCantidad = driver.findElement(By.xpath("//*[@id=\"quantity\"]"));
        inputCantidad.sendKeys("1");
        sleep();

        inputCantidad = driver.findElement(By.xpath("//*[@id=\"quantity\"]"));
        String inputValue = inputCantidad.getAttribute("value");
        assertEquals("1", inputValue);
    }

    @Test
    @DisplayName("Comprobar que se selecciona un fabricante llamado Adidas Shoes Inc")
    void SelectSFirstBuilderTest(){
        driver.get(URL2);

        WebElement selector = driver.findElement(By.id("manufacturer"));
        Select selectFabricante = new Select(selector);

        selectFabricante.selectByVisibleText("Adidas Shoes Inc");
        sleep();

        WebElement firstSelectedOption = selectFabricante.getFirstSelectedOption();
        boolean fabricante = firstSelectedOption.isSelected();
        assertTrue(fabricante);
    }

    @Test
    @DisplayName("Comprobar que se selecciona el segundo fabricante")
    void SelectSecondBuilderTest(){
        driver.get(URL2);

        WebElement selector = driver.findElement(By.id("manufacturer"));
        Select selectFabricante = new Select(selector);

        selectFabricante.selectByIndex(1);
        sleep();

        WebElement secondSelectedOption = driver.findElement(By.xpath("//*[@id=\"manufacturer\"]/option[2]"));
        boolean fabricante = secondSelectedOption.isSelected();
        assertTrue(fabricante);
    }

    @Test
    @DisplayName("Comprobar que se selecciona una categoría disponible llamada Libros")
    void SelectFirstCategoryAvailableTest(){
        driver.get(URL2);

        WebElement selector = driver.findElement(By.id("categories"));
        Select selectCategoria = new Select(selector);

        selectCategoria.selectByVisibleText("Libros");
        sleep();

        WebElement firstSelectedOption = driver.findElement(By.xpath("//*[@id=\"categories\"]/option[1]"));
        boolean categoria = firstSelectedOption.isSelected();
        assertTrue(categoria);
    }

    @Test
    @DisplayName("Comprobar que se selecciona la segunda categoría")
    void SelectSecondCategoryAvailableTest(){
        driver.get(URL2);

        WebElement selector = driver.findElement(By.id("categories"));
        Select selectCategoria = new Select(selector);

        selectCategoria.selectByIndex(1);
        sleep();

        WebElement secondSelectedOption = driver.findElement(By.xpath("//*[@id=\"categories\"]/option[2]"));
        boolean categoria = secondSelectedOption.isSelected();
        assertTrue(categoria);

        String categoriaNombre = secondSelectedOption.getText();
        assertEquals("Computación", categoriaNombre);
    }

    @Test
    @DisplayName("Comprobar que se crea un producto")
    void addProductOK() {
        driver.get(URL2);

        driver.findElement(By.cssSelector("#name")).clear();
        driver.findElement(By.cssSelector("#name")).sendKeys("Zapatos negros");
        driver.findElement(By.cssSelector("#description")).clear();
        driver.findElement(By.cssSelector("#description")).sendKeys("Zapato de mujer negro con tacón medio");
        driver.findElement(By.cssSelector("#price")).clear();
        driver.findElement(By.cssSelector("#price")).sendKeys("25");
        driver.findElement(By.cssSelector("#quantity")).clear();
        driver.findElement(By.cssSelector("#quantity")).sendKeys("1");
        driver.findElement(By.cssSelector("#manufacturer > option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#categories > option:nth-child(4)")).click();

        WebElement button = driver.findElement(By.xpath("//*[@id=\"product\"]/div[7]/button"));
        button.submit();
        sleep();

        //Comprobaciones
        String title = driver.getTitle();
        assertEquals("Product List | Awesome App",title);

        List <WebElement> productos = driver.findElements(By.cssSelector("tr"));
        int n = productos.size();

        assertEquals("Zapatos negros", driver.findElement(By.xpath("//*[@id=\"products-list\"]/tbody/tr["+ n +"]/td[1]")).getText());
        assertEquals("Zapato de mujer negro con tacón medio", driver.findElement(By.xpath("//*[@id=\"products-list\"]/tbody/tr["+ n +"]/td[3]")).getText());
        assertEquals("25.0", driver.findElement(By.xpath("//*[@id=\"products-list\"]/tbody/tr["+ n +"]/td[2]")).getText());
        assertEquals("1", driver.findElement(By.xpath("//*[@id=\"products-list\"]/tbody/tr["+ n +"]/td[4]")).getText());
    }


    private void sleep() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
