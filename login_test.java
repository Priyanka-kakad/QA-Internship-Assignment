import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginPageTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testLoginPage() {
        // navigate to login page
        driver.get("https://sakshingp.github.io/assignment/login.html");
        
        // verify that the login page URL is loaded
        Assert.assertEquals(driver.getCurrentUrl(), "https://sakshingp.github.io/assignment/login.html");
        
        // find the username and password fields and the login button
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login"));
        
        // verify that the elements are displayed and enabled
        Assert.assertTrue(usernameField.isDisplayed() && usernameField.isEnabled());
        Assert.assertTrue(passwordField.isDisplayed() && passwordField.isEnabled());
        Assert.assertTrue(loginButton.isDisplayed() && loginButton.isEnabled());
        
        // enter a valid or invalid username and password and click on login button
        usernameField.sendKeys("valid_username");
        passwordField.sendKeys("valid_password");
        loginButton.click();
        
        // verify that the user is redirected to the home page upon successful login
        Assert.assertEquals(driver.getCurrentUrl(), "https://sakshingp.github.io/assignment/home.html");
        
        // navigate back to the login page
        driver.get("https://sakshingp.github.io/assignment/login.html");
        
        // enter an invalid username and password and click on login button
        usernameField.clear();
        usernameField.sendKeys("invalid_username");
        passwordField.clear();
        passwordField.sendKeys("invalid_password");
        loginButton.click();
        
        // verify that the correct error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), "Invalid username or password.");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
