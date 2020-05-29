package com.project.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetail extends Base{
	
	public By MAIN_PICTURE = By.xpath("//img[@id=\"bigpic\"]");
	public By ITEM_NAME = By.xpath("//div[contains(@class,\"pb-center-column\")]/h1[@itemprop=\"name\"]");
	public By ZOOMED_PICTURE = By.xpath("//*[@class=\"fancybox-inner\"]/img");
	public By ZOOMED_ITEM_NAME = By.xpath("//*[@class=\"child\"]");
	public By BTN_VIEW_LARGE = By.xpath("//*[contains(@class,\"span_link\")]");
	public By BTN_CLOSE_LARGE_IMAGE = By.xpath("//*[contains(@class,\"fancybox-close\")]");
	public By INP_QUANTITY = By.xpath("//*[@id=\"quantity_wanted\"]");
	public By BTN_ADD_TO_CART = By.xpath("//*[@id=\"add_to_cart\"]/button");
	public By MSG_NULL_QUANTITY = By.xpath("//*[@class=\"fancybox-error\"]");
	public By MSG_ADD_SUCCESFUL = By.xpath("//*[@id=\"layer_cart\"]/div[@class=\"clearfix\"]");
	public By BTN_CLOSE_SUCCESSFUL_MESSAGE = By.xpath("//*[@class=\"cross\"]");
	public By BTN_VIEW_CART = By.xpath("//*[@title=\"View my shopping cart\"]");
	
	
	//Twitter testcase
	By TWITTER_BUTTON = By.xpath("//*[@class = \"btn btn-default btn-twitter\"]");
	By TWITTER_EMAIL = By.xpath("//*[@id=\"username_or_email\"]");
	By TWITTER_PASS = By.xpath("//*[@id=\"password\"]");
	By LOGIN_TWITTER_BUTTON = By.xpath("//*[@type=\"submit\"]");	
	By SUBMIT_SHARE_BUTTON = By.xpath("//*[@class=\"button selected submit\"]");
	By NOTIF_TWITTER = 	By.xpath("//*[@id=\"post-error\"]");
	By SEND_FRIEND_BUTTON = By.id("send_friend_button");
	By FRIEND_NAME = By.id("friend_name");
	By FRIEND_EMAIL = By.id("friend_email");
	By SEND_EMAIL_BUTTON = By.id("sendEmail");
	By MESS_SEND_FRIEND_EMAIL = By.xpath("//p[contains(text(),'Your e-mail has been sent successfully')]");
	By WRITE_A_REVIEW = By.xpath("//a[contains(text(),'Write a review')]");
	By REVIEW_TITLE = By.id("comment_title");
	By REVIEW_CONTENT = By.id("content");
	By SEND_REVIEW_BUTTON = By.id("submitNewMessage");
	By MESS_WRITE_A_REVIEW = By.xpath("//*[@id=\"product\"]/div[2]/div/div/div/p[contains(text(),'Your comment has been added and will be available once approved by a moderator')]");
	//*[@id="comment_title"]
	//*[@id="content"]
	//*[@id="submitNewMessage"]
	
	//*[@id="send_friend_button"]
	//*[@id="friend_name"]
	//*[@id="friend_email"]
	//*[@id="sendEmail"]
	
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
	
	public String getItemName() {
		return driver.findElement(ITEM_NAME).getText();
	}
	
	public String getZoomedItemName() {
		return driver.findElement(ZOOMED_ITEM_NAME).getText();
	}
	
	public void clickOnBigImage() {
		driver.findElement(MAIN_PICTURE).click();
	}
	
	public void clickViewLargeButton() {
		driver.findElement(BTN_VIEW_LARGE).click();
	}
	
	public void closeLargeImage() {
		driver.findElement(BTN_CLOSE_LARGE_IMAGE).click();
	}
	
	public void changeQuantity(String quantity) {
		driver.findElement(INP_QUANTITY).clear();
		driver.findElement(INP_QUANTITY).sendKeys(quantity);
	}
	
	public void clickAddToCart() {
		driver.findElement(BTN_ADD_TO_CART).click();
	}
	
	public void closeSuccesfulMessage() {
		driver.findElement(BTN_CLOSE_SUCCESSFUL_MESSAGE).click();
	}
	
	//checkout -> payment
	public ShoppingCart viewCart() {
		driver.findElement(BTN_VIEW_CART).click();
		return new ShoppingCart(driver);
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
	//TODO
	public void clickSendFriendButton() {
		driver.findElement(SEND_FRIEND_BUTTON).click();
	}
	public void setFriendName(String friendName) {
		driver.findElement(FRIEND_NAME).sendKeys(friendName);
	}
	public void setFriendEmail(String friendEmail) {
		driver.findElement(FRIEND_EMAIL).sendKeys(friendEmail);
	}
	public void clickSendEmailButton() {
		driver.findElement(SEND_EMAIL_BUTTON).click();
	}
	
	public boolean sendFriendEmailSuccessfully() {
		return isElementPresent(MESS_SEND_FRIEND_EMAIL);
	}
	
	public void inputDataSendFriendEmail(String name, String email){
		setFriendName(name);
		setFriendEmail(email);
	}
	
	public void clickWriteAReview() {
		driver.findElement(WRITE_A_REVIEW).click();
	}
	
	public void setReviewTitle(String title) {
		driver.findElement(REVIEW_TITLE).sendKeys(title);
	}
	
	public void setReviewContent(String content) {
		driver.findElement(REVIEW_CONTENT).sendKeys(content);
	}
	public void inputDataWriteAReview(String title, String content){
		setReviewTitle(title);
		setReviewContent(content);
	}
	public void clickSendReview() {
		driver.findElement(SEND_REVIEW_BUTTON).click();
	}

	public boolean writeAReviewSuccessfully() {
		return isElementPresent(MESS_WRITE_A_REVIEW);
	}
	
	
}