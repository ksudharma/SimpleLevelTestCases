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

import com.training.pom.MyProfilePagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ChangeProfileTests {

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
		
		System.out.println("First name is :" + FN + "," + "Last name is: " + "and " + "Email is: " + EM);
		
		
		MyProfilePagePOM.AgentTitle("KrishnapriyaTitle");
		//verify if title is displayed
		WebElement Agenttitle= driver.findElement(By.name("agent_title"));		
		if(Agenttitle.isDisplayed()) {
			System.out.println("Agenttitle is displayed");
		}
		MyProfilePagePOM.phone("1234567890");
		//Verify if phone is displayed
		WebElement phone= driver.findElement(By.name("phone"));		
		if(phone.isDisplayed()) {
			System.out.println("phone is displayed");
		}
		
		MyProfilePagePOM.SaveChangesButton();
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