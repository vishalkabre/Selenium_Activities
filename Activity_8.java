package TestNG_Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Activity_8 {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void beforeMethod() {

		// System.setProperty("webdriver.gecko.driver","C:\\Windows\\System32\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 40);
		driver.get("http://alchemy.hguy.co/orangehrm");
	}

	@Test
	public void TestCase8() {

		try {

			// Login

			WebElement txtUsername = driver.findElement(By.id("txtUsername"));
			WebElement txtPassword = driver.findElement(By.id("txtPassword"));

			WebElement btnLogin = driver.findElement(By.id("btnLogin"));

			txtUsername.sendKeys("orange");
			txtPassword.sendKeys("orangepassword123");

			btnLogin.click();

			String actualTitle = driver.getTitle();
			String expectedTitle = "OrangeHRM";
			Assert.assertEquals(expectedTitle, actualTitle);

			// navigate to dashboard page

			wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_dashboard_index")));

			WebElement menu_dashboard = driver.findElement(By.id("menu_dashboard_index"));

			menu_dashboard.click();
			menu_dashboard.click();

			// Apply leave

			WebElement leavePage = driver.findElement(By.linkText("Apply Leave"));

			leavePage.click();
			leavePage.click();

			WebElement type = driver.findElement(By.xpath("//select[@id='applyleave_txtLeaveType']"));

			Select leave = new Select(type);

			leave.selectByVisibleText("Paid Leave");

			WebElement fromDate = driver.findElement(By.id("applyleave_txtFromDate"));

			fromDate.clear();

			fromDate.click();

			DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			LocalDateTime now = LocalDateTime.now();

			fromDate.sendKeys(date.format(now));
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE);
			
			// Apply button

			if (driver.findElement(By.id("applyBtn")).isDisplayed()) {

				action.moveToElement(driver.findElement(By.id("applyBtn")), 100, 0);

				action.moveToElement(driver.findElement(By.id("applyBtn"))).click().build().perform();

				// driver.findElement(By.id("applyBtn")).click();
			}

			// Verify the applied leave

			driver.findElement(By.xpath("//*[@id='menu_leave_viewMyLeaveList']")).click();

			WebElement fDate = driver.findElement(By.id("calFromDate"));

			fDate.clear();

			fDate.sendKeys(date.format(now));

			WebElement tDate = driver.findElement(By.id("calToDate"));

			tDate.clear();

			tDate.sendKeys(date.format(now));

			driver.findElement(By.id("btnSearch")).click();

			driver.findElement(By.linkText(date.format(now))).click();

			// Verify status 

			String status = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr/td[5]")).getText();

			System.out.println("Leave Status is: " + status);

		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}

	}

	@AfterMethod
	public void afterMethod() {
		driver.close();

	}

}
