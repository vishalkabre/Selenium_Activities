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

public class Activity_6 {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void beforeMethod() {

		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 40);
		driver.get("http://alchemy.hguy.co/orangehrm");
	}

	@Test
	public void TestCase6() {

		try {

			// Login

			WebElement txtUsername = driver.findElement(By.id("txtUsername"));
			WebElement txtPassword = driver.findElement(By.id("txtPassword"));

			WebElement btnLogin = driver.findElement(By.id("btnLogin"));

			txtUsername.sendKeys("orange");
			txtPassword.sendKeys("orangepassword123");

			btnLogin.click();

			// Find the “Directory” menu

			wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_directory_viewDirectory")));

			WebElement directory = driver.findElement(By.id("menu_directory_viewDirectory"));

			directory.click();
			directory.click();

			// Get the Webelement

			// Verify that the heading of the page matches “Search Directory”

			WebElement directory_menu = driver.findElement(By.xpath("//*[@id='content']/div[1]/div[1]/h1"));
			String directory_text = directory_menu.getText();
			Assert.assertEquals(directory_text, "Search Directory");

		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());

		}

	}

	@AfterMethod
	public void afterMethod() {

		driver.close();

	}

}
