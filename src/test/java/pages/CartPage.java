package pages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class CartPage extends GenericWrappers{

	public CartPage(AndroidDriver<WebElement> driver){
		this.driver = driver;
	}
	public CartPage verifyAndStoreProductNameInCartPage(){
		productNameInCartPage = verifyAndStoreByPath("CartPage.ProductName.Xpath");
		return this;
	}
	public CartPage verifyAndStoreProductDescInCartPage(){
		productDescriptionInCartPage = verifyAndStoreByPath("CartPage.ProductDesc.Xpath");
		return this;
	}
	public CartPage verifyAndStoreProductPriceInCartPage(){
		productPriceInCartPage = verifyAndStoreByPath("CartPage.ProductPrice.Xpath");
		return this;
	}
}
