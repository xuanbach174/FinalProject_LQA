package com.selenium.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.safari.SafariDriver.WindowType;


public class SecondTest {
	WebDriver driver;
//	WebDriver driver2;
		public void isElementPresent (By locator,long timeout) throws InterruptedException {
			int i = 0;
			WebElement w = driver.findElement(locator);
//			System.out.println(w);
			boolean display = w.isDisplayed();
			while(i < timeout/500) {
				i = i + 1;
				display = w.isDisplayed();
				if (display) {
					System.out.println("Dialog van hien");
					Thread.sleep(1000);
//					System.out.println(display);
				}
				else {
					System.out.println("Dialog da closeeeeeeeeeeeeeeeeeee");
					break;
				}
			}
		}
	@Test (enabled = false)
	public void checkAlert() throws InterruptedException {
		String webLink = "https://www.seleniumeasy.com/test/bootstrap-progress-bar-dialog-demo.html";
		driver.get(webLink);
		String locator = "//*[@style = \"margin:0;\"]";
//		driver.findElement(By.xpath("//*[@class=\"btn btn-primary\"]")).click();
		driver.findElement(By.xpath("//*[@class = \"btn btn-warning\"]")).click();
		
		long timeout = 10000;
		Thread.sleep(1000);//1 secs
//		System.out.println(driver.findElement(By.xpath("//*[@style = \"margin:0;\"]")).isDisplayed());
		WebElement dialog = driver.findElement(By.xpath("//*[@style = \"margin:0;\"]"));
		String dialogText = dialog.getText();
		if (dialogText.equals("Hello Mr. Alert !")) {
			System.out.println("Dung la hien Alert Hello Mr.Alert");
		}
		else {
			System.out.println("Khong phai hien Alert ma chung ta can. Alert dang hien la: "+ dialogText);
		}
		
		isElementPresent(By.xpath(locator), timeout);
		//ntn nhi????
		//neu cai tren la 1s thi se bi sai
	}
	
  @Test (enabled = false)
  public void download () throws InterruptedException {
	  String webLink = "https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html";
	  driver.get(webLink);
	  WebDriverWait wait = new WebDriverWait(driver, 150);
	  driver.findElement(By.xpath("//*[@class=\"btn btn-block btn-primary\"]")).click();
	  WebElement percentageText = driver.findElement(By.xpath("//*[@class = \"percenttext\"]"));
	  Thread.sleep(1000);
	  wait.until(ExpectedConditions.textToBePresentInElement(percentageText, "100%"));
	  System.out.println("Da download xong: "+percentageText.getText());
  }
  
  @Test (enabled = true)
  public void iPage () throws InterruptedException {
	  String webLink1 = "https://www.seleniumeasy.com/test/";
	  String webLink2 = "https://vnexpress.net/";
	  driver.get(webLink1);
	//Delete file in folder before check downloaded files
	  DeleteAllFiles delFile = new DeleteAllFiles();
	  delFile.delete();
	  
	  driver.findElement(By.xpath("//*[@class=\"dropdown-toggle\"and contains(text(),\"Alerts & Modals\")]")).click();
	  driver.findElement(By.xpath("//ul[@class=\"dropdown-menu\"]//*[@href=\"./generate-file-to-download-demo.html\"]")).click();
	  Thread.sleep(1000);
	  ((JavascriptExecutor)driver).executeScript("window.open('https://vnexpress.net','_blank');");
	  Thread.sleep(2000);
	  System.out.println(driver.getTitle());
	  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	  driver.switchTo().window(tabs.get(1));
	  System.out.println(driver.getTitle());
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//article[@class=\"item-news full-thumb article-topstory\"]//h1//a[@data-medium=\"Item-1\"]")).click();
	  Thread.sleep(2000);
	  String content = driver.findElement(By.xpath("//*[@class=\"Normal\"][1]")).getText();
	  
	  //Download a file
	  driver.switchTo().window(tabs.get(0));
	  System.out.println(driver.getTitle());
	  driver.findElement(By.xpath("//textarea[@id=\"textbox\"]")).sendKeys(content);
	  driver.findElement(By.xpath("//button[@id=\"create\"]")).click();
	  driver.findElement(By.xpath("//*[@download=\"easyinfo.txt\"]")).click();
	  
	  //Check file is downloaded or not ?
	  delFile.isExist();
	  
	  
  }
  
 
@BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "E://Learning//Selenium//Webdriver//chromedriver.exe");
	  String downloadFilepath = "C:\\Users\\bachnx\\Documents\\Downloads\\changeDownloadPath";
	  HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	  chromePrefs.put("profile.default_content_settings.popups", 0);
	  chromePrefs.put("download.default_directory", downloadFilepath);
	  ChromeOptions options = new ChromeOptions();
	  options.setExperimentalOption("prefs", chromePrefs);
	  DesiredCapabilities cap = DesiredCapabilities.chrome();
	  cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	  cap.setCapability(ChromeOptions.CAPABILITY, options);
	  driver = new ChromeDriver(cap);
	  
//	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
  }

  @AfterMethod
  public void afterMethod() {
  }

}
