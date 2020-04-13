package pages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class HomePage extends GenericWrappers{
	
	public HomePage(AndroidDriver<WebElement> driver){
		this.driver = driver;
	}
	public HomePage searchProduct(String product){
		enterByID("Home.SearchBar.Id",product+"\n");
		return this;		
	}
	public ProductDetailsPage selectProductFromList(){
		clickByXPath("Home.Product.Xpath");
		return new ProductDetailsPage(driver);
	}
}
