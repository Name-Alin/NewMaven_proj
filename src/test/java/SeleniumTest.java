/*
import org.testng.annotations.Test;

import java.io.IOException;

public class SeleniumTest extends PageDesign {

    @Test
    public void ThisIsATest()
    {
        System.out.println("test something");
    }
    @Test
    public static void RunSeleniumTests() throws IOException, InterruptedException {
      /*  DriverSettings.SetDriver();
        // DriverSettings.getDriver();
        //open https://www.seleniumeasy.com/test/
        PageDesign.GoToURL(pageURL);
        PageDesign.maximize();

        PageDesign.ClickElement(closeAd);
        //From the ‘Menu List’ expend ‘Date Pickers’ and click on ‘Bootstrap Date Pickers’
        PageDesign.ClickElement(datePickers);
        PageDesign.ClickElement(bootstrap);

        //	Select ‘25 Deccember 2020’
        Thread.sleep(500);
        PageDesign.ClickElement(selectDate);

        while (!flag)
        {
            PageDesign.ClickElement(prevMonth);
            flag = getText(month).contains("December 2020");
        }

        int count = getSize(days);
        ClickDate(count,"25");

        //From the ‘Menu List’ expend ‘Table’ and click on ‘Table Data Search’
        PageDesign.ClickElement(expendTable);
        PageDesign.ClickElement(tableDataSearch);
        //Count the number of columns and rows from the Tasks table
        System.out.println(PageDesign.getSize(tableRows));
        System.out.println(PageDesign.getSize(tableColumns));
        // in caz ca trebuia cu tot cu head... :)
        System.out.println(PageDesign.getSize(tableRowsWithHead));

        //Scroll down to the bottom of the page
        PageDesign.ScrollToElement(popularPostsLinks);
        Thread.sleep(1000);
        //Open every link from the footer section ‘Popular Posts’ in a new tab
        List<String> urls = PageDesign.OpenLinks(popularPostsLinks);
        //Switch focus to every opened new tab and validate links
        SwitchTabs(urls);

        //Go back to the base page and create a screenshot
        PageDesign.ScrollToElement(basePage);
        // PageDesign.GoToURL(pageURL);
        PageDesign.ClickElement(basePage);
        PageDesign.TakeScreenshot();

        DriverSettings.close();




    }
}
*/

public interface SeleniumTest {

    public void tryAbstract();
//    {
//        System.out.println("something");
//    }
    public void tryNonAbstract();
//    {
//        System.out.println("This is the non abstract method");
//    }


    static String city = "Cluj";

    public static void getStaticCity(){
        System.out.println("Static method in interface");
    }

    public void getCity1();


}