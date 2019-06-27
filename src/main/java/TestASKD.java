import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestASKD {
    public static void main(String[] args){

        Properties p = System.getProperties();
        p.setProperty("webdriver.gecko.driver","C:\\Users\\aparfimovich\\Documents\\Java projects\\jselenium\\drivers\\geckodriver.exe");
        p.setProperty("webdriver.chrome.driver","C:\\Users\\aparfimovich\\Documents\\Java projects\\jselenium\\drivers\\chromedriver.exe");
//        p.list(System.out);
        System.setProperties(p);

        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);                                                 // Задаем таймаут неявного ожидания
//        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();

        driver.get("https://askd-pak.mos.ru");                                                                              // Переходим на главную страницу приложения АСКД         // Должна открыться форма авторизации
//        driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");


        System.out.println(driver.getTitle());
//        System.out.println(driver.getCurrentUrl());


        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("login");                        // Выполняем ввод логина и пароля в соответствующие тектовые поля
//        System.out.println(driver.findElement(By.xpath("//input[@id='username']")).getAttribute("value"));               // Вывод введенного значения

        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password");
//        System.out.println(driver.findElement(By.xpath("//input[@id='password']")).getAttribute("value"));

//        driver.findElement(By.xpath("//input[@id='password']")).clear();                                                  // при необходимости можно очистить тектовое поле

        driver.findElement(By.xpath("//button[@class='login-btn']")).submit();                                              // Нажимаем на кнопку "Войти"



//        driver.quit();
    }
}
