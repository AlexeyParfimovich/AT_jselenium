import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestASKD {

    static WebDriver driver;

    public static void main(String[] args){

        Properties p = System.getProperties();
        p.setProperty("webdriver.gecko.driver","D:\\Java projects\\jselenium\\drivers\\geckodriver.exe");
        p.setProperty("webdriver.chrome.driver","D:\\Java projects\\jselenium\\drivers\\chromedriver.exe");
//        p.list(System.out);
        System.setProperties(p);

        driver = new ChromeDriver();
//        WebDriver driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);                                                 // Задаем таймаут неявного ожидания
//        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();

//        driver.get("https://askd-pak.mos.ru");                                                                              // Переходим на главную страницу приложения АСКД         // Должна открыться форма авторизации
//        driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");


//        System.out.println(driver.getTitle());
//        System.out.println(driver.getCurrentUrl());


//        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("MCI_User");                        // Выполняем ввод логина и пароля в соответствующие тектовые поля
//        System.out.println(driver.findElement(By.xpath("//input[@id='username']")).getAttribute("value"));               // Вывод введенного значения

//        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("BF7FZeyO6K");
//        System.out.println(driver.findElement(By.xpath("//input[@id='password']")).getAttribute("value"));

//        driver.findElement(By.xpath("//input[@id='password']")).clear();                                                  // при необходимости можно очистить тектовое поле

//        driver.findElement(By.xpath("//button[@class='login-btn']")).submit();                                              // Нажимаем на кнопку "Войти"

//        WebElement link = driver.findElement(By.xpath("//a[contains(@href,'/monitoring')]"));                               // Нажимаем на кнопку "Мониторинг"
//        System.out.println(link.getText());
//        link.click();



        driver.get("https://catalog.onliner.by/display");                                                                            // Работа с checkbox и radiobutton

        //System.out.println(driver.findElement(By.xpath("//input[@value='lg']")).isSelected());
        //driver.findElement(By.xpath("//span[text()='LG']")).click();

        setCheckBox("LG");
        setCheckBox("AOC");
        setCheckBox("Dell");

        resetCheckBox("LG");

//        driver.quit();
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
