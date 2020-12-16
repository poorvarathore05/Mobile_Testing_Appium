package com.appium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseClass {

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	public static AppiumDriverLocalService startServer() {

		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {

			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}

		return service;

	}

	public static void startEmulator() throws IOException, InterruptedException {
		// D:\Work\AppiumProject\src\main\java\resources
		// Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		Runtime.getRuntime().exec("D:\\Work\\AppiumProject\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(7000);
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public AndroidDriver<AndroidElement> Capabilities(String appName) throws IOException, InterruptedException {

		// InputStream inStream = new
		// FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\global.properties");
		InputStream inStream = new FileInputStream("D:\\Work\\AppiumProject\\src\\main\\java\\global.properties");
		Properties pro = new Properties();
		pro.load(inStream);

		File file = new File("src");
		// File fs = new File(file, "ApiDemos-debug.apk");
		File fs = new File(file, (String) pro.get(appName));

		DesiredCapabilities cap = new DesiredCapabilities();
		String device = pro.getProperty("deviceName");

		/*if (device.contains("Nexus6")) {
			startEmulator();
		}*/
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10);
		//cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "25");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.VERSION, "10.0");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
		 
		cap.setCapability("autoGrantPermissions", true);
		cap.setCapability("noReset", "false");
		cap.setCapability("fullReset", "true");
		// iosDriver = new IOSDriver<MobileElement>(service.getUrl(),
		// capabilities);
		AndroidDriver<AndroidElement> driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		//AndroidDriver<AndroidElement> driver = new AndroidDriver<>(service.getUrl(), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

}
