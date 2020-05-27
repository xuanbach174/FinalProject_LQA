package com.project.pages;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Base {
	String accountName = "selepracticel.4@gmail.com";
	String password = "123123abc";
	public String nameprod;
	public String result_search;
	public WebDriver driver;
	public boolean isElementPresent (By by) {
		try {
			driver.findElement(by);
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public void getTextOfProd (By by) {
		String textSugg1 = driver.findElement(by).getText();
		
		String regex = "> (\\w+.+)*";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(textSugg1);
		if (match.find() == true) {
			nameprod = match.group(1);
		}
	}
	
	public void getNumberOfProd (By by) {
		String result_notif = driver.findElement(by).getText();
		String regex = "(\\d+)";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(result_notif);
		if (match.find() == true) {
			result_search = match.group(1);
		}
	}
	
	public boolean isElementValue (By by) {
		String priceinfo = driver.findElement(by).getText();
		String regex = "(\\d+.\\d*)";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(priceinfo);
		if (match.find() == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getNumberFromString (String string) {
		
		String regex = "(\\d+.\\d*)";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(string);
		if (match.find() == true) {
			return match.group(0);
		}
		else {
			return"khong co gia cho san pham";
		}
	}
	
	public String getTextOfBy (String by) {
		String textSugg1 = by;
		String xpath = null;
		String regex = "(\\(//.*])";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(textSugg1);
		if (match.find() == true) {
			 xpath = match.group(1);
		}
		return xpath;
	}
	public static long registerTime;
	public static void iwait(long timer) {
		try {
			System.out.println("wait in " + timer + " milisecs");
			Thread.sleep(timer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	public void click(WebElement webElement, long timer) {
		System.out.println("click");
		webElement.click();
		iwait(timer);
	}
	public void type(String keysToSend, WebElement webElement) {
		System.out.println("type " + keysToSend + " into " + webElement);
		webElement.sendKeys(keysToSend);
	}
	
}
