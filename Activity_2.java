package TestNG_Tasks;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class Activity_2 {
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		driver = new FirefoxDriver();
		driver.get("http://alchemy.hguy.co/orangehrm");
	}

	@Test
	public void TestCase2() {

		WebElement img = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[1]/img"));
		String src = img.getAttribute("src");
		System.out.println("Here the url of the header image >> " + src);

	}

	@AfterMethod
	public void afterMethod() {
		driver.close();

	}

}
