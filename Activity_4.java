package TestNG_Tasks;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Activity_4 {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void beforeMethod() {

		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 40);
		driver.get("http://alchemy.hguy.co/orangehrm");
	}

	@Test
	public void TestCase4() {

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

			// Find the PIM option in the menu

			wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewPimModule")));

			WebElement menu_pim = driver.findElement(By.id("menu_pim_viewPimModule"));

			menu_pim.click();
			menu_pim.click();

			WebElement menu_pim_addemp = driver.findElement(By.id("menu_pim_addEmployee"));

			// Add button to add 
			
			menu_pim_addemp.click();

			WebElement firstName = driver.findElement(By.id("firstName"));
			WebElement lastName = driver.findElement(By.id("lastName"));

			WebElement selectchkLogin = driver.findElement(By.id("chkLogin"));
			WebElement btnSave = driver.findElement(By.id("btnSave"));

			Random rand = new Random();
			int rand_number = rand.nextInt(1000);

			firstName.sendKeys("Jason " + rand_number);

			lastName.sendKeys("Huggins " + rand_number);

			selectchkLogin.click();

			WebElement user_name = driver.findElement(By.id("user_name"));

			user_name.sendKeys("Jason_Huggins" + rand_number);

			btnSave.click();

			// go to Admin page
			
			WebElement menu_admin_viewAdminModule = driver.findElement(By.xpath("//*[@id='menu_admin_viewAdminModule']"));

			menu_admin_viewAdminModule.click();

			WebElement searchSystemUser_userName = driver.findElement(By.id("searchSystemUser_userName"));
			WebElement searchBtn = driver.findElement(By.id("searchBtn"));

			searchSystemUser_userName.sendKeys("Jason_Huggins" + rand_number);
			searchBtn.click();
			
			WebElement verify_user = driver.findElement(By.linkText("Jason_Huggins" + rand_number));

			Assert.assertEquals(verify_user.getText(), "Jason_Huggins" + rand_number);
			

		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}

	}

	@AfterMethod
	public void afterMethod() {

		driver.close();

	}

}
