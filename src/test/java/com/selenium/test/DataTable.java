package com.selenium.test;

import org.testng.annotations.Test;

import com.pop.pages.Homepage;
import com.pop.pages.Login;
import com.pop.pages.MyAccount;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class DataTable {
	WebDriver driver;
	public static HSSFWorkbook workbook;
	public static HSSFSheet worksheet;
	public static DataFormatter formatter = new DataFormatter();
	public static String file_location = System.getProperty("user.dir") + "/Akeneo_product";
	static String SheetName = "Sheet1";
	public String Res;
	public int DataSet = -1;

	@DataProvider()
	public static Object[][] ReadVariant() throws IOException {
		String file_location = "E:\\Learning\\Selenium\\Data\\checkData.xls";
		FileInputStream fileInputStream = new FileInputStream(file_location); // Excel sheet file location get mentioned
																				// here
		workbook = new HSSFWorkbook(fileInputStream); // get my workbook
		worksheet = workbook.getSheet(SheetName);// get my sheet from workbook
		HSSFRow Row = worksheet.getRow(1); // get my Row which start from 0
		int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
		System.out.println(RowNum);
		int ColNum = Row.getLastCellNum(); // get last ColNum
		System.out.println(ColNum);
		Object Data[][] = new Object[RowNum][ColNum]; // pass my count data in array

		for (int i = 1; i < RowNum; i++) // Loop work for Rows
		{
			HSSFRow row = worksheet.getRow(i);

			for (int j = 0; j < ColNum; j++) // Loop work for colNum
			{
				if (row == null)
					Data[i][j] = "";
				else {
					HSSFCell cell = row.getCell(j);
					if (cell == null)
						Data[i][j] = ""; // if it get Null value it pass no data
					else {
						String value = formatter.formatCellValue(cell);
						Data[i][j] = value; // This formatter get my all values as string i.e integer, float all type
											// data value
					}
				}
			}
		}
		return Data;
	}

	@Test(dataProvider = "ReadVariant")
	public void f(String location, String job, String sl) {
		if (job == null) {
		} else {
			String Weblink = "https://www.seleniumeasy.com/test/table-data-download-demo.html";
			driver.get(Weblink);
			System.out.println(location + " " + job + " " + sl);
			List<WebElement> result = driver.findElements(
					By.xpath("//*[contains(text(),'" + job + "')]/../*[contains(text(),'" + location + "')]"));
			System.out.println("Ket qua tren web la:" + result.size());
			if (result.size() == Integer.parseInt(sl)) {
				System.out.println("Ket qua giong voi tren web");
			} else {
				System.out.println("Ket qua KO giong voi tren web");
			}
		}

	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "E://Learning//Selenium//Webdriver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
	}

}
