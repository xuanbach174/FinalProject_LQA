package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactNotifPage extends Base{
	public By NOTIF_MESS = By.xpath("//p[@class=\"alert alert-success\"]");
	public String messExpect = "Your message has been successfully sent to our team.";
	public ContactNotifPage(WebDriver driver) {
		this.driver =driver;
	}
}
