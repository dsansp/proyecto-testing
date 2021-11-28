package com.example.lambdaTest;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
public class IoanLambdaTest extends BaseTestLambda {


    @Test
    public void testSimple() throws Exception {

        driver.get("https://proyectogrupo1testing.herokuapp.com/products");
        assertEquals("Products Directory", driver.findElement(By.tagName("h1")).getText());
        status = "passed";

    }

    //private static final String URL1 = "http://localhost:8082/products";
    private static final String URL0 = "https://proyectogrupo1testing.herokuapp.com/products/new";
    private static  final String URL1 = "https://proyectogrupo1testing.herokuapp.com/products";
    private static final String URL2 = "https://proyectogrupo1testing.herokuapp.com/products/9/view";

    @DisplayName("Creando un producto para luego poder comprobar que se ve")
    @Test
    void buttonVerProductTest(){

        driver.get(URL1);
        driver.getCurrentUrl();

        driver.findElement(By.xpath("//div/p[2]/a[1]")).click();

        driver.get(URL0);

        driver.getCurrentUrl();

        //Creando el producto de PS3
        driver.findElement(By.xpath("//div[@class='form-group']//input[@id='name']")).clear();
        driver.findElement(By.xpath("//div[@class='form-group']//input[@id='name']")).sendKeys("PS3");
        driver.findElement(By.xpath("//div[@class='form-group']//textarea")).clear();
        driver.findElement(By.xpath("//div[@class='form-group']//textarea")).sendKeys("PS3 la mejor consola del año");
        driver.findElement(By.xpath("//div[@class='form-group']//input[@id='price']")).clear();
        driver.findElement(By.xpath("//div[@class='form-group']//input[@id='price']")).sendKeys("260");
        driver.findElement(By.xpath("//div[@class='form-group']//input[@id='quantity']")).clear();
        driver.findElement(By.xpath("//div[@class='form-group']//input[@id='quantity']")).sendKeys("1");


        WebElement button = driver.findElement(By.xpath("//*[@id=\"product\"]/div[7]/button"));
        button.submit();

        List<WebElement> productos = driver.findElements(By.tagName("tr"));
        int numero = productos.size();

        //Comprobaciones
        String title = driver.getTitle();
        assertEquals("Product List | Awesome App",title);


        driver.findElement(By.xpath("//*[@id=\"products-list\"]/tbody/tr["+ numero +"]/td[7]/a[1]")).click();
        String titleProduct = driver.getTitle();

        assertEquals("Nombre: PS3", driver.findElement(By.xpath("//div/p[1]")).getText());
        assertEquals("Descripción: PS3 la mejor consola del año", driver.findElement(By.xpath("//div/p[2]")).getText());

        driver.findElement(By.xpath("//div/a[1]")).click();

        assertEquals("Products Directory", driver.findElement(By.tagName("h1")).getText());
        status = "passed";
    }

    @DisplayName("Borrando el producto que hemos creado anteriormente")
    @Test
    void buttonDeleteProductTest(){
        driver.get(URL1);
        driver.getCurrentUrl();

        driver.findElement(By.xpath("//div/p[2]/a[1]")).click();
        driver.get(URL0);

        driver.getCurrentUrl();

        //Creando el producto de Wii para borrarlo
        driver.findElement(By.xpath("//div[@class='form-group']//input[@id='name']")).clear();
        driver.findElement(By.xpath("//div[@class='form-group']//input[@id='name']")).sendKeys("Wii");
        driver.findElement(By.xpath("//div[@class='form-group']//textarea")).clear();
        driver.findElement(By.xpath("//div[@class='form-group']//textarea")).sendKeys("Wii 2000 la mejor consola del año");
        driver.findElement(By.xpath("//div[@class='form-group']//input[@id='price']")).clear();
        driver.findElement(By.xpath("//div[@class='form-group']//input[@id='price']")).sendKeys("300");
        driver.findElement(By.xpath("//div[@class='form-group']//input[@id='quantity']")).clear();
        driver.findElement(By.xpath("//div[@class='form-group']//input[@id='quantity']")).sendKeys("1");

        WebElement button = driver.findElement(By.xpath("//*[@id=\"product\"]/div[7]/button"));
        button.submit();

        List <WebElement> productos = driver.findElements(By.tagName("tr"));
        int numero = productos.size();

        driver.findElement(By.xpath("//*[@id=\"products-list\"]/tbody/tr["+ numero +"]/td[7]/a[1]")).click();
        String titleProduct = driver.getTitle();

        assertEquals("Nombre: Wii", driver.findElement(By.xpath("//div/p[1]")).getText());
        assertEquals("Descripción: Wii 2000 la mejor consola del año", driver.findElement(By.xpath("//div/p[2]")).getText());

        //Volver a la lista de productos
        driver.findElement(By.xpath("//div/a[3]")).click();
        status = "passed";
    }






}
