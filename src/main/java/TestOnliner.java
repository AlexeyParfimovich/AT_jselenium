import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

public class TestOnliner {

    static WebDriver driver;
    static WebDriverWait waiter1;
    static WebDriverWait waiter2;

    public static void main(String[] args){

        Properties p = System.getProperties();
        p.setProperty("webdriver.gecko.driver","D:\\JavaProjects\\jselenium\\drivers\\geckodriver.exe");
        p.setProperty("webdriver.chrome.driver","D:\\JavaProjects\\jselenium\\drivers\\chromedriver.exe");
//        p.list(System.out);
        System.setProperties(p);

        driver = new ChromeDriver();
//        driver = new FirefoxDriver();

        // Работа с явным ожиданием появления элемента на странице
        waiter1 = new WebDriverWait(driver, 10);
        waiter2 = new WebDriverWait(driver, 5);

        // Работа с javascript
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        // Работа с Actions на странице
        Actions actions = new Actions(driver);

//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);                                                 // Задаем таймаут неявного ожидания
//        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();

        driver.get("https://catalog.onliner.by/display");                                                                   // Работа с checkbox и radiobutton

        // Пример явного ожидания появления элемента на странице
        waiter1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Мониторы']")));

        jse.executeScript("window.scrollBy(0, 800)","");

        setCheckBox("LG");
        setCheckBox("AOC");
        resetCheckBox("LG");

        selectOptions("Диагональ",2,5);
        selectOptions("Разрешение",2,3);

        jse.executeScript("window.scrollBy(0, -2000)","");

        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Новости']"))).build().perform();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Получение и сохранение снимков экрана
        // Получение снимка экрана
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Подготовка имени файла
        String fileName = new SimpleDateFormat("YYYY-MM-DD hh_mm_ss").format(new Date());
        // Запись файла
        try {
            FileUtils.copyFile(screenshot, new File(".\\screenshots\\" + fileName+ ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    public static boolean scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void selectOptions(String listname, int leftIndex, int rightIndex){
        WebElement element;
        String xPath = "//span[text()='%s']//following::select[%d]";

        scrollToElement(driver.findElement(By.xpath(String.format(xPath,listname,1))));

        driver.findElement(By.xpath(String.format(xPath,listname,1))).click();

        element = driver.findElement(By.xpath(String.format(xPath+"/option[%d]",listname,1,leftIndex)));
        waiter1.until(ExpectedConditions.visibilityOf(element));
        element.click();
//        waiter.until(ExpectedConditions.invisibilityOf(element));

        driver.findElement(By.xpath(String.format(xPath,listname,2))).click();

        element = driver.findElement(By.xpath(String.format(xPath+"/option[%d]",listname,2,rightIndex)));
        waiter1.until(ExpectedConditions.visibilityOf(element));
        element.click();
//        waiter.until(ExpectedConditions.invisibilityOf(element));
    }


    public static void setCheckBox(String name){

        String checkbox = "//span[text()='%s']";
        String input = "//input[@value='%s']";

        if (!driver.findElement(By.xpath(String.format(input,name.toLowerCase()))).isSelected()){

            //driver.findElement(By.xpath("//span[text()='LG']")).click();
            driver.findElement(By.xpath(String.format(checkbox,name))).click();
        }

    }

    public static void resetCheckBox(String name){

        String checkbox = "//span[text()='%s']";
        String input = "//input[@value='%s']";

        if (driver.findElement(By.xpath(String.format(input,name.toLowerCase()))).isSelected()){

            //driver.findElement(By.xpath("//span[text()='LG']")).click();
            driver.findElement(By.xpath(String.format(checkbox,name))).click();
        }

    }

}