package HomeWork;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;


public class PageDesign {

    public WebDriver myDriver;
    public PageDesign(WebDriver driver) {
        this.myDriver = driver;
    }

    //region Variables_Week3

    public String pageURL = "https://www.seleniumeasy.com/test/";
    public By closeAd = By.cssSelector("a[title='Close']");
    public By datePickers = By.xpath("//a[text()='Date pickers']");
    public By bootstrap = By.cssSelector("li[style='display: list-item;'] a[href='./bootstrap-date-picker-demo.html']");
    public By selectDate = By.cssSelector("div#sandbox-container1 div.input-group.date");
    public By prevMonth = By.cssSelector("table[class='table-condensed'] th[class='prev']");
    public By month = By.cssSelector("th[class='datepicker-switch']");
    public boolean flag;
    public By days = By.className("day");
    public By expendTable = By.xpath("//a[text()='Table']");
    public By tableDataSearch = By.cssSelector("li[style='display: list-item;'] a[href='./table-search-filter-demo.html']");
    public By tableRows = By.xpath("//table[@id='task-table']/tbody/tr");
    public By tableColumns = By.xpath("//table[@id='task-table']/tbody/tr[1]/td");
    public By tableRowsWithHead = By.cssSelector("#task-table tr");
    public By popularPostsLinks = By.xpath("//h4[text()='Popular Posts']/parent::div/ul/li/a");
    public By basePage = By.xpath("//a[text()='Demo Home']");

    //endregion

    //region Variables_Week4



    public final Logger log = LogManager.getLogger(PageDesign.class.getName());

    private final RelativeLocator.RelativeBy expendTable2 = withTagName("a").below(datePickers);
    private final By tableSortAndSearch = By.cssSelector("li[style='display: list-item;'] a[href='./table-sort-search-demo.html']");
    public By ageButton = By.xpath("//tr[@role='row']/th[4]");
    public By ageNumber = By.xpath("//tr[@role='row']/td[4]");
    public RelativeLocator.RelativeBy nextButton = withTagName("a").toRightOf(By.cssSelector("a[data-dt-idx='4']"));
    public RelativeLocator.RelativeBy entries = withTagName("Select").toLeftOf(By.cssSelector("#example_filter"));
    public By entriesValue50 = By.cssSelector("option[value='50']");
    //public RelativeLocator.RelativeBy nrOfRowsOnPage = withTagName("div").toLeftOf(By.cssSelector("#example_previous"));
    public RelativeLocator.RelativeBy nextButton2 = withTagName("a").toRightOf(By.cssSelector("a[data-dt-idx='1']"));
    public RelativeLocator.RelativeBy previousButton = withTagName("a").toLeftOf(By.cssSelector("a[data-dt-idx='1']"));
    //endregion

    //region returnElements
    public By getExpendTable2()
    {
        //return myDriver.findElement(expendTable2);
        return expendTable2;
    }
    public By getTableSortAndSearch(){
        return tableSortAndSearch;
    }
    //endregion
    //region Methods_Week4

    public void OrderAge() throws InterruptedException {
        List<String> sortedAgeList = new ArrayList<String>();
        List<String> originalAgeList = new ArrayList<String>();
        List<String> collectedSortedList = new ArrayList<>();

        Thread.sleep(500);

        List<WebElement> age =  myDriver.findElements(ageNumber);
        List<String> originalAgeList1 = age.stream().map(WebElement::getText).collect(Collectors.toList());
        originalAgeList.addAll(originalAgeList1);

        //Collect the age list and sort it in java
        while (!myDriver.findElement(nextButton).getAttribute("class").equalsIgnoreCase("paginate_button next disabled")){
            ClickElement(nextButton);
            age =  myDriver.findElements(ageNumber);
            originalAgeList1 = age.stream().map(WebElement::getText).collect(Collectors.toList());
            originalAgeList.addAll(originalAgeList1);
           //  originalAgeList.add(originalAgeList1.toString());
           // originalAgeList.add(age.stream().map(s->s.getText()).collect(Collectors.toList()).toString());
            sortedAgeList = originalAgeList.stream().sorted().collect(Collectors.toList());


            Thread.sleep(300);
        }
        //Click on age to order the table by age
        ClickElement(ageButton);

        //Collect the list again
        age =  myDriver.findElements(ageNumber);
        List<String> collectedAgeList1 = age.stream().map(WebElement::getText).collect(Collectors.toList());
        collectedSortedList.addAll(collectedAgeList1);

        while (!myDriver.findElement(nextButton).getAttribute("class").equalsIgnoreCase("paginate_button next disabled")){
            ClickElement(nextButton);
            age = myDriver.findElements(ageNumber);
            collectedAgeList1 = age.stream().map(WebElement::getText).collect(Collectors.toList());
            collectedSortedList.addAll(collectedAgeList1);
            //  originalAgeList.add(originalAgeList1.toString());
            // originalAgeList.add(age.stream().map(s->s.getText()).collect(Collectors.toList()).toString());


            Thread.sleep(300);
        }
        log.error("Original list" + originalAgeList);
        log.fatal("My sorted list" + sortedAgeList);
        log.info("Collected sorted list" + collectedSortedList);
        log.debug("try this");
        //System.out.println("Original List" + originalAgeList);
        //System.out.println("My sorted list" + sortedAgeList);
        //System.out.println("Collected sorted list" + collectedSortedList);

        //Compare the sorted lists
        Assert.assertEquals(collectedSortedList,sortedAgeList);

    }

    public void CheckNumberOfDisplayedEntries()
    {

        int value =  Integer.parseInt( myDriver.findElement(entriesValue50).getAttribute("value"));
        if (getSize(ageNumber) > value)
        {
            Assert.assertEquals(getSize(ageNumber),value,"Too manny rows displayed");
        }
        else if (getSize(ageNumber) < value)
        {
            ScrollToElement(previousButton);
          /*  System.out.println("Check total number of rows");
            String text = DriverSettings.getDriver().findElement(nrOfRowsOnPage).getText();
            System.out.println(text);
           */
            boolean flagNextButton =  myDriver.findElement(nextButton2)
                    .getAttribute("class").equalsIgnoreCase("paginate_button next disabled");
            boolean flagPreviousButton =  myDriver.findElement(previousButton)
                    .getAttribute("class").equalsIgnoreCase("paginate_button previous disabled");
            Assert.assertTrue(flagNextButton,"Should be disabled");
            Assert.assertTrue(flagPreviousButton,"Should be disabled");


        }
        else
            Assert.assertEquals(getSize(ageNumber),value,"If it failed here, something is really wrong... :))");

    }
    //endregion

    //region Methods_Week3
    public void GoToURL(String URL)
    {

        myDriver.get(URL);
        Assert.assertEquals(myDriver.getCurrentUrl(),URL);
        log.info("The Url was correct");
    }

    public void maximize() throws IOException {

        myDriver.manage().window().maximize();
    }

    public void ClickElement (By element) {
        WebDriver obj = myDriver;
        obj.findElement(element).click();

    }

    public String getText(By element)
    {
        WebDriver obj = myDriver;
        return obj.findElement(element).getText();
    }

    public int getSize (By elements)
    {
        WebDriver obj = myDriver;
        return obj.findElements(elements).size();
    }

    public void ClickDate(int count, String theDay)
    {
        WebDriver obj = myDriver;
        for (int i=0; i<count; i++)
        {
            String Day = obj.findElements(days).get(i).getText();
            if(Day.equalsIgnoreCase(theDay))
            {
                obj.findElements(days).get(i).click();
                break;
            }
        }
    }

    public void ScrollToElement(By element)
    {
        WebDriver obj = myDriver;
        WebElement elem = obj.findElement(element);
        JavascriptExecutor js = (JavascriptExecutor)obj;
        js.executeScript("arguments[0].scrollIntoView();", elem);
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public List<String> OpenLinks(By elements)
    {
        WebDriver obj = myDriver;
        int count = getSize(elements);
        String clickLinks = Keys.chord(Keys.CONTROL, Keys.ENTER);
        List<String> urls= new ArrayList<>();

        for (int i=0;i<count;i++)
        {
            obj.findElements(elements).get(i).sendKeys(clickLinks);
            String url = obj.findElements(elements).get(i).getAttribute("href");
            urls.add(url);
        }
        return urls;
    }

    public void SwitchTabs(List<String> urls)
    {
        WebDriver obj = myDriver;
        Set<String> ids = obj.getWindowHandles();
        Iterator<String> it = ids.iterator();
        boolean itIs;
        String parent = it.next();
       // it.next();
        while (it.hasNext())
        {
            obj.switchTo().window(it.next());
            System.out.println(obj.getTitle());
            itIs = urls.contains(obj.getCurrentUrl());
            Assert.assertTrue(itIs,"This url is wrong :" + obj.getCurrentUrl());
            obj.close();

        }
        obj.switchTo().window(parent);
    }
   /* public void TakeScreenshot(String testCaseName) throws IOException {
        WebDriver obj = myDriver;
        File src = ((TakesScreenshot)obj).getScreenshotAs(OutputType.FILE);
        String destinationFolder = System.getProperty("user.dir") + "\\reports\\" + testCaseName+".png";
        FileUtils.copyFile(src, new File(destinationFolder));
        //FileUtils.copyFile(src, new File("C:\\Users\\adrian.ciortea.WS-CJ-U3-3358\\Desktop\\screenshot.png"));
    }*/



    //endregion

}
