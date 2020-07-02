package swe599.AppiumTestFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CheckOutPage;
import pageObjects.FormPage;
import utilities.Utilities;

public class Ecommerce_tc_4 extends base {

	@Test
	public void totalValidation() throws IOException, InterruptedException {

		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		FormPage formPage = new FormPage(driver);
		formPage.getNameField().sendKeys("hello");
		driver.hideKeyboard();
		
		formPage.femaleOption.click();
		formPage.getCountrySelection().click();
		
		Utilities u = new Utilities(driver);
		u.scrollToText("Argentina");
		
		driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		Thread.sleep(4000);
		
		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
		double sum = 0;
		
		CheckOutPage checkOutPage = new CheckOutPage(driver);

		for (int i = 0; i < count; i++) {
			String amount1 = checkOutPage.productList.get(i).getText();
			double amount = getAmount(amount1);
			sum = sum + amount;
		}
		
		System.out.println(sum + "sum of products");

		String total = checkOutPage.totalAmount.getText();

		total = total.substring(1);
		double totalValue = Double.parseDouble(total);
		
		System.out.println(totalValue + "Total value of products");
		
		Assert.assertEquals(sum, totalValue);
		
		service.stop();
	}

	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		// taskkill /F /IM node.exe
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);

	}

	public static double getAmount(String value) {
		value = value.substring(1);
		double amount2value = Double.parseDouble(value);

		return amount2value;
	}
}
