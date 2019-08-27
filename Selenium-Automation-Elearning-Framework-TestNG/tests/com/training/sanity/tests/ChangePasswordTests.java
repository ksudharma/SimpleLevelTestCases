package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.MyProfilePagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ChangePasswordTests {

	private WebDriver driver;
	private String baseUrl;
	private MyProfilePagePOM MyProfilePagePOM;

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
		MyProfilePagePOM = new MyProfilePagePOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}

	@Test
	public void validChangeProfileTest() {
		MyProfilePagePOM.sendUserName("kitchu.nitc@gmail.com");
		MyProfilePagePOM.sendPassword("Kichu@1234");
		MyProfilePagePOM.clickLoginBtn(); 

		//Verify  My Profile page with registered credentials should get displayed
		WebElement FirstName=driver.findElement(By.name("first-name"));
		WebElement LastName=driver.findElement(By.name("last-name"));
		WebElement email=driver.findElement(By.name("email"));

		String FN = FirstName.getAttribute("value");
		String LN = LastName.getAttribute("value");				
		String EM = email.getAttribute("value");

		System.out.println("First name is :" + FN + " \n" + "Last name is: " + "\n " + "Email is: " + EM);


		MyProfilePagePOM.ChangePasswordButton();
		//Verify change password page get displayed
		String CurrentUrl= driver.getCurrentUrl();
		System.out.println(CurrentUrl);
			
		MyProfilePagePOM.CurrentPassword("Kichu@1234");
		//Entered credentials in Current Password textbox should get displayed
		WebElement CurrentPwd= driver.findElement(By.name("current_pass"));		
		if(CurrentPwd.isDisplayed()) {
			System.out.println("Current Password is displayed");
		}
		
		MyProfilePagePOM.NewPass("Kichu@123");
		//Entered credentials in New Password textbox should get displayed
		WebElement NewPwd= driver.findElement(By.name("pass1"));		
		if(NewPwd.isDisplayed()) {
			System.out.println("New Password is displayed");
		}
		MyProfilePagePOM.ConfirmPass("Kichu@123");
		
		//Entered credentials in New Password textbox should get displayed
		WebElement ConfirmPwd= driver.findElement(By.name("pass2"));		
		if(ConfirmPwd.isDisplayed()) {
			System.out.println("Confirm Password is displayed");
		}
		MyProfilePagePOM.SaveChangesButton1();
		//Verify the message
		WebElement Message= driver.findElement(By.xpath("//div[@class='notification success closeable margin-bottom-35']//p"));
		String MessageVeribiage = Message.getText();
		System.out.println(MessageVeribiage);
	}


	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}
