package com.pop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccount extends Base {
	public By LNK_HOME = By.xpath("//*[@title=\"Home\"]");
	
	public MyAccount (WebDriver driver) {
		this.driver = driver;
	}
	
	public Homepage back2Homepage () {
		driver.findElement(LNK_HOME).click();
		return new Homepage(driver);
	}
}
