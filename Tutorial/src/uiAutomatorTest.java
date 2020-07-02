import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class uiAutomatorTest extends base {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub

		AndroidDriver<AndroidElement> driver = Capabilities();
		
		// driver.findElementByAndroidUIAutomator("attribute("value")");
		
		// click View
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		
		// Validate clickable feature for all options
		// attribute'leri direkt yazabiliyorz, propertyler için farklý
		// driver.findElementsByAndroidUIAutomator("new UiSelector().property(value)");
		System.out.println(driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size());
	}

}
