import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class dragDropDemo extends base {
	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		
		AndroidDriver<AndroidElement> driver = Capabilities();
		
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElementByAndroidUIAutomator("text(\"Drag and Drop\")").click();
		
		TouchAction touchAct = new TouchAction(driver);
		// longpress(source)/move(destination)/release
		WebElement source = driver.findElementsByClassName("android.view.View").get(0);
		WebElement destination = driver.findElementsByClassName("android.view.View").get(1);
		// touchAct.longPress(longPressOptions().withElement(element(source))).moveTo(element(destination)).release().perform();
		// longpress iþleminde lonpress harici ek bir iþlem yoksa longpressoptions yazmaya gerek yok
		touchAct.longPress(element(source)).moveTo(element(destination)).release().perform();
		

	}
}
