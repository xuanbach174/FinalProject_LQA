package com.study.testng;

import org.testng.annotations.Test;
import com.pop.pages.Base;
import com.pop.pages.Homepage;
import com.pop.pages.Login;
import com.pop.pages.MyAccount;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class ParallelTest {
	WebDriver driver;
  @Test
  public void testLogin() {
	  Homepage home = new Homepage(driver);
	  Login login = home.clickSignin();
	  MyAccount myAcc = login.login();
	  home = myAcc.back2Homepage();
	  assert (home.isElementPresent(home.LNK_BESTSELLER));
  }
  @Test
  
  
  @BeforeMethod
  @Parameters("browser")
  public void beforeMethod(String browser) {
	  if (browser.equalsIgnoreCase("Firefox")) {
		System.setProperty("webdriver.gecko.driver", "E://Learning//Selenium//Webdriver//geckodriver.exe");
		System.setProperty("webdriver.firefox.bin",
	              "C:\\Users\\bachnx\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();
		System.out.println("Run Firefox");
	}
	  else {
		  System.setProperty("webdriver.chrome.driver", "E://Learning//Selenium//Webdriver//chromedriver.exe");
		  driver= new ChromeDriver();
		  System.out.println("Run Chrome");
	}
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://automationpractice.com/index.php");
	  driver.manage().window().maximize();
  }

  @AfterMethod
  public void afterMethod() {
	  
  }

}
