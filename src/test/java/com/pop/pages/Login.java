package com.pop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends Base {
	public By TXT_EMAIL = By.id("email");
	public By TXT_PASSWORD = By.xpath("//*[@id=\"passwd\"]");
	public By BTN_SIGNIN = By.xpath("//*[@id=\"SubmitLogin\"]");
	
	
	public Login (WebDriver driver) {
		this.driver= driver;
	}
	
	public MyAccount login () {
		driver.findElement(TXT_EMAIL).sendKeys("abc@adcdcd.com");
		driver.findElement(TXT_PASSWORD).sendKeys("123123123");
		driver.findElement(BTN_SIGNIN).click();
		return new MyAccount (driver);
	}
	
}
