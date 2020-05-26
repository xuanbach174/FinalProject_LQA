package com.project.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetail extends Base{
	By PROFILE_PICTURE_PRODUCT = By.xpath("//*[@id=\"bigpic\"]");
	By PROFILE_PICTURE_PRODUCT_LARGE = By.xpath("//*[@class=\"fancybox-wrap fancybox-desktop fancybox-type-image fancybox-opened\"]");
	By PROFILE_NAME = By.xpath("//*[@class=\"child\"]");
	By TWITTER_BUTTON = By.xpath("//*[@class = \"btn btn-default btn-twitter\"]");
	By TWITTER_EMAIL = By.xpath("//*[@id=\"username_or_email\"]");
	By TWITTER_PASS = By.xpath("//*[@id=\"password\"]");
	By LOGIN_TWITTER_BUTTON = By.xpath("//*[@type=\"submit\"]");	
	By SUBMIT_SHARE_BUTTON = By.xpath("//*[@class=\"button selected submit\"]");
	By NOTIF_TWITTER = 	By.xpath("//*[@id=\"post-error\"]");
	
	
	public int widthlargeimg;
	public int heightlargeimg;
	
	
	public int widthprofileimg;
	public int heightprofileimg;
	
	public int productnameinlargeimgY;
	public String productname;
	public int largepictureproductY;
	
	
	public ProductDetail(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickProfilePictureOfProduct () {
		driver.findElement(PROFILE_PICTURE_PRODUCT).click();
	}
	public void getWidthHeightLargeImg () {
		Dimension size = driver.findElement(PROFILE_PICTURE_PRODUCT_LARGE).getSize();
		widthlargeimg = size.getWidth();
		heightlargeimg = driver.findElement(PROFILE_PICTURE_PRODUCT_LARGE).getSize().getHeight();
		
	}
	
	public void getWidthHeightProfileImg () {
		widthprofileimg = driver.findElement(PROFILE_PICTURE_PRODUCT).getSize().getWidth();
		heightprofileimg = driver.findElement(PROFILE_PICTURE_PRODUCT).getSize().getHeight();
	}
	
	public void getLocationProductName() {
		productnameinlargeimgY = driver.findElement(PROFILE_NAME).getLocation().getY();
		productname = driver.findElement(PROFILE_NAME).getText();
	}
	
	
	public void getLocationLargeImg() {
		largepictureproductY = driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[1]/div/img")).getLocation().getY();
	}
	
	
	public void clickTwitterButton() {
		driver.findElement(TWITTER_BUTTON).click();
	}
	public String fillInfoToLoginTwitter() {
		String parentWindowID = driver.getWindowHandle();
		System.out.println(driver.getTitle());
		Set<String> handleIDs = driver.getWindowHandles();
		String twitterPopupHandle = null;
		for (String h:handleIDs) {
			if(!h.equals(parentWindowID)) {
				twitterPopupHandle = h;
				System.out.println(twitterPopupHandle);
			}
		}
		driver.switchTo().window(twitterPopupHandle);
		System.out.println(driver.getTitle());
		WebElement twitteremail = driver.findElement(TWITTER_EMAIL);
		twitteremail.sendKeys("xuanbach174@gmail.com");
		driver.findElement(TWITTER_PASS).sendKeys("bachdaica123");
		driver.findElement(LOGIN_TWITTER_BUTTON).click();
		driver.findElement(SUBMIT_SHARE_BUTTON).click();
		
		//Click Twitter button again and get text to confirm that message was shared 
		driver.switchTo().window(parentWindowID);
		ProductDetail product = new ProductDetail(driver);
		
		product.clickTwitterButton();
		driver.switchTo().window(twitterPopupHandle);
		
		
		 driver.findElement(SUBMIT_SHARE_BUTTON).click();
		 String notification_when_click_submit = driver.findElement(NOTIF_TWITTER).getText();
		return notification_when_click_submit;
	}
	
}