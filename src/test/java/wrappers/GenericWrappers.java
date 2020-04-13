package wrappers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class GenericWrappers implements Wrappers {

	public AndroidDriver<WebElement> driver;
	protected static Properties prop;

	protected String productNameInPDP = null;
	protected String productDescriptionInPDP = null;
	protected String productPriceInPDP = null;

	protected String productNameInCartPage = null;
	protected String productDescriptionInCartPage = null;
	protected String productPriceInCartPage = null;

	public void invokeApp() throws MalformedURLException {

		DesiredCapabilities cap = new DesiredCapabilities();

		File targetApp = new File("C:\\Users\\Ramu\\Desktop\\Amazon_shopping.apk"); //APK file path 

		cap.setCapability("deviceName", "394ff933"); 
		cap.setCapability("platformName", "Android");
		cap.setCapability("app", targetApp.getAbsolutePath());
		cap.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		cap.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
		driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(30, java.util.concurrent.TimeUnit.SECONDS);

		/*Screen Orientation check*/
		ScreenOrientation orientation = driver.getOrientation();
		System.out.println("By Default: "+orientation.value());
		if(orientation.value().contains("landscape"))
		{
			driver.rotate(ScreenOrientation.PORTRAIT);
			System.out.println("Orientation Changed to: "+driver.getOrientation().value());
		}
	}

	public void enterByID(String ID, String value) {
		driver.findElementById(ID).clear();
		driver.findElementById(ID).sendKeys(value);
	}

	public void clickByID(String ID) {
		driver.findElementById(ID).click();
	}

	public void verifyByID(String iD) {
		driver.findElementById(iD).isSelected();

	}

	public void clickByXPath(String xPath) {
		driver.findElementByXPath(xPath).click();		
	}

	public String verifyAndStoreByID(String ID) {
		driver.findElementById(ID).isDisplayed();
		String val = driver.findElementById(ID).getText();
		return val;
	}

	public String verifyAndStoreByPath(String xpath){
		driver.findElementByXPath(xpath).isDisplayed();
		String var = driver.findElementByXPath(xpath).getText();
		return var;
	}

	public void clickByAccessibilityID(String ID){
		AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
				ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId(ID)));
		searchElement.click();
	}

	public void compareValues(String actual, String expected) {
		try{
			Assert.assertEquals(actual, expected);
			System.out.println("Actual value: "+ actual +" is same as the Expected Value: "+ expected );
		} catch (Exception e){
			System.out.println("Actual value: "+ actual +" is not same as the Expected value: "+ expected);
		}
	}

	public void loadObjects() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./resources/object.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void takesnap(){
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String tm = new SimpleDateFormat("MMDD_mmsss").format(Calendar.getInstance().getTime());
		try {
			FileUtils.copyFile(src, new File("./Screenshots/"+"500266_"+tm+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeBrowser(){
		driver.close();
	}

}
