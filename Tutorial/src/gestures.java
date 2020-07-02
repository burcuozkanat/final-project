import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;


public class gestures extends base{

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		
		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		
		// Tap
		TouchAction touchAct = new TouchAction(driver);
		WebElement expandList = driver.findElementByAndroidUIAutomator("text(\"Expandable Lists\")");
		touchAct.tap(tapOptions().withElement(element(expandList))).perform();
		
		driver.findElementByAndroidUIAutomator("text(\"1. Custom Adapter\")").click();
		
		WebElement peopleNames = driver.findElementByAndroidUIAutomator("text(\"People Names\")");
		touchAct.longPress(longPressOptions().withElement(element(peopleNames)).withDuration(ofSeconds(2))).release();
		
		System.out.println(driver.findElementById("android:id/title").isDisplayed());
	}

}
