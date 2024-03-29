import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestTables {

    static WebDriver driver;

    public static void main(String[] args) {

        Properties p = System.getProperties();
        p.setProperty("webdriver.gecko.driver","D:\\JavaProjects\\jselenium\\drivers\\geckodriver.exe");
        p.setProperty("webdriver.chrome.driver","D:\\JavaProjects\\jselenium\\drivers\\chromedriver.exe");
//        p.list(System.out);
        System.setProperties(p);

        driver = new ChromeDriver();
//        driver = new FirefoxDriver();

//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);                                                 // Задаем таймаут неявного ожидания
//        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();


        driver.get("https://www.w3schools.com/html/html_tables.asp");

        // Пример явного ожидания появления элемента на странице
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[text()='HTML Table Example']")));

        WebElement tableElement = driver.findElement(By.xpath("//table[@id='customers']"));

        Table table = new Table(tableElement, driver);

        System.out.println("Rows number is " + table.getRows().size());
        System.out.println(table.getValueFromCell(3,3));
        System.out.println(table.getValueFromCell(2,"Company"));

    }
}
