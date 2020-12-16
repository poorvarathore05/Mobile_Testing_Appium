package PageObject;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FormPage {

	private AndroidDriver<AndroidElement> driver;

	public FormPage(AndroidDriver<AndroidElement> driver){
		
		this.driver = driver;
	}
	
	public WebElement getDropDown() {
		return dropDown;
	}

	public WebElement getFindCountry() {
		return findCountry;
	}

	public WebElement getNameField() {
		return nameField;
	}

	public WebElement getRadioButtonOption() {
		return radioButtonOption;
	}

	public WebElement getLogin() {
		return login;
	}

	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement dropDown;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Australia']")
	private WebElement findCountry;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement radioButtonOption;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement login;
	

	
		
}
