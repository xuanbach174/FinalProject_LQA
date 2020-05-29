package com.project.test;

import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.beust.jcommander.Parameter;
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
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.DataFormatter;

public class Final_Project {
	WebDriver driver;
	String nodeURL;
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
	@Test(groups = "CreateAccount", dataProvider = "multipleTest")
	public void createInvalidEmail(DataTestDTO dataTestDTO) {
		String webLink = "http://automationpractice.com/";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		Login login = home.clickLoginButton();
		login.createAccount(dataTestDTO);
		assert (login.isElementPresent(login.notification));
	}

	// TC02
	@Test(groups = "CreateAccount", dataProvider = "multipleTest")
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
	@Test(groups = "Newsletter")
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
	@Test(groups = "Contact Us")
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
	@Test(groups = "Search")
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
	@Test(groups = "Search")
	public void searchkeyworkSuggestion() {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		home.checkKeywordSuggestion();
		assert (home.isElementPresent(home.SUGG_SEARCH_BOX));
	}

//TC06.2
	@Test(groups = "Search")
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
	@Test(groups = "Search")
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
	@Test(groups = "Search")
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
	@Test(groups = "Search")
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
	@Test(groups = "Shopping")
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
	@Test(groups = "Shopping")
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
	@Test(groups = "Shopping")
	public void purchaseReductionProduct() {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		Login login = home.clickLoginButton();
		MyAccount myacc = login.login();
		myacc.goHomePage();
		// Tìm sản phẩm nào đang giảm giá 20%
		By Reductionproduct = home.foundReductionProduct("-20%");
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
	@Test(groups = "Item Detail")
	public void testCase11_1() throws InterruptedException {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		ProductDetail productdetail = home.viewDetailProduct();

		productdetail.clickOnBigImage();
		Thread.sleep(2000);
		String itemName = productdetail.getItemName();
		String actual = productdetail.getZoomedItemName();
		assert (productdetail.isElementPresent(productdetail.ZOOMED_PICTURE));
		assert (productdetail.isElementPresent(productdetail.ZOOMED_ITEM_NAME));
		assertEquals(actual, itemName);
	}

	@Test(groups = { "Item Detail" })
	public void TC11_2() throws InterruptedException {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		ProductDetail productdetail = home.viewDetailProduct();

		productdetail.clickOnBigImage();
		productdetail.closeLargeImage();
		productdetail.clickViewLargeButton();
		Thread.sleep(1000);
		String itemName = productdetail.getItemName();
		assert (productdetail.isElementPresent(productdetail.ZOOMED_PICTURE));
		assert (productdetail.isElementPresent(productdetail.ZOOMED_ITEM_NAME));
		assertEquals(productdetail.getZoomedItemName(), itemName);
	}

	@Test(groups = { "Item Detail" })
	public void TC11_3() {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		ProductDetail productdetail = home.viewDetailProduct();

		productdetail.changeQuantity("0");
		productdetail.clickAddToCart();
		productdetail.isElementPresent(productdetail.MSG_NULL_QUANTITY);
	}

	@Test(groups = { "Item Detail" })
	public void TC11_4() {
		String webLink = "http://automationpractice.com";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		ProductDetail productdetail = home.viewDetailProduct();

		productdetail.changeQuantity("1");
		String itemName = productdetail.getItemName();
		productdetail.clickAddToCart();
		assert (productdetail.isElementPresent(productdetail.MSG_ADD_SUCCESFUL));
		productdetail.closeSuccesfulMessage();
		ShoppingCart shopcart = productdetail.viewCart();
		String cartItemName = shopcart.getItemName();
		int cartItemQuantity = shopcart.getItemQuantity();
		assertEquals(cartItemName, itemName);
		assertEquals(cartItemQuantity, 1);
	}

	// TC12
	@Test(groups = "Item Detail")
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

	// TC13
	@Test(groups = "Item Detail")
	public void writeAComment() {
		System.out.println("Write a comment");
		String webLink = "http://automationpractice.com/index.php";
		driver.get(webLink);
		Homepage home = new Homepage(driver);

		Login login = home.clickLoginButton();
		MyAccount myacc = login.login();
		myacc.goHomePage();

		ProductDetail productdetail = home.viewDetailProduct();
		productdetail.clickWriteAReview();
		productdetail.inputDataWriteAReview("title", "content");
		productdetail.clickSendReview();
		boolean flag;
		flag = productdetail.writeAReviewSuccessfully();
		assertTrue(flag, "Can send review");
	}

	// TC14
	// TODO
	@Test(groups = "Item Detail")
	public void sendToFriend() {
		System.out.println("Send to friend");
		String webLink = "http://automationpractice.com/index.php";
		driver.get(webLink);
		Homepage home = new Homepage(driver);
		ProductDetail productdetail = home.viewDetailProduct();
		productdetail.clickSendFriendButton();
		productdetail.inputDataSendFriendEmail("Lqa213465", "anhld.lqa@gmail.com");
		productdetail.clickSendEmailButton();
		boolean flag;
		if (productdetail.sendFriendEmailSuccessfully()) {
			GooglePage googlePage = new GooglePage(driver);
			flag = googlePage.verifyInEmailWithPin("anhld.lqa@gmail.com", "Lqa213465");
		} else {
			flag = false;
		}
		assertTrue(flag, "Can not send friend email");
		System.out.println();
	}

	@Test
    public void sampleTest() {
        driver.get("http://demo.guru99.com/test/guru99home/");
        

        if (driver.getPageSource().contains("MOBILE TESTING")) {
            Assert.assertTrue(true, "Mobile Testing Link Found");
        } else {
            Assert.assertTrue(false, "Failed: Link not found");
        }

    }
	
//	@BeforeMethod(groups = { "Item Detail", "CreateAccount", "Newsletter", "Contact Us", "Search", "Shopping" })
	@BeforeMethod(groups = {"Item Detail", "Shopping"})
	@Parameters(value= {"node","version", "browser"})
	public void beforeMethod(String node, String version, String browser) throws MalformedURLException {
//		System.setProperty("webdriver.chrome.driver", "D:\\Study\\Selenium\\driver\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver", "./webdriver/chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
//		nodeURL = "http://10.10.31.80:19610/wd/hub";
		DesiredCapabilities capability;
		if (browser.equalsIgnoreCase("firefox")) {
	        capability = DesiredCapabilities.firefox();
	        capability.setBrowserName("firefox");
	        capability.setPlatform(Platform.WINDOWS);
	        capability.setVersion(version);
	        driver = new RemoteWebDriver(new URL(node), capability);
		} else if (browser.equalsIgnoreCase("chrome")) {
	        capability = DesiredCapabilities.chrome();
	        capability.setBrowserName("chrome");
	        capability.setPlatform(Platform.WINDOWS);
//	        capability.setVersion(version);
	        driver = new RemoteWebDriver(new URL(node), capability);
		}
	}

//	@AfterMethod(groups = { "Item Detail", "CreateAccount", "Newsletter", "Contact Us", "Search", "Shopping" })
	@AfterMethod (groups = {"Item Detail", "Shopping"})
	public void afterMethod() {
		driver.quit();
	}
}
