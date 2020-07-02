package swe599.AppiumTestFramework;

import org.testng.annotations.DataProvider;

public class TestData {

	// test case will run 2 times. each for array element
	// testing alphanumerical characters
	@DataProvider(name="InputData")
	public Object[][] getDataForEditField() {
		Object[][] obj = new Object[][] {
			{"hello"}, {"@&$%"}
		};
		
		return obj;
	}
}
