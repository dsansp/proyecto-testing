package selenium;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ProductListTest extends BaseTest {


    private static final String URL = "https://proyectogrupo1testing.herokuapp.com/products";
    @Disabled
    @Test
    void cargarPaginaTest(){
        driver.get(URL);
        driver.findElement(By.xpath("//*[@id=\"products-list\"]/tbody/tr[2]/td[7]/a[1]")).click();


    }


}
