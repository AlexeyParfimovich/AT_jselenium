import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainClass {
    public static void main(String[] args){


        Properties p = System.getProperties();
        p.setProperty("webdriver.gecko.driver","C:\\Users\\aparfimovich\\Documents\\Java projects\\jselenium\\drivers\\geckodriver.exe");
        p.setProperty("webdriver.chrome.driver","C:\\Users\\aparfimovich\\Documents\\Java projects\\jselenium\\drivers\\chromedriver.exe");
//        p.list(System.out);
        System.setProperties(p);

//        FirefoxDriver driver = new FirefoxDriver();
//        WebDriver driver_cr = new ChromeDriver();
        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);                                                 // Задаем таймаут неявного ожидания
//        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();

        driver.get("http://wikipedia.org");

        driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");

//        driver.navigate().back();
//        driver.navigate().forward();
//        driver.navigate().refresh();

        System.out.println(driver.getTitle());
//        System.out.println(driver.getCurrentUrl());

        System.out.println("Выполняем поиск элементов");

//        WebElement lnk1 = driver.findElement(By.linkText("Log in"));
//        WebElement lnk2 = driver.findElement(By.partialLinkText("Donate"));
//        WebElement searchFld = driver.findElement(By.name("search"));
//        WebElement searchBtn = driver.findElement(By.className("searchButton"));
//        WebElement logo = driver.findElement(By.xpath("//input[@id='searchButton']"));

        driver.findElement(By.xpath("//input[@id='searchButton']")).click();                                                // Работа с кнопками


        driver.navigate().to("https://github.com/");

        WebElement btn = driver.findElement(By.xpath("//form[@class='home-hero-signup text-gray-dark js-signup-form']//button"));

        if (btn.getText().equals("Sign up for GitHub")) {
            System.out.println("Button text is " + btn.getText());
        }
//        btn.submit();

        driver.findElement(By.xpath("//a[@class='HeaderMenu-link no-underline mr-3']")).click();


        System.out.println("Элементы найдены");



//        driver.quit();
    }
}
