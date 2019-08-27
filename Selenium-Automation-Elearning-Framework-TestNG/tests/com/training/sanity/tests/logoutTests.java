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

import com.training.pom.LogoutPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class logoutTests {

	private WebDriver driver;
	private String baseUrl;
	private LogoutPOM LogoutPOM;
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
		LogoutPOM = new LogoutPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}


	@Test
	public void validLogoutTest() throws InterruptedException {

		//Click on LOG IN/REGISTER button
		LogoutPOM.LOGINorREGISTERbutton();
		Thread.sleep(2000);
		
		//Validate Login Screen is displayed by checking LOG IN/REGISTER button is present or not
		String XPath = "//div[@class='container']//ul/li[6]/a";
		Boolean iselementpresent = driver.findElements(By.xpath(XPath)).size()!= 0;
		if (iselementpresent == true)
		{
			System.out.print("LOG IN/REGISTER button is found" +"\n");
		}
		else
		{
			System.out.print("LOG IN/REGISTER button is not found" + "\n");
		}


		//Enter valid credentials in Email textbox		
		LogoutPOM.sendUserName("kitchu.nitc@gmail.com");

		//isDisplayed() to Verify
		LogoutPOM.UsernameDisplayed();
		

		//Enter valid credentials in password textbox
		LogoutPOM.sendPassword("Kichu@1234");

		//Assert to verify
		WebElement password=driver.findElement(By.name("pwd"));		
		LogoutPOM.sendPassword("Kichu@1234");
		String Pass=password.getAttribute("value");				
		String ExpectedValue= "Kichu@1234";
		Assert.assertEquals(Pass,ExpectedValue);
		System.out.println("password is displayed");

		//Click on Sign in Button
		LogoutPOM.clickLoginBtn(); 

		//Verify profile page is displayed or not
		String CurrentUrl= driver.getCurrentUrl();
		System.out.println("Profile page url is: " + CurrentUrl);

		Thread.sleep(2000);

		//Click on Log Out button
		LogoutPOM.logoutBtn();

		//Verify Login Screen is displayed by checking LOG IN/REGISTER button is present or not
		String XPath1 = "//div[@class='container']//ul/li[6]/a";
		Boolean iselementpresent1 = driver.findElements(By.xpath(XPath1)).size()!= 0;
		if (iselementpresent == true)
		{
			System.out.print("LOG IN/REGISTER button is present on the screen");
		}
		else
		{
			System.out.print("LOG IN/REGISTER button is not present on the screen");
		}


	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}


}
