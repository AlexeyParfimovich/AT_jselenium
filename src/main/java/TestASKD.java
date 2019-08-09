import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Locatable;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestASKD {

    static WebDriver driver;
    private static final String login = "MP_User";
    private static final String password = "uWHj3koZeK";

    public static void main(String[] args){

        Properties p = System.getProperties();
        p.setProperty("webdriver.gecko.driver","D:\\Java projects\\jselenium\\drivers\\geckodriver.exe");
        p.setProperty("webdriver.chrome.driver","D:\\Java projects\\jselenium\\drivers\\chromedriver.exe");
//        p.list(System.out);
        System.setProperties(p);

        //driver = new ChromeDriver();
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);                                                 // Задаем таймаут неявного ожидания
//        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();

        driver.get("https://askd-pak.mos.ru");                                                                              // Переходим на главную страницу приложения АСКД         // Должна открыться форма авторизации
//        driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");

//        System.out.println(driver.getTitle());
//        System.out.println(driver.getCurrentUrl());

        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(login);                        // Выполняем ввод логина и пароля в соответствующие тектовые поля
//        System.out.println(driver.findElement(By.xpath("//input[@id='username']")).getAttribute("value"));               // Вывод введенного значения

        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
//        System.out.println(driver.findElement(By.xpath("//input[@id='password']")).getAttribute("value"));

//        driver.findElement(By.xpath("//input[@id='password']")).clear();                                                  // при необходимости можно очистить тектовое поле

        driver.findElement(By.xpath("//button[@class='login-btn']")).submit();                                              // Нажимаем на кнопку "Войти"

        driver.findElement(By.xpath("//a[contains(@href,'/monitoring')]")).click();                               // Нажимаем на кнопку "Мониторинг"



//        setCheckBox("LG");
//        setCheckBox("AOC");
//
//        resetCheckBox("LG");
//
//        WebElement element;
//        element = driver.findElement(By.xpath("//span[text()='Диагональ']//following::div/select[1]"));          // Работа с ListBox (Select)
//        scrollToElement(element);                                                                                            // Прогрутка до элемента
//        element.click();
//        driver.findElement(By.xpath("//span[text()='Диагональ']/following::div/select[1]/option[2]")).click();

//        selectOptions("Диагональ",1,3);

//        element = driver.findElement(By.xpath("//span[text()='Разрешение']//following::div/select[1]"));          // Работа с ListBox (Select)
//        scrollToElement(element);                                                                                            // Прогрутка до элемента
//        element.click();
//        driver.findElement(By.xpath("//span[text()='Разрешение']/following::div/select[1]/option[2]")).click();

//        selectOptions("Разрешение",1,3);

                                                                                                                                // Получение группы элементов
//        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='schema-filter']/div[2]/div[3]//span[@class='i-checkbox']"));
//        elements.get(3).click();

        // Работа с таблицей
        driver.get("https://askd-pak.mos.ru");                                                                              // Переходим на главную страницу приложения АСКД         // Должна открыться форма авторизации


//        driver.quit();
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
        String xPath = "//span[text()='%s']//following::div/select[%d]";

        scrollToElement(driver.findElement(By.xpath(String.format(xPath,listname,1))));

        driver.findElement(By.xpath(String.format(xPath,listname,1))).click();
        driver.findElement(By.xpath(String.format(xPath+"/option[%d]",listname,1,leftIndex))).click();

        driver.findElement(By.xpath(String.format(xPath,listname,2))).click();
        driver.findElement(By.xpath(String.format(xPath+"/option[%d]",listname,1,leftIndex))).click();
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
