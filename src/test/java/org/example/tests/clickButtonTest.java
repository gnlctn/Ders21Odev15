package org.example.tests;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class clickButtonTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @BeforeClass
    public void Setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/java/org/example/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
        js = (JavascriptExecutor) driver;
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    private By buttonOptionBy = By.xpath("//li[.='Buttons']");
    private By meButtonBy = By.xpath("//button[text()='Click Me']");
    private By confirmationMessageBy = By.xpath("//p[@id='dynamicClickMessage']");

    @Test
    public void clickMeButtonTest(){
        driver.get("https://demoqa.com/elements");
        WebElement buttonOption = driver.findElement(buttonOptionBy);;
        buttonOption.click();
        js.executeScript("window.scrollBy(0, 500)");
        WebElement meButton= driver.findElement(meButtonBy);
        meButton.click();
        WebElement confirmationMessage = driver.findElement(confirmationMessageBy);
        Assert.assertEquals(confirmationMessage.getText(), "You have done a dynamic click");

    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
