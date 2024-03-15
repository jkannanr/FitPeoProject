package com.flipkartTestNG;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.CharMatcher;

public class AutomationUsingTestNG {
	
//  Declaring driver
	static WebDriver driver;

//	 Using @Before Annotations(TestNG)-To execute the  openBrowser Method first
	@BeforeClass
	public void openBrowser() {

//		 Instantiate a ChromeDriver
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Anitha\\Downloads\\chromedriver-win64\\chromedriver.exe");

		driver = new ChromeDriver();
		
//	 Fetching a web page using Get Command
		driver.get("https://www.flipkart.com");
//		The maximize command is used to maximize the browser window
		driver.manage().window().maximize();

	}

//	 Using @After Annotations(TestNG)-To execute the closeBrowser Method at Last
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

//	 Using @Test Annotations(TestNG) with Priority-To execute the methods in order
	@Test(priority = 1)
	public void OpenFlipKartWebsite() throws InterruptedException {



		System.out.println("HomePage Loading");
// The thread sleeps for the 2000 milliseconds, which is 1 sec 
		Thread.sleep(2000);
		
//	    To verify Home page load successfully 
//		To get the Title of the Browser Page
		String ActualTitle = driver.getTitle();
//		To Pring the Title of the Browser Page
		System.out.println(ActualTitle);
//		Declaring and Instantiating expected Title of the Browser Page
		String ExpectedTitle = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
//		Using Assertions (J-unit) to verify Expected and Actual Title or the browser page
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		System.out.println("HomePage Loaded Successfully");

	}

//	 Using @Test Annotations(TestNG) with Priority-To execute the methods in order
	@Test(priority = 2)
	public void ToSearchProduct() throws AWTException {

//		 To enter "Laptop" in search box and press Enter Key in Keyboard

        // Locate the search bar element by Xpath  and passing values using sendkeys() function  
		WebElement txtSearchBar = driver.findElement(By.xpath("//*[@name='q']"));
		txtSearchBar.sendKeys("laptop");

//		Using Robot class Keyboard events to press ENTER key
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

//	 Using @Test Annotations(TestNG) with Priority-To execute the methods in order
	@Test(priority = 3)
	public void ToSelectLaptopAndAddtoCart() throws InterruptedException {
		
//		 To Select the desired Laptop 
		 // Locate the Price High to Low element box by Xpath  and check it using click() function 
		WebElement selectPriceHighToLow = driver.findElement(By.xpath("//div[text()='Price -- High to Low']"));
		selectPriceHighToLow.click();

		// The thread sleeps for the 2000 milliseconds, which is 1 sec 
		Thread.sleep(2000);
		
		// Locate the product Price element by Xpath  and check it using GetText() function 
		String price1 = driver.findElement(By.xpath("//div[text()='₹4,49,990']")).getText();
		System.out.println("The Selected Product Price is = Rs." + price1);

		// Using CharMatcher utility class in Guava to Remove Special Characters (commas, Rupee Symbol)from a String
		String selectedProductPrice = CharMatcher.inRange('0', '9').retainFrom(price1);
		System.out.println(selectedProductPrice);

		// Locate the product element by Xpath  and check it using click() function 
		WebElement selectTheProduct = driver.findElement(By.xpath(
				"//div[text()='₹4,49,990']/ancestor::div[@class='_3pLy-c row']//*[contains(text(),'Lenovo Legion ')]"));
		selectTheProduct.click();

		// To switch from parent window to child window(s)
		String mainPage = driver.getWindowHandle();
		Set<String> allPages = driver.getWindowHandles();
		for (String page : allPages) {
			if (!page.equals(mainPage)) {
				driver.switchTo().window(page);
				break;
			}
		}
		// The thread sleeps for the 2000 milliseconds, which is 2 sec 
		Thread.sleep(2000);
		
		// Locate the Add to Cart element by Xpath  and check it using click() function 
		WebElement btnAddToCart = driver.findElement(By.xpath("//button[text()='Add to cart']"));
		btnAddToCart.click();

	}

//	 Using @Test Annotations(TestNG) with Priority-To execute the methods in order
	@Test(priority = 4)
	public void ToVerifyCart() throws InterruptedException {

		// The thread sleeps for the 2000 milliseconds, which is 2 sec 
		Thread.sleep(2000);
		// Locate the price element by Xpath  and check it using getText() function 
		String price2 = driver.findElement(By.xpath("//span[text()='4,49,990']")).getText();
		System.out.println("The Product Price Added in Cart is = Rs." + price2);
		// Using CharMatcher utility class in Guava to Remove Special Characters (commas, Rupee Symbol)from a String
		String addedProductPrice = CharMatcher.inRange('0', '9').retainFrom(price2);
		System.out.println(addedProductPrice);

//		Using Assertions (J-unit) to verify Expected and Actual Product Prices 
		Assert.assertEquals(" Verify Laptop added to cart correctly", "449990", addedProductPrice);
		System.out.println("Laptop added to cart correctly ");

		// Locate the Place order Button element by Xpath  and check it using click() function
		WebElement btnPlaceOrder = driver.findElement(By.xpath("//span[text()='Place Order']"));
		btnPlaceOrder.click();

	}

//	 Using @Test Annotations(TestNG) with Priority-To execute the methods in order
	@Test(priority = 5)
	public void ToLoginWebsite() throws InterruptedException {

//		Enter email ID/Mobile number
	
		 // Locate the Enter Email element by Xpath  and passing values using sendkeys() function  
		WebElement txtEnterEmail = driver.findElement(By.xpath("//span[contains(text(),'Enter Email/Mobile number')]/ancestor::div[@class='IiD88i GtCYSF']//input[@type='text']"));
		txtEnterEmail.sendKeys("jkannan2008@gmail.com");

		// Locate the COntinue button element by Xpath  and check it using click() function  
		WebElement btnContinue = driver.findElement(By.xpath("//span[contains(text(),'CONTINUE')]/parent::button"));
		btnContinue.click();

		 // Locate the Enter OTP  element by Xpath  and passing values using sendkeys() function  
		WebElement txtEnterOtp = driver.findElement(By.xpath("//span[contains(text(),'Enter OTP')]"));
		txtEnterOtp.sendKeys("254878");

		// Locate the Login button element by Xpath  and check it using click() function 
		WebElement btnLogin = driver.findElement(By.xpath("//span[contains(text(),'Login')]/parent::button"));
		btnLogin.click();

		// The thread sleeps for the 2000 milliseconds, which is 2 sec 
		Thread.sleep(2000);
		
		// Locate the Username element by Xpath  and check it using getText() function 
		String txtLoginSuccessful = driver.findElement(By.xpath("//span[contains(text(),'Jawahar kannan ')]")).getText();
		System.out.println(txtLoginSuccessful);
		
//		Using Assertions (J-unit) to verify Login successful
		Assert.assertEquals(" Verify Login successful", "Jawahar kannan ", txtLoginSuccessful);
		System.out.println("LoggedIn Successfully ");
	}

//	 Using @Test Annotations(TestNG) with Priority-To execute the methods in order
	@Test(priority = 6)
	public void ToEnterAddress() {

//		 Address verification	//input[@name='pincode'][@tabindex='3']

		// Locate the AddAddress element by Xpath  and check it using click() function 
		WebElement btnAddAddress = driver.findElement(By.xpath("//div[contains(text(),'Add a new address')]"));
		btnAddAddress.click();

		 // Locate theName  element by Xpath  and passing values using sendkeys() function  
		WebElement txtName = driver.findElement(By.xpath("//input[@name='name'][@tabindex='1']"));
		txtName.sendKeys("Jawahar");
		
		 // Locate the Phone No element by Xpath  and passing values using sendkeys() function  
		WebElement txtPhoneNo = driver.findElement(By.xpath("//input[@name='phone'][@tabindex='2'"));
		txtPhoneNo.sendKeys("8973944202");
		
		 // Locate the Pincode  element by Xpath  and passing values using sendkeys() function  
		WebElement txtPincode = driver.findElement(By.xpath("//input[@name='pincode'][@tabindex='3']"));
		txtPincode.sendKeys("600097");
		
		 // Locate the Locality  element by Xpath  and passing values using sendkeys() function  
		WebElement txtLocality = driver.findElement(By.xpath("//input[@name='addressLine2'][@tabindex='4']"));
		txtLocality.sendKeys("Thoraipakkam");
		
		 // Locate the Address element by Xpath  and passing values using sendkeys() function  
		WebElement txtAddress = driver.findElement(By.xpath("//textarea[@name='addressLine1'][@tabindex='5']"));
		txtAddress.sendKeys("F2, Sapphire Block, Grand GEms Apatement");

		
		 // Locate the CIty Name  element by Xpath  and passing values using sendkeys() function  
		WebElement txtCityName = driver.findElement(By.xpath("//input[@name='city'][@tabindex='6']"));
		txtCityName.sendKeys("Chennai");

		 // Locate the State  element by Xpath  and check it using Select by Visible Text() function  
		Select txtSelectState = new Select(driver.findElement(By.xpath("//select[@name='state'][@tabindex='7']")));
		txtSelectState.selectByVisibleText("Tamil Nadu");

		 // Locate the Radio Button element by Xpath  and check it using click() function  
		WebElement btnRadioButton = driver
				.findElement(By.xpath("//input[@type='radio'][@name='locationTypeTag'] [contains(text(),'Home')]"));
		btnRadioButton.click();

		 // Locate the Save and Deliver  element by Xpath  and check it using click() function  
		WebElement btnSaveAndDeliver = driver.findElement(By.xpath("button"));
		btnSaveAndDeliver.click();

	}

//	 Using @Test Annotations(TestNG) with Priority-To execute the methods in order
	@Test(priority = 7)
	public void ToEnterPayment() {

		 // Locate the card No  element by Xpath  and passing values using sendkeys() function  
		WebElement txtCardNo = driver.findElement(By.id("cardNumber"));
		txtCardNo.sendKeys("4587 5689 5478 4578");

		 // Locate the DropDown  element by Xpath  and check it using Select visible by text() function  
		Select dropdownDate = new Select(driver.findElement(By.name("month")));
		dropdownDate.selectByVisibleText("5");
		
		 // Locate the DropDown  element by Xpath  and check it using Select visible by text() function  
		Select dropdownYear = new Select(driver.findElement(By.name("year")));
		dropdownYear.selectByVisibleText("26");

		 // Locate the CVV No  element by Xpath  and and passing values using sendkeys() function   
		WebElement TxtCvv = driver.findElement(By.id("TxtCvv"));
		TxtCvv.sendKeys("587");

		 // Locate the Pay Button  element by Xpath  and check it using click() function  
		WebElement btnPay = driver.findElement(By.xpath("button"));
		btnPay.click();

	}



}
