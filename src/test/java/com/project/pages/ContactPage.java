package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactPage extends Base {
	By SUB_HEAD = By.xpath("//*[@id=\"id_contact\"]");
	By EMAIL_ADD = By.xpath("//*[@id=\"email\"]");
	By ORDER_FIELD = By.xpath("//*[@id=\"id_order\"]");
	By ATT_FILE = By.xpath("//*[@id=\"fileUpload\"]");
	By MESS_BOX = By.xpath("//*[@id=\"message\"]");
	By SEND = By.xpath("//*[@id=\"submitMessage\"]");
	
	
	public ContactPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public ContactNotifPage fillInfo () {
		Select select = new Select (driver.findElement(SUB_HEAD));
		select.selectByVisibleText("Customer service");
		driver.findElement(EMAIL_ADD).sendKeys(accountName);
		driver.findElement(ORDER_FIELD).sendKeys("ABCD123");
		driver.findElement(ATT_FILE).sendKeys("C:\\Users\\bachnx\\Desktop\\car_red_sports_car_142598_1920x1080.jpg");
		driver.findElement(MESS_BOX).sendKeys("aaaaaaaaaaaa");
		driver.findElement(SEND).click();
		return new ContactNotifPage(driver);
	}
	
}
