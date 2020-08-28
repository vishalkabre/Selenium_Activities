package TestNG_Tasks;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Activity_7 {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void beforeMethod() {

		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 20);
		driver.get("http://alchemy.hguy.co/orangehrm");
	}

	@Test
	public void TestCase7() {

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

			// Find the “My Info” menu

			wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewMyDetails")));

			WebElement menu_myinfo = driver.findElement(By.id("menu_pim_viewMyDetails"));

			menu_myinfo.click();
			menu_myinfo.click();

			WebElement qualifications_menu = driver
					.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[1]/ul/li[9]/a"));

			qualifications_menu.click();
			// qualifications_menu.click();

			WebElement add_btn = driver.findElement(By.id("addWorkExperience"));

			add_btn.click();

			WebElement experience_employer = driver.findElement(By.id("experience_employer"));

			experience_employer.clear();
			experience_employer.sendKeys("Google");

			WebElement experience_title = driver.findElement(By.id("experience_jobtitle"));

			experience_title.clear();
			experience_title.sendKeys("SME");

			WebElement btnSave = driver.findElement(By.id("btnWorkExpSave"));

			btnSave.click();

		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}

	}

	@AfterMethod
	public void afterMethod() {
		driver.close();

	}

}
