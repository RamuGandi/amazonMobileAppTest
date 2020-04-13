package wrappers;

import java.net.MalformedURLException;

public interface Wrappers {
	
	public void invokeApp() throws MalformedURLException;
	public void enterByID(String ID, String value);
	public void clickByID(String ID);
	public void clickByXPath(String xPath);
	public void verifyByID(String ID);
	public String verifyAndStoreByID(String ID);
	public void clickByAccessibilityID(String ID);
	public String verifyAndStoreByPath(String xpath);
	public void compareValues(String actual, String expected);
}
