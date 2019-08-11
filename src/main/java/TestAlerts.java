import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestAlerts {
    static WebDriver driver;

    public static void main(String[] args) {
        Properties p = System.getProperties();
        p.setProperty("webdriver.gecko.driver","D:\\JavaProjects\\jselenium\\drivers\\geckodriver.exe");
        p.setProperty("webdriver.chrome.driver","D:\\JavaProjects\\jselenium\\drivers\\chromedriver.exe");
        System.setProperties(p);

        driver = new ChromeDriver();
//        driver = new FirefoxDriver();

        // Работа с javascript
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);                                                 // Задаем таймаут неявного ожидания
//        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();

        driver.get("https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html");
        String window1 = driver.getWindowHandle();

        jse.executeScript("confirm('Are you Ok?')");

        try {
            Thread.sleep(5000);
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }

        driver.switchTo().alert().accept();

        driver.findElement(By.xpath("//a[text()='Oracle Technology Network License Agreement for Oracle Java SE']")).click();

        driver.get("http://www.google.com");
        driver.get("http://www.yandex.ru");
        driver.get("http://www.tut.by");

        // Перебираем все откытые вкладки браузера и переключаемся в них по очереди
        for(String window: driver.getWindowHandles()){
            driver.switchTo().window(window);
        }



    }
}
