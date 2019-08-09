import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Table {

    private WebDriver driver;
    // Ссылка на табицу
    private WebElement tableElement;

    public Table(WebElement tableElement, WebDriver driver){
        this.tableElement = tableElement;
        this.driver = driver;
    }

    public List<WebElement> getRows(){
        // Получение всех строк таблицы
        List<WebElement> rows = tableElement.findElements(By.xpath(".//tr"));
        // Удаление строки с заголовками (если есть)
        rows.remove(0);

        return rows;
    }

    public List<WebElement> getHeaders(){
        // Получение первой строки таблицы, содержащей заголовки
        WebElement headsRow = tableElement.findElement(By.xpath(".//tr[1]"));

        // Получение заголовков столбцов (th - заголовки таблицы)
        List<WebElement> headsCols = headsRow.findElements(By.xpath(".//th"));

        return headsCols;
    }

    public List<List<WebElement>> getRowsWithCols(){
        // Получение всех строк таблицы
        List<WebElement> rows = getRows();

        List<List<WebElement>> rowsWithCols = new ArrayList<List<WebElement>>();

        // Заполнение списка ячеек
        for (WebElement row: rows){
            List<WebElement> rowWithCols = row.findElements(By.xpath(".//td"));

            rowsWithCols.add(rowWithCols);
        }

        return rowsWithCols;
    }

    // Получение значений строк, разнесенных по заголовкам
    public List<Map<String,WebElement>> getRowsByHeads(){

        Map<String, WebElement> rowByHeads;
        // Список строк разнесенных по заголовкам
        List<Map<String,WebElement>> rowsByHeads = new ArrayList<Map<String, WebElement>>();

        // Список ячеек
        List<List<WebElement>> rowsWithCols = getRowsWithCols();
        // Список заголовков
        List<WebElement> headsCols = getHeaders();

        // Перебираем все строки
        for(List<WebElement> row : rowsWithCols){
            rowByHeads = new HashMap<String, WebElement>();

            // Перебираем все заголовки
            for(int i=0; i < headsCols.size(); i++){
                // Берем заголовок
                String heading = headsCols.get(i).getText();
                // Берерм ячейку
                WebElement cell = row.get(i);
                // Добавляем в Map
                rowByHeads.put(heading,cell);
            }
            rowsByHeads.add(rowByHeads);
        }

        return rowsByHeads;
    }


    // Получение текста в ячейке по номеру строки и столбца
    public String getValueFromCell(int rowNumber, int colNumber){

        List<List<WebElement>> rowsWithCols = getRowsWithCols();
        // Получаем ячейку - индексы ничинаются с нуля!
        WebElement cell = rowsWithCols.get(rowNumber-1).get(colNumber-1);
        return cell.getText();
    }

    // Получение текста в ячейке по номеру строки и столбца
    public String getValueFromCell(int rowNumber, String colName){

        List<Map<String,WebElement>> rowsByHeads = getRowsByHeads();
        // Возвращаем текст ячейки - индексы ничинаются с нуля!
        return rowsByHeads.get(rowNumber-1).get(colName).getText();
    }



}
