import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class MyJunit {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void SetUp() {
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions ops = new FirefoxOptions();
        ops.addArguments("--headed");
        driver = new FirefoxDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


    }

    @Test
    public void getTitle() {
        driver.get("https://demoqa.com/");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("ToolsQA"));

    }

    @Test
    public void checkImage() {
        driver.get("https://demoqa.com/");
//        boolean status = driver.findElement(By.className("banner-image")).isDisplayed();
//        WebElement element = driver.findElement(By.className("banner-image"));
//       boolean status= element.isDisplayed();
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        boolean status = wait.until(ExpectedConditions.elementToBeClickable(By.className("banner-image"))).isDisplayed();
        System.out.println(status);
    }

    @Test
    public void writeOnText() {
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys("Mr. Alamin Mondol");
        driver.findElement(By.id("userEmail")).sendKeys("alamin@gmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("Dhaka-1212");
        driver.findElement(By.id("submit")).submit();


    }

    @Test
    public void clickOnButtonFromMultipleElement() {
        driver.get("https://demoqa.com/buttons");
        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        WebElement rightClick = driver.findElement(By.id("rightClickBtn"));
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClickBtn).perform();
        actions.contextClick(rightClick).perform();
        String text1 = driver.findElement(By.id("doubleClickMessage")).getText();
        String text2 = driver.findElement(By.id("rightClickMessage")).getText();
        Assert.assertTrue(text1.contains("You have done a double click"));
        Assert.assertTrue(text2.contains("You have done a right click"));


    }


    // same but using array
    @Test
    public void clickButton() {
        driver.get("https://demoqa.com/buttons");
        List<WebElement> buttonElement = driver.findElements(By.tagName("button"));
        Actions actions = new Actions(driver);
        actions.doubleClick(buttonElement.get(1)).perform();
        actions.contextClick(buttonElement.get(2)).perform();
        actions.click(buttonElement.get(3)).perform();


    }

    @Test
    public void handleAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        driver.switchTo().alert().accept();
//        driver.findElement(By.id("timerAlertButton")).click();
//        driver.switchTo().alert().accept();
        driver.findElement(By.id("confirmButton")).click();
        driver.switchTo().alert().dismiss();
        driver.findElement(By.id("promtButton")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().sendKeys("Fahim");
        driver.switchTo().alert().accept();
        String text3 = driver.findElement(By.id("promptResult")).getText();
        Assert.assertTrue(text3.contains("Fahim"));

    }

    @Test
    public void datePicker() {
        driver.get("https://demoqa.com/date-picker");
        driver.findElement(By.id("datePickerMonthYearInput")).clear();
        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys("07/25/2022");
        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(Keys.ENTER);
    }

    @Test
    public void selectDrop() {
        driver.get("https://demoqa.com/select-menu");
        Select color = new Select(driver.findElement(By.id("oldSelectMenu")));
        color.selectByValue("1");


    }

    @After
    public void finished() {
//        driver.close();
//        driver.quit();
    }
}
