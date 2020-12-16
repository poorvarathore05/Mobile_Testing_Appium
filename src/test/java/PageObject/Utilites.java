package PageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilites {

	AndroidDriver<AndroidElement> driver;

	public Utilites(AndroidDriver<AndroidElement> driver) {

		this.driver = driver;
	}

	public void scrollToText(String text) {

		driver.findElementsByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"))");
	}

}
