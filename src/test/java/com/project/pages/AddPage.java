package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddPage extends Base {
	By PROCEED_CHECKOUT =  By.xpath("//button[@type=\"submit\" and @class=\"button btn btn-default button-medium\" ]");
	public AddPage (WebDriver driver) {
		this.driver = driver;
	}
	public ShipPage clickCheckOut () {
		driver.findElement(PROCEED_CHECKOUT).click();
		return new ShipPage(driver);
	}
	
}
