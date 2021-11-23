package selenium;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class ProductListTest extends BaseTest {


    //private static final String URL = "https://proyectogrupo1testing.herokuapp.com/products";
    private static final String URL1 = "http://localhost:8082/products";
    private static final String URL2 = "http://localhost:8082/products/9/view";

    @DisplayName("Comprobamos que se carga correctamente la pagina web")
    @Test
    void cargarPaginaTest(){
        driver.get(URL1);
        WebElement buttonVer = driver.findElement(By.xpath("//*[@id=\"products-list\"]/tbody/tr[2]/td[7]/a[1]"));
        buttonVer.click();
        assertEquals("Producto 9", driver.findElement(By.tagName("h2")).getText());

    }

    @Test
    void buttonVerTest(){
        driver.get(URL2);
        assertEquals("Nombre", driver.findElement(By.xpath("//div/p[1]/b")).getText());
       // assertEquals(": Balón", driver.findElement(By.xpath("/html/body/div/div/div/p[1]/text()")).getText());

        assertEquals("Descripción", driver.findElement(By.xpath("//div/div/div/p[2]/b")).getText());

        assertEquals("Precio",driver.findElement(By.xpath("//*/div/div/p[3]/b")).getText());

        assertEquals("Cantidad", driver.findElement(By.xpath("//*/div/div/p[4]/b")).getText());

//        driver.get("http://localhost:8082/manufacturers/1/view");
//        assertEquals("Fabricante 1", driver.findElement(By.tagName("h2")).getText());
//
//        WebElement buttonVolver = driver.findElement(By.xpath("//div/div/a[1]"));
//        buttonVolver.click();

        assertEquals("Fabricante", driver.findElement(By.xpath("//*/div/div/p[5]/b")).getText());
        assertEquals("Categorías asociadas", driver.findElement(By.tagName("h3")).getText());

        assertEquals("Libros",driver.findElement(By.xpath("//div/ul/li[1]")).getText());
        assertEquals("Computación", driver.findElement(By.xpath("//div/ul/li[2]/span")).getText());

    }

    @Test
    void VolverAProductoListTest() {
        driver.get(URL2);
        driver.findElement(By.className("btn-info")).click();
        assertEquals("Products Directory", driver.findElement(By.tagName("h1")).getText());
    }


}
