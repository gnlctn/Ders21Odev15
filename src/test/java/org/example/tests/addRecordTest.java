package org.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class addRecordTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private By addButtonBy = By.xpath("//button[@id='addNewRecordButton']");
    private By firstNameBoxBy = By.xpath("//input[@id='firstName']");
    private By lastNameBoxBy = By.xpath("//input[@id='lastName']");
    private By userEmailBoxBy = By.xpath("//input[@id='userEmail']");
    private By ageBoxBy = By.xpath("//input[@id='age']");
    private By salaryBoxBy = By.xpath("//input[@id='salary']");
    private By departmentBoxBy = By.xpath("//input[@id='department']");
    private By submitButtonBy = By.xpath("//button[@id='submit']");
    private By editButtonBy = By.xpath("//div[@class='rt-tbody']/div[4]//span[@class='mr-2']");
    private By salaryUpdateControlBy = By.xpath("//div[@class='rt-tr-group']/following::div[27]");


    @BeforeClass
    public void Setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/java/org/example/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
        js = (JavascriptExecutor) driver;
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void addTest(){
        driver.get("https://demoqa.com/webtables");
        WebElement addButton = driver.findElement(addButtonBy);
        addButton.click();

        WebElement firstNameBox = driver.findElement(firstNameBoxBy);
        firstNameBox.sendKeys("Gönül");

        WebElement lastNameBox = driver.findElement(lastNameBoxBy);
        lastNameBox.sendKeys("Çetin");

        WebElement userEmailBox = driver.findElement(userEmailBoxBy);
        userEmailBox.sendKeys("gnlntc@gmail.com");

        WebElement ageBox = driver.findElement(ageBoxBy);
        ageBox.sendKeys("30");

        WebElement salaryBox = driver.findElement(salaryBoxBy);
        salaryBox.sendKeys("50000");

        WebElement departmentBox = driver.findElement(departmentBoxBy);
        departmentBox.sendKeys("IT");

        WebElement submitButton = driver.findElement(submitButtonBy);
        submitButton.click();

        js.executeScript("window.scrollBy(0, 500)");

        WebElement editButton = driver.findElement(editButtonBy);
        editButton.click();

        WebElement salaryField = driver.findElement(salaryBoxBy);
        salaryField.clear();
        salaryField.sendKeys("60000");

        WebElement submitUpdateClick = driver.findElement(submitButtonBy);
        submitUpdateClick.click();

        WebElement salaryUpdateControl = driver.findElement(salaryUpdateControlBy);
        Assert.assertTrue(salaryUpdateControl.isDisplayed());
        Assert.assertEquals(salaryUpdateControl.getText(), "60000");
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


