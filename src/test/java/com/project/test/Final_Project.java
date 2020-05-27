package com.project.test;

import org.testng.annotations.Test;

import com.project.data.DataTest;
import com.project.pages.AccountCreationPage;
import com.project.pages.AddPage;
import com.project.pages.ContactNotifPage;
import com.project.pages.ContactPage;
import com.project.pages.DataTestDTO;
import com.project.pages.GooglePage;
import com.project.pages.Homepage;
import com.project.pages.Login;
import com.project.pages.MyAccount;
import com.project.pages.PaymentMethodPage;
import com.project.pages.ProductDetail;
import com.project.pages.SearchResultPage;
import com.project.pages.ShipPage;
import com.project.pages.ShoppingCart;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

public class Final_Project {
	WebDriver driver;
	public static HSSFWorkbook workbook;
	public static HSSFSheet worksheet;
	public static DataFormatter formatter = new DataFormatter();

	@DataProvider(name = "multipleTest")
	public Object[] ReadVariant(Method mtd) throws IOException {

		DataTest datatest = new DataTest();
		List<DataTestDTO> listDataTestDTO = new ArrayList<DataTestDTO>();
		// Data cho TC1
		if (mtd.getName().equalsIgnoreCase("createInvalidEmail")) {
			listDataTestDTO = datatest.readExcelTC1();
		}
		// Data cho TC2
		else if (mtd.getName().equalsIgnoreCase("creatEmail")) {
			listDataTestDTO = datatest.readExcelTC2();
		}

		// Transfer Data tu ArrayList to Array and return (no edit)
		DataTestDTO[] arrayDataTestDTO = new DataTestDTO[listDataTestDTO.size()];
		for (int index = 0; index < listDataTestDTO.size(); index++) {
			arrayDataTestDTO[index] = listDataTestDTO.get(index);
		}
		return arrayDataTestDTO;
	}

//TC01
	@Test(enabled = true, dataProvider = "multipleTest")
	public void createInvalidEmail(DataTestDTO dataTestDTO) {
		String webLink = "http://automationpractice.com/";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		Login login = home.clickLoginButton();
		login.createAccount(dataTestDTO);
		assert (login.isElementPresent(login.notification));
	}

	// TC02
	@Test(enabled = true, dataProvider = "multipleTest")
	public void creatEmail(DataTestDTO dataTestDTO) {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		Login login = home.clickLoginButton();
		AccountCreationPage creationPage = login.createAccount(dataTestDTO);
		MyAccount myAccount = creationPage.actionFillData(dataTestDTO);
		assert (myAccount.isElementPresent(myAccount.NOTIFICATION_ACC));
	}

	// TC03
	@Test(enabled = false)
	public void checkEmail() {
		String webLink = "https://www.guru99.com/selenium-tutorial.html";
		driver.get(webLink);
		driver.findElement(By.xpath("//*[@id=\"providence-field-email\"]")).sendKeys("selepracticel@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"providence-FieldsElementButton--C9kKY3pT6XjY37cXvyoM\"]")).click();
		driver.get("https://mail.google.com/mail/u/0/#inbox");
		driver.findElement(By.xpath(
				"//li[@class =\"h-c-header__nav-li g-mail-nav-links\"]/a[@class =\"h-c-header__nav-li-link \" and @ga-event-action = \"sign in\"]"))
				.click();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));
		driver.findElement(By.xpath("//*[@id=\"identifierId\"]")).sendKeys("selepracticel@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("lqa12345");
		driver.findElement(By.xpath("//*[@id=\"passwordNext\"]")).click();
		try {
			driver.findElement(By.xpath(
					"//div[@class=\"Cp\"]//tr[1]//div[1]//span[@email = \"support@guru99.com\" and @data-hovercard-id =\"support@guru99.com\"]"));
			assert (true);
		} catch (Exception e) {
			// TODO: handle exception
			assert (false);
		}
	}

	// TC04
	@Test(enabled = false)
	public void checkEmailP2() {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		ContactPage contactPage = home.clickContactButton();
		ContactNotifPage conNotPage = contactPage.fillInfo();
		String actualNot = driver.findElement(conNotPage.NOTIF_MESS).getText();
		assertEquals(actualNot, conNotPage.messExpect);
	}

	// TC05
	@Test(enabled = false)
	public void checkSearchPlaceHolder() throws IOException, InterruptedException {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		System.out.println("Check place holder with text");
		assert (home.checkPlaceHolderText());
		System.out.println("Check place holder NO text");
		assert (home.checkPlaceHolderNoText());
	}

	// TC06.1
	@Test(enabled = false)
	public void searchkeyworkSuggestion() {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		home.checkKeywordSuggestion();
		assert (home.isElementPresent(home.SUGG_SEARCH_BOX));
	}

//TC06.2
	@Test(enabled = false)
	public void compareSuggAndActual() {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		home.checkKeywordSuggestion();
		List<WebElement> suggcount = driver.findElements(By.xpath("//*[@class = \"ac_results\"]//li"));
		int count = suggcount.size();
		for (int i = 0; i < suggcount.size(); i++) {
			By searchbox = By.xpath("//*[@class = \"ac_results\"]//li[" + (i + 1) + "]");
			home.getTextOfProd(searchbox);
//		System.out.println(home.nameprod);
			driver.findElement(searchbox).click();
			WebElement productname = driver.findElement(By.xpath("//*[@itemprop=\"name\"]"));
			String productnametext = productname.getText();
			assertEquals(productnametext, home.nameprod, "Sai o Suggestion thu: " + (i + 1));
			driver.navigate().back();
			home.checkKeywordSuggestion();
		}
	}

//TC06.3
	@Test(enabled = false)
	public void searchResult() throws InterruptedException {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		SearchResultPage searchprod = home.searchProduction();
		Thread.sleep(2000);
		searchprod.countprod();
		int total_prod = searchprod.searchresultcount.size();
		searchprod.getNumberOfProd(searchprod.NUMBER_OF_PROD_NOTIF);
		String number_result = searchprod.result_search;
		assertEquals(number_result, Integer.toString(total_prod));
	}

//TC06.4 
	@Test(enabled = false)
	public void checkPriceInfo() throws InterruptedException {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		SearchResultPage searchprod = home.searchProduction();
		Thread.sleep(2000);
		searchprod.countPrice();
		for (int i = 0; i < searchprod.searchresultcount.size(); i++) {
			By price = By
					.xpath("(//div[@class=\"right-block\"]//span[@class=\"price product-price\"])[" + (i + 1) + "]");
			assert (searchprod.isElementValue(price));
		}

	}

//TC07  
	@Test(enabled = false)
	public void checkNoSearchResult() {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		SearchResultPage searchprod = home.searchNoResult();
		searchprod.textNoResult();
		String actual_result = searchprod.textnoresult;
		String expect_result = "No results were found for your search " + "\"" + home.keywordsearch_noresult + "\"";

		assertEquals(actual_result, expect_result);
	}

	// TC08
	@Test(enabled = false)
	public void checkTotalPrice() throws InterruptedException {

		// Step 1: Open site
		String webLink = "http://automationpractice.com";
		driver.get(webLink);

		// Step 2: Login
		Homepage home = new Homepage(driver);
		Login login = home.clickLoginButton();
		MyAccount myacc = login.login();
		myacc.goHomePage();
		Thread.sleep(2000);

		// Step 3: Purchase
//	  System.out.println(home.addCart());
		Double total_price = home.addCart();

		// step 4 checkout
		ShoppingCart shopcart = home.checkOut();

		// Check if total payment if correct
		assertEquals(Double.toString(total_price), shopcart.getValuePrice());

		AddPage addpage = shopcart.checkOut();
		ShipPage shippage = addpage.clickCheckOut();
		PaymentMethodPage payment = shippage.clickCheckOut();
		payment.choosePayment();
//	  System.out.println(payment.getNotifAfterPay());

		// Check if mesage is displayed.
		assertEquals(payment.getNotifAfterPay(), "Your order on My Store is complete.");

	}

	// TC09
	@Test(enabled = true)
	public void interrupCheckOut() throws InterruptedException {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		Login login = home.clickLoginButton();
		MyAccount myacc = login.login();
		myacc.goHomePage();
		Thread.sleep(2000);
		// Add san pham muon mua
		home.addCart2();
		// Move den page checkout
		ShoppingCart shopcart = home.checkOut();

		// Thay doi so luong san pham + xoa san pham
		shopcart.changeNumberOfProducts();
		Thread.sleep(3000);
		// Tinh gia tien tren so san pham
		double totalunitprice = shopcart.checkTotalUnitPrice();
		// Check total price is correct
		double totoproductsprice = Double.valueOf(shopcart.getValuePrice());
		assertEquals(totalunitprice, totoproductsprice);
		// Thuc hien cac buoc de checkout
		AddPage addpage = shopcart.checkOut();
		ShipPage shippage = addpage.clickCheckOut();
		shippage.clickCheckOutWithoutClickCheckBox();
		String notifbox = shippage.checkNotif();
		assertEquals(notifbox, "You must agree to the terms of service before continuing.");
		shippage.clickClose();
		PaymentMethodPage payment = shippage.clickCheckOut();
		payment.choosePayment();
		assertEquals(payment.getNotifAfterPay(), "Your order on My Store is complete.");
	}

	// TC10
	@Test
	public void purchaseReductionProduct() {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		Login login = home.clickLoginButton();
		MyAccount myacc = login.login();
		myacc.goHomePage();
		// Tìm sản phẩm nào đang giảm giá 20%
		By Reductionproduct = home.foundReductionProduct("-20%");
		// Add sản phẩm đang giảm giá vào giỏ
		home.addCartReductionProduct(Reductionproduct);
		// Thực hiện các bước checkout
		ShoppingCart shopcart = home.checkOut();
		AddPage addpage = shopcart.checkOut();
		ShipPage shippage = addpage.clickCheckOut();
		PaymentMethodPage payment = shippage.clickCheckOut();
		payment.choosePayment();
		// Kiểm tra xem checkout đã thành công hay chưa
		assertEquals(payment.getNotifAfterPay(), "Your order on My Store is complete.");
	}

	// TC11 khong getlocation duoc
	@Test(enabled = true)

	public void testCase11_1() throws InterruptedException {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		ProductDetail productdetail = home.viewDetailProduct();

		productdetail.getWidthHeightProfileImg();
		System.out.println(productdetail.widthprofileimg);

		productdetail.clickProfilePictureOfProduct();
		productdetail.getWidthHeightLargeImg();
		productdetail.getLocationLargeImg();
		productdetail.getLocationProductName();
		System.out.println(productdetail.widthlargeimg);
		System.out.println(productdetail.largepictureproductY);
		System.out.println(productdetail.productname);
	}

	// TC12
	@Test(enabled = true)
	public void sharetoTwitter() {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		// Vào viewdetail 1 sản pham bat ki
		ProductDetail productdetail = home.viewDetailProduct();
		// Click vao nut twitter
		productdetail.clickTwitterButton();
		// Dang nhap va share, dong thoi share lan hai de xac dinh da share thanh cong
		String notification_when_click_submit = productdetail.fillInfoToLoginTwitter();
		// Share thanh cong thi khi click Share lan 2 se thong bao loi
		String expect_message = "You have already sent this Tweet.";
		assertEquals(notification_when_click_submit, expect_message);
	}

	// TC14
	// TODO
	@Test (priority = 2)
	public void sendToFriend() {
		System.out.println("Send to friend");
		String webLink = "http://automationpractice.com/index.php";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		ProductDetail productdetail = home.viewDetailProductDA();
		productdetail.clickSendFriendButton();
		productdetail.inputDataSendFriendEmail("Lqa213465", "anhld.lqa@gmail.com");
		productdetail.clickSendEmailButton();
		boolean flag;
		if(productdetail.sendFriendEmailSuccessfully()) {
			GooglePage googlePage = new GooglePage(driver);
			flag = googlePage.verifyInEmailWithPin("anhld.lqa@gmail.com", "Lqa213465");
		}
		else {
			flag = false;
		}
		assertTrue(flag, "Can not send friend email");
		System.out.println();
	}
	//TC13
	@Test (priority = 1)
	public void writeAComment() {
		System.out.println("Write a comment");
		String webLink = "http://automationpractice.com/index.php";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		
		Login login = home.clickLoginButton();
		MyAccount myacc = login.login();
		myacc.goHomePage();
		
		ProductDetail productdetail = home.viewDetailProductDA();
		productdetail.clickWriteAReview();
		productdetail.inputDataWriteAReview("title", "content");
		productdetail.clickSendReview();
		boolean flag;
		flag = productdetail.writeAReviewSuccessfully();
		assertTrue(flag, "Can send review");
		//
		//
		//
	}

	@BeforeMethod
	public void beforeMethod() {
//		System.setProperty("webdriver.chrome.driver", "D:\\Study\\Selenium\\driver\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\webdriver\\chromedriver.exe");
		driver = new ChromeDriver();
//		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
	}
}
