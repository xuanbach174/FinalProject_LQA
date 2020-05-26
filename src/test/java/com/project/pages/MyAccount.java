package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccount extends Base {
	public By NOTIFICATION_ACC = By.xpath("//p[@class =\"info-account\"]");
	By HOME_BUTTON = By.xpath("//*[@title=\"Home\"]");
	public MyAccount (WebDriver driver) {
		this.driver = driver;
	}
	
	public Homepage goHomePage () {
		driver.findElement(HOME_BUTTON).click();
		return new Homepage(driver);
	}
	
}
