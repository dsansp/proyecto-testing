package selenium;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.support.ui.Select;

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
        inputName.sendKeys("INDITEX");
        sleep();

        inputName = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        String inputValue = inputName.getAttribute("value");
        assertEquals("INDITEX", inputValue);
    }

    @Test
    @DisplayName("Comprobar que se escribe la descripción en el TextBox")
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
        inputCantidad.sendKeys("25");
        sleep();

        inputCantidad = driver.findElement(By.xpath("//*[@id=\"quantity\"]"));
        String inputValue = inputCantidad.getAttribute("value");
        assertEquals("25", inputValue);
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
    @DisplayName("Comprobar que se selecciona una categoría disponible llamada Libros")
    void SelectSCategoryAvailableTest(){
        driver.get(URL2);

        WebElement selector = driver.findElement(By.id("categories"));
        Select selectCategoria = new Select(selector);

        selectCategoria.selectByVisibleText("Libros");
        sleep();

        WebElement firstSelectedOption = selectCategoria.getFirstSelectedOption();
        boolean categoria = firstSelectedOption.isSelected();
        assertTrue(categoria);
    }


    private void sleep() {
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
