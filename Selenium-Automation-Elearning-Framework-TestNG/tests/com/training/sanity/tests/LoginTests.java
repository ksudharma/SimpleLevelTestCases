package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LoginTests {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void validLoginTest() {
		loginPOM.sendUserName("kitchu.nitc@gmail.com");
		
		//verify if displayed or not
		WebElement username= driver.findElement(By.name("log"));		
		if(username.isDisplayed()) {
			System.out.println("username is displayed");
		}		
		
		WebElement password=driver.findElement(By.name("pwd"));
		
		//Assert to verify
		loginPOM.sendPassword("Kichu@1234");
		String Pass=password.getAttribute("value");				
		String ExpectedValue= "Kichu@1234";
		Assert.assertEquals(Pass,ExpectedValue);

		System.out.println("password is displayed");


		loginPOM.clickLoginBtn(); 
		//Verify my profile page get displayed
		String CurrentUrl= driver.getCurrentUrl();
		System.out.println(CurrentUrl);

		screenShot.captureScreenShot("First");
	}
}
