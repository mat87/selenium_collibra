package com.selenium.automation.tests;

import com.selenium.automation.page.BasePage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TestDemo {

    public static WebDriver driver;
    public static BasePage basePage;
    ArrayList<ArrayList<String>> columnsToCompare = new ArrayList<>();
    ArrayList<String> firstColumn = new ArrayList<>();
    ArrayList<String> secondColumn = new ArrayList<>();
    ArrayList<String> thirdColumn = new ArrayList<>();
    ArrayList<String> fourthColumn = new ArrayList<>();

    public TestDemo(){
        firstColumn.add("5, 6, 7, 8, 9, 2, 3, 4, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 1, 0");
        secondColumn.add("Sunt proident ea proident proident qui qui elit sunt ullamco in., Ullamco ad eiusmod voluptate cillum exercitation elit in excepteur mollit dolor laborum., Lorem id id ad sunt eu ut veniam consectetur culpa., Esse ea reprehenderit deserunt adipisicing minim cupidatat voluptate Lorem culpa incididunt dolore culpa nulla., Tempor deserunt enim Lorem minim in consectetur velit proident in enim officia Lorem., Fugiat mollit irure sit ullamco est est culpa., Ut sint ullamco ad ea veniam aute irure nisi ipsum aute in adipisicing sit., Et in qui dolore eu nostrud culpa do qui., Eu amet culpa velit tempor est voluptate., Consectetur laborum non reprehenderit magna excepteur quis reprehenderit irure nisi et officia., Minim ex deserunt proident cillum eiusmod excepteur anim in amet., Aute proident laborum aute laboris occaecat est., Ad exercitation velit duis sit mollit consequat laboris labore dolor ipsum culpa magna cillum fugiat., Irure voluptate aliquip fugiat consectetur consectetur nulla adipisicing id proident consectetur minim culpa., Mollit aliqua elit elit Lorem Lorem amet fugiat excepteur nulla., Reprehenderit duis cillum ipsum officia reprehenderit voluptate et in cillum ea Lorem., Aliqua ullamco quis tempor qui., Elit occaecat anim dolore nisi exercitation qui., Duis do sunt ut exercitation laborum ea., Eu veniam eiusmod labore adipisicing voluptate id ipsum minim dolore quis non., Dolore laborum est reprehenderit qui adipisicing non ipsum est reprehenderit excepteur., Laboris nisi ullamco adipisicing pariatur., Occaecat ad ipsum laborum enim velit nostrud excepteur velit nisi velit., Ipsum eu amet duis velit., Aliqua mollit esse tempor excepteur qui qui in laborum., Deserunt officia cupidatat adipisicing non ea do veniam enim voluptate in ut nulla labore.");
        thirdColumn.add("Apr 25, 2015, Dec 25, 2015, Sep 25, 2015, Jul 23, 2015, May 22, 2015, Oct 20, 2015, Sep 23, 2014, Nov 19, 2015, Feb 28, 2015, Mar 11, 2016, Oct 10, 2015, Feb 18, 2014, Sep 22, 2015, Mar 8, 2014, Sep 21, 2014, Dec 10, 2014, Oct 26, 2014, Apr 4, 2015, Jul 25, 2015, Sep 22, 2014, Aug 12, 2014, Mar 20, 2016, Dec 30, 2015, Nov 1, 2015, Sep 30, 2014");
        fourthColumn.add("excepteur, tempor, cupidatat, ex, occaecat, cillum, esse, est, ullamco, pariatur, labore, id, adipisicing, aute, nostrud, eu, nostrud, id, dolore, proident, exercitation, est, dolore, culpa, do, incididunt");
        columnsToCompare.add(firstColumn);
        columnsToCompare.add(secondColumn);
        columnsToCompare.add(thirdColumn);
        columnsToCompare.add(fourthColumn);
    }

    /*
    * Konieczna była konwersja ArryList na Stringa
    * Problem był taki, że przy asercji bez konwersji leciał wyjątek pomimo, że dwie listy były identyczne.
    * Nie wiem jakim cudem, ale sklejana kolumna do ArrayList zawierała o jeden znak więcej od porównawczej.
    * */
    public String convertToString(String column){
        String myColumn = basePage.getColumnContent(column).toString();
        return myColumn;
    }

    public static void selectBrowser(String browser){
        switch (browser){
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "IE":
                System.setProperty("webdriver.ie.driver","D:\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
                //Nie miałem Safari :)
        }
    }

    @BeforeClass
    public static void setUp(){
        selectBrowser("Firefox");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /*
    * "Signifie - link/hierarchy"
    * "Description"
    * "Created On"
    * "Multi Selection"
    * */
    //@Test()
    public void test_column_content() {
        driver.get("localhost:8000");
        basePage = new BasePage(driver);
        Assert.assertEquals(convertToString("Multi Selection"), columnsToCompare.get(3).toString());
    }

    /*
    * Columns to choose:
    * "First Name"
    * "Last Name"
    * "Company"
    * "Sentence"
    * */
    //@Test()
    public void test_column_resize(){
        driver.get("https://facebook.github.io/fixed-data-table/example-resize.html");
        basePage = new BasePage(driver);
        basePage.resizeColumn("Sentence", 50);
    }

    @Test()
    public void test_print_whole_table(){
        driver.get("localhost:8000");
        basePage = new BasePage(driver);
        basePage.getWholeTable(2, 2);
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
