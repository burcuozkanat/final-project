package swe599.AppiumTestFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.Preferences;

public class ApiDemoTest extends base {

	@Test(dataProvider = "InputData", dataProviderClass = TestData.class)
	public void apiDemoTest(String input) throws IOException, InterruptedException {
		
		service = startServer();

		AndroidDriver<AndroidElement> driver = Capabilities("apiDemo");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		HomePage h = new HomePage(driver);		

		h.Preferences.click();

		Preferences p = new Preferences(driver);
		p.dependencies.click();
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementByClassName("android.widget.EditText").sendKeys(input);
		
		p.buttons.get(1).click();
		
		service.stop();
	}
}
