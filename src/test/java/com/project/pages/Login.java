package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends Base {
	
	public By notification = By.xpath("//*[@class=\"form_content clearfix\"]//*[contains(text(),\"Invalid email address.\")]");
	By username = By.xpath("//*[@class=\"form-group\"]/*[@id=\"email_create\"]");
	By createButton = By.xpath("//*[@id=\"SubmitCreate\"]");
	By EMAIL_LOGIN = By.xpath("//*[@id=\"email\"]");
	By PASS_LOGIN = By.xpath("//*[@id=\"passwd\"]");
	By LOGIN_BUTTON = By.xpath("//*[@id=\"SubmitLogin\"]");
	public Login (WebDriver driver) {
		this.driver = driver;
	}
	public AccountCreationPage createAccount (DataTestDTO datatest) {
		driver.findElement(username).sendKeys(datatest.getEmail());
		driver.findElement(createButton).click();
		return new AccountCreationPage(driver);
	}
	
	public MyAccount login () {
		driver.findElement(EMAIL_LOGIN).sendKeys(accountName);
		driver.findElement(PASS_LOGIN).sendKeys(password);
		driver.findElement(LOGIN_BUTTON).click();
		return new MyAccount(driver);
	}
	
	
}
