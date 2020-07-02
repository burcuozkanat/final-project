package swe599.AppiumTestFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base {

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		
		System.out.println("Is emulator running : " + isServerRunning);
	
		return isServerRunning;
	}
	
	public AppiumDriverLocalService startServer() {

		if (!checkIfServerIsRunnning(4723)) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}

		return service;
	}

	

	public static void startEmulator() throws IOException, InterruptedException {

		// On runtime go and execute this file
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(6000);
		
		System.out.println("Emulator started...");
		
	}

	public static AndroidDriver<AndroidElement> Capabilities(String applicationName) throws IOException, InterruptedException {

		// Get project path
		String projectPath = System.getProperty("user.dir");

		FileInputStream fileInputStream = new FileInputStream(
				projectPath + "\\src\\main\\java\\swe599\\AppiumTestFramework\\config.properties");

		Properties properties = new Properties();
		properties.load(fileInputStream);

		String device = (String) properties.get("device");		
		System.out.println(device);
		
		// if device name doesn't have emulator in it then act as its a real device and do not start emulator
		if (device.toLowerCase().contains("emulator")) {
			startEmulator();
		}

		File srcFolder = new File("src");
		File file = new File(srcFolder, (String) properties.get(applicationName));
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
		capabilities.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());

		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}
	
	public static void getScreenshot(String defectScreenshotName) throws IOException {
		File ssFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ssFile,new File(System.getProperty("user.dir") + "\\" + defectScreenshotName + ".png"));
		
	}
}
