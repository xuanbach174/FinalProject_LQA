package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentMethodPage extends Base {
	By PAY_BY_CHECK =  By.xpath("//*[@id=\"HOOK_PAYMENT\"]//*[@title= \"Pay by check.\"]");
	By CONFIRM_PAY = By.xpath("//*[@id=\"cart_navigation\"]/button");
	By PAYMENT_NOTIF = By.xpath("//*[@id=\"center_column\"]/*[@class=\"alert alert-success\"]");
	public PaymentMethodPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public void choosePayment () {
		driver.findElement(PAY_BY_CHECK).click();
		driver.findElement(CONFIRM_PAY).click();
	}
	
	public String getNotifAfterPay () {
		String notif = driver.findElement(PAYMENT_NOTIF).getText();
		return notif;
	}
}
