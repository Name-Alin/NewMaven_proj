package HomeWork;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class Week3_Alin extends DriverSettings {

    //private static final PageDesign pageDesign = new PageDesign(DriverSettings.getDriver());

   @BeforeTest
    public void init() throws IOException {
        DriverSettings.SetDriver();
    }
    @Test
    public void HomeWork_week3 () throws InterruptedException, IOException {
        //DriverSettings.SetDriver();
       // PageDesign pageDesign = new PageDesign(DriverSettings.getDriver());
        WebDriver driver = getDriver();
        PageDesign pageDesign = new PageDesign(driver);
        //open https://www.seleniumeasy.com/test/
        pageDesign.GoToURL(pageDesign.pageURL);
        pageDesign.maximize();

        pageDesign.ClickElement(pageDesign.closeAd);
        //From the ‘Menu List’ expend ‘Date Pickers’ and click on ‘Bootstrap Date Pickers’
        pageDesign.ClickElement(pageDesign.datePickers);
        pageDesign.ClickElement(pageDesign.bootstrap);

        //	Select ‘25 Deccember 2020’
        Thread.sleep(500);
        pageDesign.ClickElement(pageDesign.selectDate);

        while (!pageDesign.flag)
        {
            pageDesign.ClickElement(pageDesign.prevMonth);
            pageDesign.flag = pageDesign.getText(pageDesign.month).contains("December 2020");
        }

        int count = pageDesign.getSize(pageDesign.days);
        pageDesign.ClickDate(count,"25");

        //From the ‘Menu List’ expend ‘Table’ and click on ‘Table Data Search’
        pageDesign.ClickElement(pageDesign.expendTable);
        pageDesign.ClickElement(pageDesign.tableDataSearch);
        //Count the number of columns and rows from the Tasks table
        System.out.println(pageDesign.getSize(pageDesign.tableRows));
        System.out.println(pageDesign.getSize(pageDesign.tableColumns));
        // in caz ca trebuia cu tot cu head... :)
        System.out.println(pageDesign.getSize(pageDesign.tableRowsWithHead));

        //Scroll down to the bottom of the page
        pageDesign.ScrollToElement(pageDesign.popularPostsLinks);
        Thread.sleep(1000);
        //Open every link from the footer section ‘Popular Posts’ in a new tab
        List<String> urls = pageDesign.OpenLinks(pageDesign.popularPostsLinks);
        //Switch focus to every opened new tab and validate links
        pageDesign.SwitchTabs(urls);

        //Go back to the base page and create a screenshot
        pageDesign.ScrollToElement(pageDesign.basePage);
       // PageDesign.GoToURL(pageURL);
        pageDesign.ClickElement(pageDesign.basePage);
        //pageDesign.TakeScreenshot();

        //DriverSettings.close();
        close();

    }


}
