package com.selenium.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;




public class BasePage {

    WebDriver driver;

    @FindBy(xpath = "/html/body/div/div[2]/div/div[2]/div[2]/div[1]/div[2]/div/div/div[1]/div/div/div[1]")
    WebElement firstColumn;

    @FindBy(xpath = "/html/body/div/div[2]/div/div[2]/div[2]/div[1]/div[2]/div/div/div[2]/div/div[1]/div[1]")
    WebElement secondColumn;

    @FindBy(xpath = "/html/body/div/div[2]/div/div[2]/div[2]/div[1]/div[2]/div/div/div[2]/div/div[2]/div[1]")
    WebElement thirdColumn;

    @FindBy(xpath = "/html/body/div/div[2]/div/div[2]/div[2]/div[1]/div[2]/div/div/div[2]/div/div[3]/div[1]")
    WebElement fourthColumn;


    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void getWholeTable(int myRow, int myColumn){
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='85483B148E3F8ED2']/div/div/div[1]/div[2]/div/div[1]/div[3]/div"));
        int rowsCount = rows.size();
        for (int row = 0; row < rowsCount; row++) {
            List<WebElement> columnsRow = rows.get(row).findElements(By.className("uf-table-cell-content"));
            int columns_count = columnsRow.size();
            //System.out.println("Cells in row " + row + " are " + columns_count);
            for (int column = 0; column < columns_count; column++) {
                String celText = columnsRow.get(column).getText();
                //System.out.println("Row " + row + " | Column " + column + " | Cell content = " + celText);
                if(row == myRow && column == myColumn){
                    System.out.println(celText);
                }
            }
        }
    }

    public List<String> getColumnContent(String colName){

        List<String> myCol = new ArrayList();
        List<WebElement> col;
        int colSize;
        List<WebElement> row = driver.findElements(By.xpath("//*[@id='85483B148E3F8ED2']/div/div/div[1]/div[2]/div/div[1]/div[3]/div"));
        int rowsCount = row.size();


        for(int i = 0; i < rowsCount; i++){
            switch(colName){
                case "Signifie - link/hierarchy":
                    col = row.get(i).findElements(By.xpath("//*[@id='85483B148E3F8ED2']/div/div/div[1]/div[2]/div/div[1]/div[3]/div["+i+"]/div/div/div[2]/div/div[2]"));
                    colSize = col.size();
                    for(int j = 0; j < colSize; j++){
                        String column = col.get(j).getText();
                        myCol.add(column);
                    }
                    //Usuwa puste elementy z listy ponieważ tabela testowa zawiera puste komórki.
                    myCol.removeAll(Collections.singleton(""));
                    break;
                case "Description":
                    col = row.get(i).findElements(By.xpath("//*[@id='85483B148E3F8ED2']/div/div/div[1]/div[2]/div/div[1]/div[3]/div["+i+"]/div/div/div[2]/div/div[3]"));
                    colSize = col.size();
                    for(int j = 0; j < colSize; j++){
                        String column = col.get(j).getText();
                        myCol.add(column);
                    }
                    myCol.removeAll(Collections.singleton(""));
                    break;
                case "Created On":
                    col = row.get(i).findElements(By.xpath("//*[@id='85483B148E3F8ED2']/div/div/div[1]/div[2]/div/div[1]/div[3]/div["+i+"]/div/div/div[2]/div/div[5]"));
                    colSize = col.size();
                    for(int j = 0; j < colSize; j++){
                        String column = col.get(j).getText();
                        myCol.add(column);
                    }
                    myCol.removeAll(Collections.singleton(""));
                    break;
                case "Multi Selection":
                    col = row.get(i).findElements(By.xpath("//*[@id='85483B148E3F8ED2']/div/div/div[1]/div[2]/div/div[1]/div[3]/div["+i+"]/div/div/div[2]/div/div[6]"));
                    colSize = col.size();
                    for(int j = 0; j < colSize; j++){
                        String column = col.get(j).getText();
                        myCol.add(column);
                    }
                    myCol.removeAll(Collections.singleton(""));
                    break;
            }
        }

        return myCol;
    }


    public void resizeColumn(String columnName, int offset){

        WebElement column;
        Actions action = new Actions(driver);

        switch (columnName){
            case "First Name":
                column = firstColumn;
                action.dragAndDropBy(column, offset, 0).build().perform();
                break;
            case "Last Name":
                column = secondColumn;
                action.dragAndDropBy(column, offset, 0).build().perform();
                break;
            case "Company":
                column = thirdColumn;
                action.dragAndDropBy(column, offset, 0).build().perform();
                break;
            case "Sentence":
                column = fourthColumn;
                action.dragAndDropBy(column, offset, 0).build().perform();
                break;
            default:
                System.out.println("Column not found");
        }

    }



}