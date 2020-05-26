package com.selenium.test;

import org.testng.annotations.Test;
import com.pop.pages.Base;
import com.pop.pages.Homepage;
import com.pop.pages.Login;
import com.pop.pages.MyAccount;

import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	WebDriver driver;
  @Test
  public void testLogin() {
	  Homepage home = new Homepage(driver);
	  Login login = home.clickSignin();
	  MyAccount myAcc = login.login();
	  home = myAcc.back2Homepage();
	  assert (home.isElementPresent(home.LNK_BESTSELLER));
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "E://Learning//Selenium//Webdriver//chromedriver.exe");
	  driver= new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("http://automationpractice.com/index.php");
  }

  @AfterMethod
  public void afterMethod() {
	  
  }

}
