package PageObject;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CheckOutPage {
	
     private AndroidDriver<AndroidElement> driver;

	public CheckOutPage(AndroidDriver<AndroidElement> driver){
    	 this.driver = driver;
     }
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrice;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	public List<WebElement> getProductPrice(){
		return productPrice;
	}
	
	public WebElement getTotalAmount(){
		return totalAmount;
	}
}
