package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AccountCreationPage extends Base{
	
	
	private By TITLE = By.xpath("//input[@id = \"id_gender1\"]");
	private By FIRSTNAME = By.id("customer_firstname");
	private By LASTNAME = By.id("customer_lastname");
	private By PASSWORD = By.xpath("//*[@type =\"password\"]");
	private By DAY_OF_BIRTH = By.xpath("//*[@id=\"days\"]");
	private By MONTH_OF_BIRTH = By.xpath("//*[@id=\"months\"]");
	private By YEAR_OF_BIRTH = By.xpath("//*[@id=\"years\"]");
	private By ADDRESS = By.xpath("//*[@id=\"address1\"]");
	private By CITY = By.xpath("//*[@id=\"city\"]");
	private By STATE = By.xpath("//*[@id=\"id_state\"]");
	private By ZIPCODE = By.xpath("//*[@id=\"postcode\"]");
	private By MOBILEPHONE = By.xpath("//*[@id=\"phone_mobile\"]");
	private By ALIAS = By.xpath("//*[@id=\"alias\"]");
	private By REGISTER_BUTTON = By.xpath("//*[@id=\"submitAccount\"]"); 
	
	public AccountCreationPage (WebDriver driver) {
		this.driver = driver;
	}
	public MyAccount actionFillData (DataTestDTO obj) {
		driver.findElement(TITLE).click();
		driver.findElement(FIRSTNAME).sendKeys(obj.getFirst_Name());
		driver.findElement(LASTNAME).sendKeys(obj.getLast_name());
		driver.findElement(PASSWORD).sendKeys(obj.getPass());
		Select selectDay = new Select (driver.findElement(DAY_OF_BIRTH));
		selectDay.selectByValue(obj.getDAY_OF_BIRTH());
		Select selectMonth = new Select (driver.findElement(MONTH_OF_BIRTH));
		selectMonth.selectByValue(obj.getMONTH_OF_BIRTH());
		Select selectYear = new Select (driver.findElement(YEAR_OF_BIRTH));
		selectYear.selectByValue(obj.getYEAR_OF_BIRTH());
		driver.findElement(ADDRESS).sendKeys(obj.getADDRESS());
		driver.findElement(CITY).sendKeys(obj.getCity());
		Select selectState = new Select (driver.findElement(STATE));
		selectState.selectByValue(obj.getState());
		driver.findElement(ZIPCODE).sendKeys(obj.getZIPCODE());
		driver.findElement(MOBILEPHONE).sendKeys(obj.getMOBILEPHONE());
		driver.findElement(ALIAS).sendKeys(obj.getALIAS());
		driver.findElement(REGISTER_BUTTON).click();
		return new MyAccount(driver);
	}
}