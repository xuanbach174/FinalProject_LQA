package com.selenium.test;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class shadow_DOM {

	WebDriver driver;
	String driverPath = "F:/Jars/chromedriver/";

	@BeforeTest
	public void setUp() {
		System.out.println("Opening chrome browser");
		System.setProperty("webdriver.chrome.driver", "E://Learning//Selenium//Webdriver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Test
	public void DragnDrop()			 		
    {						
         driver.get("http://demo.guru99.com/test/drag_drop.html");					
         
		//Element which needs to drag.    		
        	WebElement From=driver.findElement(By.xpath("//*[@id='credit2']/a"));	
         
         //Element on which need to drop.		
         WebElement To=driver.findElement(By.xpath("//*[@id='bank']/li"));					
         		
         //Using Action class for drag and drop.		
         Actions act=new Actions(driver);					

	//Dragged and dropped.		
         act.dragAndDrop(From, To).build().perform();		
	}		
	@Test
	public void testGetText_FromShadowDOMElements() throws InterruptedException {
		driver.get("chrome://downloads/");
		
//		https://www.seleniumeasy.com/test/generate-file-to-download-demo.html
		System.out.println("Open Chrome downloads");
		((JavascriptExecutor)driver).executeScript("window.open('https://www.seleniumeasy.com/test/generate-file-to-download-demo.html','_blank');");
		Thread.sleep(1000);
		System.out.println(driver.getTitle());
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.findElement(By.xpath("//textarea[@id=\"textbox\"]")).sendKeys("aaaaaaaaaaaaaa");
		driver.findElement(By.xpath("//button[@id=\"create\"]")).click();
		driver.findElement(By.xpath("//*[@download=\"easyinfo.txt\"]")).click(); 
		
		System.out.println("Validate downloads page header text");
		
		
		driver.switchTo().window(tabs.get(0));
		WebElement root1 = driver.findElement(By.tagName("downloads-manager"));

                //Get shadow root element
		WebElement shadowRoot1 = expandRootElement(root1);

		WebElement root2 = shadowRoot1.findElement(By.cssSelector("div#mainContainer"));
		WebElement shadowRoot2 = expandRootElement(root2);
		System.out.println("Access Download-items");
		
		WebElement root3 = shadowRoot2.findElement(By.cssSelector("a#file-link"));
		WebElement shadowRoot3 = expandRootElement(root3);
		System.out.println("Access file link");


	}

	//Returns webelement
	public WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver)
.executeScript("return arguments[0].shadowRoot",element);
		return ele;
	}

	@AfterTest
	public void tearDown() {
//		driver.quit();
	}
}