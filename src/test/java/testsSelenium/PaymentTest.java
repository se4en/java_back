package testsSelenium;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import services.PaymentService;
import services.ProjectService;
import services.RoleService;
import services.WorkerService;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class PaymentTest {
    String URL = "http://localhost:8080/";
    WebDriver driver;
    WebDriverWait wait;

    WorkerService workerService = new WorkerService();
    ProjectService projectService = new ProjectService();
    PaymentService paymentService = new PaymentService();
    RoleService roleService = new RoleService();

    @BeforeClass
    public void settings() {
        System.setProperty("webdriver.gecko.driver", "/home/dmitry/demo/geckodriver-v0.29.1-linux64/geckodriver");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        driver = new FirefoxDriver(capabilities);
        driver.manage().window().setSize(new Dimension(1000, 1000));
        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);
    }

    @Test(priority = 1)
    public void paymentListPageTest() {
        driver.get(URL + "project/all");
        WebElement button = wait.until(visibilityOfElementLocated(By.id("all_payments_link")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "Payments list");
    }

    @Test(priority = 2)
    public void addPaymentTrueTest() {
        driver.get(URL + "payment/all");

        int before = driver.findElements(By.name("one_payment")).size();

        WebElement button = wait.until(visibilityOfElementLocated(By.name("add_payment_button")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "New employer page");

        Select selectW = new Select(driver.findElement(By.name("p_worker")));
        selectW.selectByVisibleText("Anton Brazhnikov");
        driver.findElement(By.name("p_type")).sendKeys("Test_type");
        driver.findElement(By.name("p_date")).sendKeys("2010-10-10");
        driver.findElement(By.name("p_amount")).sendKeys("100");

        WebElement button_1 = wait.until(visibilityOfElementLocated(By.name("submit_button")));
        button_1.click();
        wait.until(stalenessOf(button_1));

        int after = driver.findElements(By.name("one_payment")).size();

        Assert.assertEquals(before, after-1);
        Assert.assertEquals(driver.getTitle(), "Payments list");
    }

    @Test(priority = 3)
    public void addPaymentFalseTest() {
        driver.get(URL + "payment/all");

        WebElement button = wait.until(visibilityOfElementLocated(By.name("add_payment_button")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "New employer page");

        Select selectW = new Select(driver.findElement(By.name("p_worker")));
        selectW.selectByVisibleText("Anton Brazhnikov");
        driver.findElement(By.name("p_type")).sendKeys("Test_type");
        driver.findElement(By.name("p_date")).sendKeys("2010-december-10");
        driver.findElement(By.name("p_amount")).sendKeys("100");

        WebElement button_1 = wait.until(visibilityOfElementLocated(By.name("submit_button")));
        button_1.click();
        wait.until(stalenessOf(button_1));

        Assert.assertEquals(driver.getTitle(), "Error");
    }

    @Test(priority = 3)
    public void updatePaymentTrueTest() {
        driver.get(URL + "payment/all");
        WebElement button = wait.until(visibilityOfElementLocated(By.name("update_button")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "Update project");

        driver.findElement(By.name("p_amount")).sendKeys("100");

        WebElement button_1 = wait.until(visibilityOfElementLocated(By.name("submit_button")));
        button_1.click();
        wait.until(stalenessOf(button_1));
        Assert.assertEquals(driver.getTitle(), "Payments list");
    }

    @Test(priority = 4)
    public void updatePaymentFalseTest() {
        driver.get(URL + "payment/all");
        WebElement button = wait.until(visibilityOfElementLocated(By.name("update_button")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "Update project");

        driver.findElement(By.name("p_date")).sendKeys("october");

        WebElement button_1 = wait.until(visibilityOfElementLocated(By.name("submit_button")));
        button_1.click();
        wait.until(stalenessOf(button_1));
        Assert.assertEquals(driver.getTitle(), "Error");
    }

    @Test(priority = 5)
    public void deletePaymentTest() {
        driver.get(URL + "payment/all");

        int before = driver.findElements(By.name("one_payment")).size();

        WebElement button = wait.until(visibilityOfElementLocated(By.name("delete_button")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "Payments list");

        int after = driver.findElements(By.name("one_payment")).size();

        Assert.assertEquals(before, after+1);
    }


    @Test(priority = 6)
    public void workerPaymentTest() {
        driver.get(URL + "payment/all");

        WebElement button = wait.until(visibilityOfElementLocated(By.name("worker_button")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "Worker info");
    }

    @AfterClass
    public void end() {
        driver.quit();
    }

}