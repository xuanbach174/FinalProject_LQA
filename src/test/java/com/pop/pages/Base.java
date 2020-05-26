package com.pop.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Base {
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
	
	
}
