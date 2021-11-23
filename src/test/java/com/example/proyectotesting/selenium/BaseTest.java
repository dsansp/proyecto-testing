package com.example.proyectotesting.selenium;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;

public class BaseTest {
    //Driver del navegador
    WebDriver driver;
    JavascriptExecutor js;




    @BeforeEach
    void setUp() {
        System.getenv().forEach((key, value) -> System.out.println(key + " " + value));
        System.getProperties().forEach((key, value) -> System.out.println(key + " " + value));

        if(System.getProperties().get("os.name").equals("Linux")){
            System.out.println("Configurando Navegador Chrome Headless para CI");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            js = (JavascriptExecutor) driver;
        }else{
            System.out.println("Configurando Navegador Chrome desde carpeta drivers para testing en desarrollo");
            String dir = System.getProperty("user.dir"); // ruta del proyecto
            String driverUrl = "/driver/chromedriver.exe";
            String url = dir + driverUrl;
            System.setProperty("webdriver.chrome.driver", url);
            driver = new ChromeDriver(); // Google Chrome
            js = (JavascriptExecutor) driver;
        }
    }

    @AfterEach
    void tearDown() {
        driver.quit();

    }



}
