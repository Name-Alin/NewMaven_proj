package HomeWork;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.BeforeTest;


public class ExtentReportsDemo {

   static ExtentReports extent;

@BeforeTest
public static ExtentReports config()
{
    String path =  System.getProperty("user.dir") + "\\reports\\index.html";
   // ExtentSparkReporter reporter = new ExtentSparkReporter();
    ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
    reporter.config().setReportName("Web Automation Results");
    reporter.config().setDocumentTitle("Test Result");

    extent = new ExtentReports();
    extent.attachReporter(reporter);
    extent.setSystemInfo("Tester", "Someone");
    return extent;
}

    /*@Test//(dataProvider = "getData")
    public void HomeWork_week4 () throws InterruptedException, IOException {

        extent.createTest("TestName1");
            *//* WebDriver driver = getDriver();
             PageDesign pageDesign = new PageDesign(driver);*//*
        PageDesign pageDesign = new PageDesign(DriverSettings.getDriver());
        //Open https://www.seleniumeasy.com/test/
        pageDesign.GoToURL("https://www.seleniumeasy.com/test/");
        pageDesign.maximize();
        //pageDesign.ClickElement(pageDesign.closeAd);
        pageDesign.ClickElement(pageDesign.expendTable2);
        pageDesign.ClickElement(pageDesign.tableSortAndSearch);

        //Sort the Table by Age and validate if it is sorted using Java Streams
        pageDesign.OrderAge();

        //And Display 50 items on the page
        pageDesign.ClickElement(pageDesign.entries);
        pageDesign.ClickElement(pageDesign.entriesValue50);
        pageDesign.CheckNumberOfDisplayedEntries();
        extent.flush();

       // close();

    }*/
}
