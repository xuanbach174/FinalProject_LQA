package com.project.pages;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends Base{
	public List<WebElement> searchresultcount;
	public By NUMBER_OF_PROD_NOTIF = By.xpath("//*[@class=\"heading-counter\"]");
	public String textnoresult;
	By NOTIF_NO_RESULT = By.xpath("//*[@class=\"alert alert-warning\"]");
	By PRODUCT_PRICE_INFO = By.xpath("//div[@class=\"right-block\"]//span[@class=\"price product-price\"]");
	public SearchResultPage (WebDriver driver) {
		this.driver = driver;
	}
	
	public void countprod() {
		searchresultcount = driver.findElements(By.xpath("//*[@id=\"center_column\"]/ul[@class=\"product_list grid row\"]/li"));
	}
	
	public void countPrice() {
		searchresultcount = driver.findElements(PRODUCT_PRICE_INFO);
	}
	
	public void textNoResult() {
		textnoresult = driver.findElement(NOTIF_NO_RESULT).getText();
	}
}
