import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestFileLoad {

    static WebDriver driver;
    static WebDriverWait waiter;

    public static void main(String[] args) {

        Properties p = System.getProperties();
        p.setProperty("webdriver.gecko.driver","D:\\JavaProjects\\jselenium\\drivers\\geckodriver.exe");
        p.setProperty("webdriver.chrome.driver","D:\\JavaProjects\\jselenium\\drivers\\chromedriver.exe");
        System.setProperties(p);

        driver = new ChromeDriver();
//        driver = new FirefoxDriver();

        // Работа с явным ожиданием появления элемента на странице
        waiter = new WebDriverWait(driver, 10);

        // Работа с javascript
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);                                                 // Задаем таймаут неявного ожидания
//        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();

        driver.get("https://www.google.com/");

        driver.findElement(By.xpath("//a[text()='Картинки']")).click();
        driver.findElement(By.xpath("//div[@aria-label='Поиск по картинке']")).click();

        // Ожидаем появления окна для загрузки картинки
        waiter.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='qbp']"))));

        WebElement element = driver.findElement(By.xpath("//span[text()='Загрузить файл']/parent::div"));

        System.out.println(element.isDisplayed());
        if(element.isDisplayed()){
            element.click();
        }

//        driver.findElement(By.xpath("//span[text()='Загрузить файл']/parent::div")).click();

        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("D:\\User\\Pictures\\Тенерифе 01-2018\\Леша\\IMG_20180106_091822.jpg");

//        driver.quit();
    }
}
