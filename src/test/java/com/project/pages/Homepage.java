package com.project.pages;

import static org.testng.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Homepage extends Base {
	By LOGIN_BUTTON = By.xpath("//*[@class=\"login\"]");
	By CONTACT_US_BUTTON = By.xpath("//*[@id=\"contact-link\"]");
	By SEARCH_BOX  = By.xpath("//*[@id=\"search_query_top\"]");
	By FOOTER_PAGES = By.xpath("//*[@id=\"editorial_block_center\"]");
	By SEARCH_BUTTON = By.xpath("//*[@id=\"searchbox\"]/button");
	By ADDCART_BUTTON = By.xpath("//*[@id=\"homefeatured\"]//*[@title=\"Add to cart\"]");
	By PROCEED_CHECKOUT_BUTT = By.xpath("//*[@title=\"Proceed to checkout\"]");
	By PRICE_REDUCTION_PRODUCT = By.xpath("//*[@class=\"product_list grid row homefeatured tab-pane active\"]/li//div[@class=\"right-block\"]//*[@class=\"price-percent-reduction\"]");
	String ADDCART = "(//*[@id=\"homefeatured\"]//*[@title=\"Add to cart\"])[{param}]";


	
	List<WebElement> addcart;
	public By SUGG_SEARCH_BOX = By.xpath("//*[@class = \"ac_results\"]");
	String keywordsearch = "Dress";
	public String keywordsearch_noresult = "DreSSSsss";
	
	
	public Homepage (WebDriver driver) {
		this.driver = driver;
	}
	public Login clickLoginButton () {
		driver.findElement(LOGIN_BUTTON).click();
		return new Login(driver);
	}
	
	public ContactPage clickContactButton () {
		driver.findElement(CONTACT_US_BUTTON).click();
		return new ContactPage(driver);
	}
	
	public void typeText () {
		driver.findElement(SEARCH_BOX).click();
		driver.findElement(SEARCH_BOX).sendKeys("Dress");
	}
	
	public boolean checkPlaceHolderText() throws IOException, InterruptedException {
		WebElement searchBoxWithText = driver.findElement(SEARCH_BOX);
		searchBoxWithText.sendKeys("Dress");
		driver.findElement(FOOTER_PAGES).click();
		Screenshot searchBoxWithTextSS = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver,searchBoxWithText);
		ImageIO.write(searchBoxWithTextSS.getImage(),"jpg",new File ("C:\\Users\\bachnx\\Documents\\Downloads\\screenshot\\searchBoxWithText.jpg"));
		
		BufferedImage originalIMG = ImageIO.read(new File("C:\\Users\\bachnx\\Documents\\Downloads\\screenshot\\searchBoxWithTextOriginal.jpg"));
		BufferedImage actualImage = ImageIO.read(new File("C:\\Users\\bachnx\\Documents\\Downloads\\screenshot\\searchBoxWithText.jpg"));
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, originalIMG);
        if (diff.hasDiff() == true) {
//            System.out.println("Images are Different");
            return false;
        } else {
//            System.out.println("Images are same");
            return true;
        }
	}
	
	public boolean checkPlaceHolderNoText() throws IOException {
		WebElement searchBox = driver.findElement(SEARCH_BOX);
		searchBox.clear();
		Screenshot searchBoxSS = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver,searchBox);
		ImageIO.write(searchBoxSS.getImage(),"jpg",new File ("C:\\Users\\bachnx\\Documents\\Downloads\\screenshot\\searchBox.jpg"));
		BufferedImage actualImage = ImageIO.read(new File("C:\\Users\\bachnx\\Documents\\Downloads\\screenshot\\searchBox.jpg"));
		BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\bachnx\\Documents\\Downloads\\screenshot\\searchBoxOriginal.jpg"));
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, originalImage);
		
        
        if (diff.hasDiff()) {
//            System.out.println("Images are Different");
            
        } else {
//            System.out.println("Images are same");
            
        }
        
        return (!diff.hasDiff());
	}
	
	public void checkKeywordSuggestion () {
		WebElement searchBox = driver.findElement(SEARCH_BOX);
		searchBox.sendKeys("Dress");
		searchBox.click();
		searchBox.click();
	}
	
	public SearchResultPage searchProduction () {
		WebElement searchBox = driver.findElement(SEARCH_BOX);
		searchBox.sendKeys(keywordsearch);
		driver.findElement(SEARCH_BUTTON).click();
		return new SearchResultPage(driver);
	}
	
	public SearchResultPage searchNoResult () {
		WebElement searchBox = driver.findElement(SEARCH_BOX);
		searchBox.sendKeys(keywordsearch_noresult);
		driver.findElement(SEARCH_BUTTON).click();
		return new SearchResultPage(driver);
	}
	
	public double addCart() {
		Actions act = new Actions(driver);
		
		addcart = driver.findElements(ADDCART_BUTTON);
		int maxproduct = addcart.size();
		double total = 0;
		Random rd = new Random();
		int random_pick = rd.nextInt(maxproduct);
		for (int i = 0; i < ( random_pick + 1); i++) {
//			By ADDCART = By.xpath("(//*[@id=\"homefeatured\"]//*[@title=\"Add to cart\"])["+(i+1)+"]");
			
			By pricepath = By.xpath("(//div[@class=\"right-block\"]//span[@class=\"price product-price\"])["+(i+1)+"]");
			String price =  driver.findElement(pricepath).getText();
			Base base = new Base();
			String temp = base.getNumberFromString(price);
			double pricevalue = Double.parseDouble(temp);
//			System.out.println(pricevalue);
			total = total + pricevalue;
			act.moveToElement(driver.findElement(By.xpath("(//*[@id=\"homefeatured\"]/li)["+(i+1)+"]"))).perform();
			driver.findElement(By.xpath(ADDCART.replace("{param}", Integer.toString(i+1)))).click();
			if (i == random_pick) {
				
				break;
			}
			else {
				driver.findElement(By.xpath("//*[@class=\"continue btn btn-default button exclusive-medium\"]")).click();
			}
		}
		return total;
	}
	
	public void addCart2() {
		Actions act = new Actions(driver);
		
		addcart = driver.findElements(ADDCART_BUTTON);
		int maxproduct = addcart.size();
		Random rd = new Random();
		int random_pick = rd.nextInt(maxproduct);
		for (int i = 0; i < ( random_pick + 1); i++) {
			int id_prod = rd.nextInt(maxproduct)+1;
			By ADDCART = By.xpath("(//*[@id=\"homefeatured\"]//*[@title=\"Add to cart\"])["+(id_prod)+"]");
			act.moveToElement(driver.findElement(By.xpath("(//*[@id=\"homefeatured\"]/li)["+(id_prod)+"]"))).perform();
			driver.findElement(ADDCART).click();
			if (i == random_pick) {
				
				break;
			}
			else {
				driver.findElement(By.xpath("//*[@class=\"continue btn btn-default button exclusive-medium\"]")).click();
			}
		}
	}
	
	
	
	public ShoppingCart checkOut() {
		driver.findElement(PROCEED_CHECKOUT_BUTT).click();
		return new ShoppingCart(driver);
	}
	
	public By foundReductionProduct (String percentage) {
		List<WebElement> listreductionproduct = driver.findElements(PRICE_REDUCTION_PRODUCT);
//		WebElement reductionproduct = null;
		By Reductionproduct = null;
		for (int i = 0; i < listreductionproduct.size(); i++) {
			String reductionpercen = driver.findElement(By.xpath("(//*[@class=\"product_list grid row homefeatured tab-pane active\"]/li//div[@class=\"right-block\"]//*[@class=\"price-percent-reduction\"])["+(i+1)+ "]")).getText();
			if (percentage.equals(reductionpercen)) {
				Reductionproduct = By.xpath("(//*[@class=\"product_list grid row homefeatured tab-pane active\"]/li//div[@class=\"right-block\"]//*[@class=\"price-percent-reduction\"])["+(i+1)+"]");
				
			}
		}
		return Reductionproduct;
	}
	
	public void addCartReductionProduct (By REDUCTIONPRODUCT) {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(REDUCTIONPRODUCT)).perform();
		
//		driver.findElement(abc).click();
		String xpath = super.getTextOfBy(REDUCTIONPRODUCT+"abc");
		driver.findElement(By.xpath(xpath+"/..//..//*[@class=\"button-container\"]/*[@title=\"Add to cart\"]")).click();
	}
	
	public ProductDetail viewDetailProduct() {
		addcart = driver.findElements(ADDCART_BUTTON);
		int maxproduct = addcart.size();
		Random rd = new Random();
		int random_pick = rd.nextInt(maxproduct) + 1;
		driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li["+ random_pick +"]")).click();
		return new ProductDetail(driver);
	}
	public ProductDetail viewDetailProductDA() {
		addcart = driver.findElements(ADDCART_BUTTON);
		int maxproduct = addcart.size();
		Random rd = new Random();
		int random_pick = rd.nextInt(maxproduct) + 1;
		//*[@id="homefeatured"]/li[1]/div/div[1]/div/a[1]/img
		driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li["+ random_pick +"]/div/div[1]/div/a[1]/img")).click();
		return new ProductDetail(driver);
	}
	
}
