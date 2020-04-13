package pages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class ProductDetailsPage extends GenericWrappers {


	public ProductDetailsPage(AndroidDriver<WebElement> driver){
		this.driver = driver;
	}
	public ProductDetailsPage verifyAndStoreProductNameInPDP(){
		productNameInPDP = verifyAndStoreByID("PDP.ProductName.Id");
		return this;
	}
	public ProductDetailsPage verifyAndStoreProductDescriptionInPDP(){
		productDescriptionInPDP=verifyAndStoreByID("PDP.ProductDesc.Id");
		return this;
	}
	public ProductDetailsPage verifyAndStoreProductPriceInPDP(){
		productPriceInPDP=verifyAndStoreByID("PDP.ProductPrice.Id");
		return this;
	}
	public ProductDetailsPage clickAddToCart(){
		clickByID("PDP.AddtoCart.Id");
		return this;
	}
	public CartPage clickOnCartIcon(){
		clickByAccessibilityID("PDP.Cart.AccId");
		return new CartPage(driver);
	}
}
