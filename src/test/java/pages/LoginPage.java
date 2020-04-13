package pages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class LoginPage extends GenericWrappers{

	public LoginPage(AndroidDriver<WebElement> driver){
		this.driver = driver;
	}
	public LoginPage verifyLoginRadioButtonIsSelected(){
		verifyByID("Login.LoginRadioButton.Id");
		return this;		
	}
	public LoginPage enterUsername(String userName){
		enterByID("Login.UserName.Id", userName);
		return this;
	}
	public LoginPage clickContinue(){
		clickByXPath("Login.Continue.Xpath");
		return this;
	}	
	public LoginPage enterPassword(String passWord){
		enterByID("Login.Password.Id", passWord);
		takesnap();
		return this;
	}
	public HomePage clickLogin(){
		clickByID("Login.LoginButton.Id");
		return new HomePage(driver);
	}
}
