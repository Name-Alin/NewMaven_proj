package HomeWork;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DriverSettings {

    private static WebDriver obj;
    private static Properties prop;

    public static void SetDriver () throws IOException {

        prop = new Properties();
        FileInputStream dataFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties");
        prop.load(dataFile);
       // String browserName = System.getProperty("browser");
        String browserName = prop.getProperty("browser");


        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");

            obj = new ChromeDriver(options);
            obj.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        else if (browserName.equalsIgnoreCase("firefox"))
        {
            obj = new FirefoxDriver();
            obj.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }

    }


    public static WebDriver getDriver() throws IOException {
       // SetDriver();
        return obj;
    }


    public static void close()
    {
        obj.close();
    }

    public String SetUrl() throws IOException {

        FileInputStream dataFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties");
        prop.load(dataFile);
       return prop.getProperty("seleniumTestUrl");
    }
}
