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
import org.springframework.web.bind.annotation.RequestParam;
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

public class WorkerTest {
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
    public void workerListPageTest() {
        driver.get(URL + "worker/all");
        WebElement button = wait.until(visibilityOfElementLocated(By.id("all_workers_link")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "Workers list");
    }

    @Test(priority = 2)
    public void addWorkerTrueTest() {
        driver.get(URL + "worker/all");

        int before = driver.findElements(By.name("one_worker")).size();

        WebElement button = wait.until(visibilityOfElementLocated(By.name("add_worker_button")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "New worker");

        driver.findElement(By.name("w_name")).sendKeys("Test_name");
        driver.findElement(By.name("w_sername")).sendKeys("Test_name");
        driver.findElement(By.name("w_patronymic")).sendKeys("Test_name");
        driver.findElement(By.name("w_birthdate")).sendKeys("2000-01-01");
        driver.findElement(By.name("w_phone")).sendKeys("Test_phone");
        driver.findElement(By.name("w_email")).sendKeys("Test_email");
        driver.findElement(By.name("w_address")).sendKeys("Test_email");
        driver.findElement(By.name("w_first_day")).sendKeys("2020-01-01");
        driver.findElement(By.name("w_last_day")).sendKeys("2020-02-01");
        driver.findElement(By.name("w_post")).sendKeys("Test_post");
        driver.findElement(By.name("w_salary")).sendKeys("200");

        WebElement button_1 = wait.until(visibilityOfElementLocated(By.name("submit_button")));
        button_1.click();
        wait.until(stalenessOf(button_1));

        int after = driver.findElements(By.name("one_worker")).size();

        Assert.assertEquals(before, after-1);
        Assert.assertEquals(driver.getTitle(), "Workers list");
    }

    @Test(priority = 3)
    public void addWorkerFalseTest() {
        driver.get(URL + "worker/all");

        WebElement button = wait.until(visibilityOfElementLocated(By.name("add_worker_button")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "New worker");

        driver.findElement(By.name("w_name")).sendKeys("Test_name");
        driver.findElement(By.name("w_sername")).sendKeys("Test_name");
        driver.findElement(By.name("w_patronymic")).sendKeys("Test_name");
        driver.findElement(By.name("w_birthdate")).sendKeys("2000-01-01");
        driver.findElement(By.name("w_phone")).sendKeys("Test_phone");
        driver.findElement(By.name("w_email")).sendKeys("Test_email");
        driver.findElement(By.name("w_address")).sendKeys("Test_email");
        driver.findElement(By.name("w_first_day")).sendKeys("2020-october-01");
        driver.findElement(By.name("w_last_day")).sendKeys("2020-02-01");
        driver.findElement(By.name("w_post")).sendKeys("Test_post");
        driver.findElement(By.name("w_salary")).sendKeys("100");

        WebElement button_1 = wait.until(visibilityOfElementLocated(By.name("submit_button")));
        button_1.click();
        wait.until(stalenessOf(button_1));

        Assert.assertEquals(driver.getTitle(), "Error");
    }

    @Test(priority = 3)
    public void updateWorkerTrueTest() {
        driver.get(URL + "worker/all");
        WebElement button = wait.until(visibilityOfElementLocated(By.name("update_button")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "Update worker");

        driver.findElement(By.name("w_salary")).sendKeys("10");

        WebElement button_1 = wait.until(visibilityOfElementLocated(By.name("submit_button")));
        button_1.click();
        wait.until(stalenessOf(button_1));
        Assert.assertEquals(driver.getTitle(), "Workers list");
    }

    @Test(priority = 4)
    public void updateWorkerFalseTest() {
        driver.get(URL + "worker/all");
        WebElement button = wait.until(visibilityOfElementLocated(By.name("update_button")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "Update worker");

        driver.findElement(By.name("w_birthdate")).sendKeys("2000-01");

        WebElement button_1 = wait.until(visibilityOfElementLocated(By.name("submit_button")));
        button_1.click();
        wait.until(stalenessOf(button_1));
        Assert.assertEquals(driver.getTitle(), "Error");
    }

    @Test(priority = 5)
    public void deleteWorkerTest() {
        driver.get(URL + "worker/all");

        int before = driver.findElements(By.name("one_worker")).size();

        WebElement button = wait.until(visibilityOfElementLocated(By.name("delete_button")));
        button.click();
        wait.until(stalenessOf(button));
        Assert.assertEquals(driver.getTitle(), "Workers list");

        int after = driver.findElements(By.name("one_worker")).size();

        Assert.assertEquals(before, after+1);
    }


    @Test(priority = 6)
    public void dismissWorkerTest() {
        driver.get(URL + "worker/all");

        int before = driver.findElements(By.name("one_worker")).size();

        WebElement button = wait.until(visibilityOfElementLocated(By.name("dismiss_button")));
        button.click();
        wait.until(stalenessOf(button));

        int after = driver.findElements(By.name("one_worker")).size();

        Assert.assertEquals(before, after);
        Assert.assertEquals(driver.getTitle(), "Workers list");
    }

    @Test(priority = 7)
    public void infoWorkerTest() {
        driver.get(URL + "worker/all");
        WebElement button = wait.until(visibilityOfElementLocated(By.name("info_button")));
        button.click();
        wait.until(stalenessOf(button));

        Assert.assertEquals(driver.getTitle(), "Worker info");
    }

    @Test(priority = 8)
    public void newWorkerRoleTrueTest() {
        driver.get(URL + "worker/all");
        WebElement button = wait.until(visibilityOfElementLocated(By.name("role_button")));
        button.click();
        wait.until(stalenessOf(button));

        Assert.assertEquals(driver.getTitle(), "New role");

        Select selectW = new Select(driver.findElement(By.name("r_project")));
        selectW.selectByVisibleText("Test_title");
        driver.findElement(By.name("r_role")).sendKeys("Test_role");
        driver.findElement(By.name("r_description")).sendKeys("Test_description");
        driver.findElement(By.name("r_start_date")).sendKeys("2020-02-02");
        driver.findElement(By.name("r_end_date")).sendKeys("2020-03-02");

        WebElement button_1 = wait.until(visibilityOfElementLocated(By.name("submit_button")));
        button_1.click();
        wait.until(stalenessOf(button_1));
        Assert.assertEquals(driver.getTitle(), "Workers list");
    }

    @Test(priority = 9)
    public void newWorkerRoleFalseTest() {
        driver.get(URL + "worker/all");
        WebElement button = wait.until(visibilityOfElementLocated(By.name("role_button")));
        button.click();
        wait.until(stalenessOf(button));

        Assert.assertEquals(driver.getTitle(), "New role");

        Select selectW = new Select(driver.findElement(By.name("r_project")));
        selectW.selectByVisibleText("Test_title");
        driver.findElement(By.name("r_role")).sendKeys("Test_role");
        driver.findElement(By.name("r_description")).sendKeys("Test_description");
        driver.findElement(By.name("r_start_date")).sendKeys("2020-02");
        driver.findElement(By.name("r_end_date")).sendKeys("2020-03-02");

        WebElement button_1 = wait.until(visibilityOfElementLocated(By.name("submit_button")));
        button_1.click();
        wait.until(stalenessOf(button_1));
        Assert.assertEquals(driver.getTitle(), "Error");
    }

    @AfterClass
    public void end() {
        driver.quit();
    }

}