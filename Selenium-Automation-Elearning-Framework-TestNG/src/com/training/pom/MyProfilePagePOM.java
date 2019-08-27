package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyProfilePagePOM {
	
private WebDriver driver; 
	
	public MyProfilePagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//login elements
	
	@FindBy(id="user_login")
	private WebElement userName; 
	
	@FindBy(id="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement loginBtn; 
	

	//profile class elements
	
	@FindBy(id="agent_title")
	private WebElement AgentTitle; 
	
	@FindBy(id="phone")
	private WebElement phone;
	
	@FindBy(xpath="//button[@value='Submit']")
	private WebElement SaveChangesButton; 
	
	//change password
	
	@FindBy(xpath="//div[@class='my-account-nav-container']//ul[2]//li//a")
	private WebElement ChangePasswordButton; 
	
	@FindBy(name="current_pass")
	private WebElement CurrentPassword;	
	
	@FindBy(name="pass1")
	private WebElement NewPass;	
	
	@FindBy(name="pass2")
	private WebElement ConfirmPass;		
	
	@FindBy(name="wp-submit")
	private WebElement SaveChangesButton1;		
	
	
	//login methods
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
	
	//profile class methods

	
	public void AgentTitle(String AgentTitle) {
		this.AgentTitle.clear();
		this.AgentTitle.sendKeys(AgentTitle);
	}
	
	public void phone(String phone) {
		this.phone.clear(); 
		this.phone.sendKeys(phone); 
	}
	
	public void SaveChangesButton() {
		this.SaveChangesButton.click(); 
	}
	
	//change password methods
	public void ChangePasswordButton() {
		this.ChangePasswordButton.click();
	}
	
	public void CurrentPassword(String CurrentPassword) {
		this.CurrentPassword.sendKeys(CurrentPassword);
	}
	
	public void NewPass(String NewPass) {
		this.NewPass.sendKeys(NewPass);
	}
	
	public void ConfirmPass(String ConfirmPass) {
		this.ConfirmPass.sendKeys(ConfirmPass);
	}
	
	public void SaveChangesButton1() {
		this.SaveChangesButton1.click(); 
	}
	

}


