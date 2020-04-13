package eBayTest;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import resources.ExcelUtils;
import wrappers.GenericWrappers;

public class AppTest extends GenericWrappers{

	
	private static AndroidDriver<WebElement> driver;
	
	String user = "******@gmail.com";
	String pass = "*************";

	@BeforeTest
	public void beforeTest() throws MalformedURLException{
		loadObjects();		
		invokeApp();
	}

	@Test(priority = 0)
	public void loginToApp() 
	{	
		new LoginPage(driver)
		.verifyLoginRadioButtonIsSelected()
		.enterUsername(user)
		.clickContinue()
		.enterPassword(pass)
		.clickLogin();

	}

	@Test(dependsOnMethods = { "loginToApp" }, dataProvider="testData")
	public void searchProduct(String sNo, String product)
	{

		System.out.println("****Executing Test Number: " + sNo);
		new HomePage(driver)
		.searchProduct(product)
		.selectProductFromList();
	}

	@Test(dependsOnMethods = { "searchProduct" })
	public void getProductDetailsFromPDP()
	{
		new ProductDetailsPage(driver)
		.verifyAndStoreProductNameInPDP()
		.verifyAndStoreProductDescriptionInPDP()
		.verifyAndStoreProductPriceInPDP()
		.clickAddToCart()
		.clickOnCartIcon();
	}

	@Test(dependsOnMethods = { "getProductDetailsFromPDP" })
	public void getProductDetailsFromCartPage()
	{
		new CartPage(driver)
		.verifyAndStoreProductNameInCartPage()
		.verifyAndStoreProductDescInCartPage()
		.verifyAndStoreProductPriceInCartPage();
	}

	@Test(dependsOnMethods = { "getProductDetailsFromCartPage" })
	public void validateProductDetialsInCartPage()
	{
		compareValues(productNameInCartPage,productNameInPDP);
		compareValues(productDescriptionInCartPage,productDescriptionInPDP);
		compareValues(productPriceInCartPage,productPriceInPDP);
	
	}

	@AfterTest
	public void quit()
	{
		closeBrowser();
	}

	@DataProvider(name = "testData")
	public Object[][] testData()
	{    	
		ExcelUtils er = new ExcelUtils();
		Object data[][] = er.readData();
		return data;

	}   

}
