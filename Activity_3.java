package TestNG_Tasks;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Activity_3 {
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
		driver.get("http://alchemy.hguy.co/orangehrm");
	}

	@Test
	public void TestCase3() {

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


	}

	@AfterMethod
	public void afterMethod() {

		driver.close();

	}

}
