package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GooglePage extends Base {
//	@FindBy(xpath = "//div[@class='h-c-header__cta']//a[contains(text(),'Sign in')]")
	@FindBy(xpath = "//div[@class='h-c-header__cta']//li[2]//a[contains(text(),'Sign in')]")
	WebElement signIn;
	@FindBy(id = "identifierId")
	WebElement email;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(id = "identifierNext")
	WebElement next;

	@FindBy(id = "passwordNext")
	WebElement passwordNext;

	By SIGN_IN = By.xpath("//div[@class='h-c-header__cta']//li[2]//a[contains(text(),'Sign in')]");
	By EMAIL = By.id("identifierId");
	By EMAIL_NEXT = By.id("identifierNext");
	By PASSWORD = By.xpath("//*[@id=\"password\"]//input");
	By PASSWORD_NEXT = By.xpath("//*[@id=\"passwordNext\"]/span/span");
	By WELCOME_MAIL = By.xpath("(//span[contains(text(),'automationpractice')])[2]");
	public GooglePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickSignIn() {
//		click(this.signIn, 2000);
//		driver.findElement();
		driver.findElement(SIGN_IN).click();
	}

	public void setEmail(String email) {
//		type(email, this.email);
		driver.findElement(EMAIL).sendKeys(email);;
	}

	public void setPassword(String password) {
//		type(password, this.password);
		driver.findElement(PASSWORD).sendKeys(password);;
	}

	public void clickEmailNext() {
//		click(this.next, 500);
		driver.findElement(EMAIL_NEXT).click();
	}

	public void clickPasswordNext() {
//		click(this.passwordNext, 500);
		driver.findElement(PASSWORD_NEXT).click();
	}
	public void selectWelcomeMail() {
		try {
//			waitNClick(this.welcomeMail);
			driver.findElement(WELCOME_MAIL).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	boolean isExistUnreadEmail() {
		registerTime = System.currentTimeMillis();
		iwait(2222);

//		List<WebElement> unreadMails = this.webDriver.findElements(By.xpath("//span[@class='bq3']"));
		List<WebElement> unreadMails = this.driver
				.findElements(By.xpath("//table/tbody/tr/td[9]/span/span[@class='bq3']"));
		List<String> unreadMailsTime = new ArrayList<String>();
		for (WebElement webElement : unreadMails) {
			try {
				char firstCharacter = webElement.getText().charAt(0);
				if (Character.isDigit(firstCharacter)) {
					unreadMailsTime.add(webElement.getText());
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		// no unread mail
		if (unreadMails.size() == 0) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		Date resultdate = new Date(registerTime);
//			System.out.println(sdf.format(resultdate));
		String registerTimeStr = sdf.format(resultdate);

		// registerTimeStr = "3:33 PM";
		for (String emailTime : unreadMailsTime) {
			// has new email
			if (emailTime.compareTo(registerTimeStr) >= 0) {
//				log("emailTime:" + emailTime + "-" + registerTimeStr + ":registerTimeStr");
				return true;
			}
		}
		// no new email
		return false;
	}
	public boolean verifyInEmailWithPin(String emailStr, String passwordStr) {
		boolean flag;
		((JavascriptExecutor) driver)
				.executeScript("window.open('https://www.google.com/intl/en/gmail/about/#','_blank');");
		ArrayList<String> tabs = new ArrayList<String>(this.driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		try {
			this.clickSignIn();
//			driver.findElement(By.xpath("//div[@class='h-c-header__cta']//li[2]//a[contains(text(),'Sign in')]")).click();
		}catch (Exception e) {
			e.getMessage();
		}

		ArrayList<String> tabs2 = new ArrayList<String>(this.driver.getWindowHandles());

		driver.switchTo().window(tabs2.get(2));

		this.setEmail(emailStr);
		this.clickEmailNext();
		this.setPassword(passwordStr);
		this.clickPasswordNext();
		iwait(2000);
		if (!isExistUnreadEmail()) {
			System.out.println("Not exist unread email");
			flag =  false;
			return flag;
		}
		try {
			iwait(2000);
			this.selectWelcomeMail();
			flag =  true;
		} catch (Exception e) {
			e.getMessage();
			flag =  false;
		}
		
		driver.switchTo().window(tabs2.get(0));
		System.out.println();
//		return new DashboardPageModel(driver);
		return flag;
	}
}
