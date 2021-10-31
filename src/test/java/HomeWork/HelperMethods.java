package HomeWork;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class HelperMethods{

    public String TakeScreenshot(String testCaseName, WebDriver driver) throws IOException {
        WebDriver obj = driver;
        File src = ((TakesScreenshot)obj).getScreenshotAs(OutputType.FILE);
        String destinationFolder = System.getProperty("user.dir") + "\\reports\\" + testCaseName+".png";
        FileUtils.copyFile(src, new File(destinationFolder));
        //FileUtils.copyFile(src, new File("C:\\Users\\adrian.ciortea.WS-CJ-U3-3358\\Desktop\\screenshot.png"));
        return destinationFolder;
    }


}
