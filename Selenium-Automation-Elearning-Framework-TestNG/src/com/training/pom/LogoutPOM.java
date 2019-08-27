package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPOM {

	private WebDriver driver; 

	public LogoutPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	//log in---------------------------------------------------------------------------------------------------

	@FindBy(xpath="//div[@class='container']//ul/li[6]/a")
	private WebElement LOGINorREGISTERbutton; 	

	@FindBy(id="user_login")
	private WebElement userName; 

	@FindBy(id="user_pass")
	private WebElement password;

	@FindBy(name="login")
	private WebElement loginBtn; 

	public void LOGINorREGISTERbutton() {
		this.LOGINorREGISTERbutton.click(); 
	}

	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}

	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}

	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}

	//log out------------------------------------------------------------------------------------------------------


	@FindBy(xpath="//div[@class='my-account-nav-container']//ul[2]//li[2]/a")
	private WebElement logoutBtn; 

	@FindBy(name="log")
	private WebElement UsernameDisplayed;

	public void logoutBtn() {
		this.logoutBtn.click(); 
	}	
	
	public void UsernameDisplayed() {
		if(UsernameDisplayed.isDisplayed()) {
			System.out.println("username is displayed");
		}
	}

}





