package HomeWork;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Week4_Alin extends DriverSettings {



//@BeforeTest
@BeforeMethod
public void init() throws IOException {
   // WebDriver driver = getDriver();

   // DriverSettings.getDriver();
   // PageDesign pageDesign = new PageDesign(driver);
    DriverSettings.SetDriver();
}

    @Test//(dataProvider = "getData")
    public void HomeWork_week4 () throws InterruptedException, IOException {

             WebDriver driver = getDriver();
             PageDesign pageDesign = new PageDesign(driver);
           // PageDesign pageDesign = new PageDesign(DriverSettings.getDriver());
            //Open https://www.seleniumeasy.com/test/
            pageDesign.GoToURL(SetUrl());
            pageDesign.maximize();
            //pageDesign.ClickElement(pageDesign.closeAd);
            pageDesign.ClickElement(pageDesign.getExpendTable2());
            pageDesign.ClickElement(pageDesign.getTableSortAndSearch());

            //Sort the Table by Age and validate if it is sorted using Java Streams
            pageDesign.OrderAge();

            //And Display 50 items on the page
           pageDesign.ClickElement(pageDesign.entries);
           pageDesign.ClickElement(pageDesign.entriesValue50);
           pageDesign.CheckNumberOfDisplayedEntries();

           close();

    }

    @Test
    public void tryFailTest() throws IOException {
        WebDriver driver = getDriver();
        PageDesign pageDesign = new PageDesign(driver);
        //Open https://www.seleniumeasy.com/test/
        pageDesign.GoToURL(SetUrl());

        pageDesign.maximize();
        pageDesign.ClickElement(pageDesign.closeAd);
        Assert.assertEquals(driver.getCurrentUrl(),"http://Something.com");

        close();
    }

   /* @AfterTest
    public void TearDown()
    {
        close();
    }
*/
   /* @DataProvider
    public Object[][] getData()
    {
        //Row stands for how many different data types test should run
        //Column stands for how many values per each test
        Object[][] data = new Object[1][1];

        data[0][0] = "https://www.seleniumeasy.com/test/";
        return data;
    }*/
}
