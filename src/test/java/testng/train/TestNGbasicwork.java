package testng.train;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGbasicwork extends SmsBaseClass {

	@Test
	private void loginWithUserNameAndPassword() throws IOException {
	System.out.println("welcome sms");
	takeSnap(withDateAndTime());
	LoginPojo l = new LoginPojo();
	WebElement mail = l.getTextemail();
//	fillTextbox(mail, "senthami");
	fillTextBoxUsingJs(mail, readDatafromExcel(2, 1));
	WebElement pass = l.getTxtPass();
	fillTextBoxUsingJs(pass, readDatafromExcel(2, 2));
	buttonClickOnElement(l.getlgnbutton());
	takeSnap(withDateAndTime());
	}
	
	@BeforeMethod
	private void beforemethod() {
		System.out.println("face book launched");
	}
	
	@AfterTest
	private void closeBrowser() {
		quitBrowser();
	}
	
	@BeforeTest
	private void getURL() {
		getUrl("https://www.facebook.com/");
	}
	
	@BeforeSuite
	private void openBrowser() {
		openChromeBrowser();
		applyImplicitlyWait(10);
	}
	
	@AfterClass
	private void afterclass() {
		System.out.println("REPORT GENERATED");
	}
	
}
