import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class HomePageTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("web-driver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testHomePage() {
        // navigate to login page
        driver.get("https://sakshingp.github.io/assignment/login.html");

        // find the username and password fields and the login button
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login"));

        // enter a valid username and password and click on login button
        usernameField.sendKeys("valid_username");
        passwordField.sendKeys("valid_password");
        loginButton.click();

        // verify that the user is redirected to the home page upon successful login
        Assert.assertEquals(driver.getCurrentUrl(), "https://sakshingp.github.io/assignment/home.html");

        // find the AMOUNT header in the transaction table
        WebElement amountHeader = driver.findElement(By.xpath("//table//th[text()='AMOUNT']"));

        // verify that the AMOUNT header in the transaction table is clickable
        Assert.assertTrue(amountHeader.isDisplayed() && amountHeader.isEnabled());

        // click on the AMOUNT header
        amountHeader.click();

        // get all the values from the amount column
        List<WebElement> amountColumn = driver.findElements(By.xpath("//table//td[4]"));
        List<Double> amountList = new ArrayList<>();
        for(WebElement element:amountColumn){
            amountList.add(Double.parseDouble(element.getText()));
        }

        // check if the values are sorted
        for(int i=0; i < amountList.size() - 1; i++) {
            Assert.assertTrue(amountList.get(i) <= amountList.get(i+1));
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
