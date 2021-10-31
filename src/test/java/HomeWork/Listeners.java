package HomeWork;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends HelperMethods implements ITestListener {

    ExtentReports extent = ExtentReportsDemo.config();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {

     test = extent.createTest(result.getMethod().getMethodName());
     extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

     extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

     WebDriver driver = null;
     extentTest.get().fail(result.getThrowable());
     try {
      driver = DriverSettings.getDriver();
     } catch (IOException e) {
      e.printStackTrace();
     }
          // driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());

        String testMethodName = result.getMethod().getMethodName();
        try {
         extentTest.get().addScreenCaptureFromPath(TakeScreenshot(testMethodName, driver), result.getMethod().getMethodName());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

     @Override
     public void onFinish(ITestContext context) {
      extent.flush();
    }
}
