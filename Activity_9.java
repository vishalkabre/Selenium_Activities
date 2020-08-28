package TestNG_Tasks;

import java.util.List;
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

public class Activity_9 {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void beforeMethod() {

		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 40);
		driver.get("http://alchemy.hguy.co/orangehrm");
	}

	@Test
	public void TestCase9() {

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

			// Click on the “Emergency Contacts” menu

			driver.findElement(By.xpath("//*[@id='sidenav']/li[3]/a")).click();

			// Retrieve information about all the contacts listed in the table

			List<WebElement> emgcontact_list = driver.findElements(By.xpath("//*[@id='emgcontact_list']/tbody"));

			for (WebElement row : emgcontact_list) {

				System.out.println(row.getText());

			}

		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}

	}

	@AfterMethod
	public void afterMethod() {
		driver.close();

	}

}
