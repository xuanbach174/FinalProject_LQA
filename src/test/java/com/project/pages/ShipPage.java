package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShipPage extends Base{
	By PROCEED_CHECKOUT =  By.xpath("//*[@id=\"form\"]//*[@type=\"submit\"]");
	By AGREE_CHECKBOX = By.xpath("//*[@id=\"cgv\"]");
	By NOTIFICATION_BOX = By.xpath("//p[@class=\"fancybox-error\"]");
	By CLOSE_ICON = By.xpath("//*[@title=\"Close\"]");
	public ShipPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public PaymentMethodPage clickCheckOut () {
		driver.findElement(AGREE_CHECKBOX).click();
		driver.findElement(PROCEED_CHECKOUT).click();
		return new PaymentMethodPage(driver);
	}
	
	public void clickCheckOutWithoutClickCheckBox () {
		driver.findElement(PROCEED_CHECKOUT).click();
	}
	
	public String checkNotif() {
		String notif = driver.findElement(NOTIFICATION_BOX).getText();
		return notif;
	}
	
	public void clickClose() {
		driver.findElement(CLOSE_ICON).click();
	}
}
