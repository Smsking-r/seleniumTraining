package testng.train;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPojo extends SmsBaseClass {
	
	public LoginPojo() {
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="email")
	private WebElement txtEmail;
	
	@FindBy(id="pass")
	private WebElement txtPwd;
	
	@FindBy(name="login")
	private WebElement lgnButton;
	
	public WebElement getTextemail() {
		return txtEmail;
		
	}
	public WebElement getTxtPass() {
		return txtPwd;
	}
	public WebElement getlgnbutton() {
		return lgnButton;
	}
	
	
	
}
