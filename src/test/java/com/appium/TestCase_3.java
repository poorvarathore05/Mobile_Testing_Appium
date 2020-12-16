package com.appium;

import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObject.CheckOutPage;
import PageObject.FormPage;
import PageObject.Utilites;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

public class TestCase_3 extends BaseClass {

	@Test
	public void testMethod3() throws InterruptedException, IOException {

		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities("generalStoreApp");
		FormPage formPage = new FormPage(driver);
		formPage.getDropDown().click();
		formPage.getFindCountry().click();

		Utilites utilites = new Utilites(driver);
		utilites.scrollToText("Austrila");

		formPage.getNameField().sendKeys("Hello");
		formPage.getRadioButtonOption().click();
		formPage.getLogin().click();

		driver.findElements(By.xpath("//*[@text ='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text ='ADD TO CART']")).get(0).click();
		driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
		Thread.sleep(4000);
		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		double sum = 0;
		
		CheckOutPage checkOut = new CheckOutPage(driver);
		
		for (int i = 0; i < count; i++) {

			String amount = checkOut.getProductPrice().get(i)
					.getText();
			double productAmount = totalProductValue(amount);
			sum = sum + productAmount;
		}

		String totalAmount = checkOut.getTotalAmount().getText();
		totalAmount = totalAmount.substring(1);

		double totalValue = Double.parseDouble(totalAmount);

		System.out.println("Total value of products :" + totalValue);

		Assert.assertEquals(sum, totalValue);

		// Mobile Gestures

		driver.findElement(
				By.xpath("//*[@text ='Send me e-mails on discounts related to selected products in future']")).click();
		WebElement termscond = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		TouchAction touchaction = new TouchAction(driver);
		touchaction.longPress(longPressOptions().withElement(element(termscond)).withDuration(ofSeconds(3))).release()
				.perform();
		driver.findElement(By.id("android:id/button1")).click();
		
		service.stop();

	}

	public double totalProductValue(String amount) {

		amount = amount.substring(1);
		double total = Double.parseDouble(amount);

		return total;

	}

/*	public static void main(String[] args) throws InterruptedException, IOException {

		TestCase_3 t3 = new TestCase_3();
		try {
			t3.testMethod3();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}*/

	/*@BeforeTest
	public void killTask() throws IOException, InterruptedException{
		
		Runtime.getRuntime().exec("taskKill /F /IM node.exe");
		Thread.sleep(3000);
	}*/
}
