package com.pop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage extends Base {
	
	public By BTN_SIGNIN = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");
	public By LNK_BESTSELLER = By.xpath("//*[@id=\"home-page-tabs\"]/li[2]/a");
	
	public Homepage (WebDriver driver) {
		this.driver = driver;	
	}
	
	public Login clickSignin() {
		driver.findElement(BTN_SIGNIN).click();
		return new Login(driver);
	}
}
