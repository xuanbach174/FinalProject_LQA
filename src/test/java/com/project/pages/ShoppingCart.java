package com.project.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCart extends Base {
	By PROCEED_CHECKOUT =  By.xpath("//*[@class=\"cart_navigation clearfix\"]/*[@title=\"Proceed to checkout\"]");
	By TOTAL_PRODUCTS_PRICE = By.xpath("//*[@id=\"total_product\"]");
	public ShoppingCart(WebDriver driver) {
		this.driver = driver;
	}
	
	public AddPage checkOut() {
		driver.findElement(PROCEED_CHECKOUT).click();
		return new AddPage(driver);
	}
	
	public String getValuePrice() {
		String total_price = driver.findElement(TOTAL_PRODUCTS_PRICE).getText();
		String value_of_total_price = super.getNumberFromString(total_price);
		return value_of_total_price;
	}
	
	public void changeNumberOfProducts () {
		List<WebElement> rowsofproducttable = driver.findElements(By.xpath("//tbody/tr"));
		int rownumberofproductable = rowsofproducttable.size();
		Random rd = new Random();
		int sanphambidoisoluong = rd.nextInt(rownumberofproductable)+1;
		WebElement numnerofproducts = driver.findElement(By.xpath("//tbody/tr["+sanphambidoisoluong+"]/td[@class=\"cart_quantity text-center\"]/*[@type=\"text\"]"));
		numnerofproducts.clear();
		numnerofproducts.sendKeys("3");
		int sanphambixoa = rd.nextInt(rownumberofproductable)+1;
		driver.findElement(By.xpath("//tbody/tr["+ sanphambixoa +"]//*[@class=\"icon-trash\"]")).click();
	}
	
	public double checkTotalUnitPrice () {
		List<WebElement> rowsofproducttable = driver.findElements(By.xpath("//tbody/tr"));
		int rownumberofproductable = rowsofproducttable.size();
		double totalunitprice = 0;
		for (int iRow = 0; iRow < rownumberofproductable; iRow++) {
			WebElement unitpriceElement = driver.findElement(By.xpath("//tbody/tr["+ (iRow + 1) +"]/td[4]/span[@class=\"price\"]/span[@class=\"price\"]"));
			double unitprice = Double.parseDouble(super.getNumberFromString(unitpriceElement.getText()));
			String quantityProduct = driver.findElement(By.xpath("//tbody/tr["+ (iRow + 1) +"]/td[@class =\"cart_quantity text-center\"]/*[@type=\"hidden\"]/../*[@value]")).getAttribute("value");
			int quantityproduct = Integer.parseInt(quantityProduct) ;
			totalunitprice = totalunitprice + (unitprice*quantityproduct);
		}
		return totalunitprice;
	}
	
	
}
