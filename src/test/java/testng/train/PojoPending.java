package testng.train;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PojoPending extends SmsBaseClass{

	public static void main(String[] args) throws IOException {
		
		openChromeBrowser();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://adactinhotelapp.com/");
		driver.findElement(By.id("username")).sendKeys("smskingr");
		driver.findElement(By.id("password")).sendKeys("Sms@7425464");
		driver.findElement(By.id("login")).click();
		List<WebElement> dropdown = driver.findElements(By.tagName("select"));
		for (WebElement sele : dropdown) {
			sele.click();
//			for (WebElement last1 : dropdown) {
//				last1.sendKeys(Keys.DOWN);
//			}
			sele.sendKeys(Keys.ENTER);	
			sele.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		}
		driver.findElement(By.id("Submit")).click(); 
		driver.findElement(By.id("radiobutton_0")).click();
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("first_name")).sendKeys(readDatafromExcel(2, 1),Keys.TAB ,readDatafromExcel(2, 1),Keys.TAB, readDatafromExcel(1,3)
,Keys.TAB, readDatafromExcel(2,3), readDatafromExcel(2,3));
		
		List<WebElement> se = driver.findElements(By.tagName("select"));
		for (WebElement dro : se) {
			dro.click();
			for (WebElement last : se) {
				last.sendKeys(Keys.DOWN);
			}
		}
		driver.findElement(By.id("cc_cvv")).sendKeys(readDatafromExcel(2, 4), Keys.TAB , Keys.ENTER);
		
		String ss = driver.findElement(By.id("order_no")).getAttribute("value");
		System.out.println("The Booked Order NO : "+ss);
//		writeDataToExcel(5, 4,ss);
		
		
		
 
		/*
		 * Select s = new Select(driver.findElement(by.));
		List<WebElement> li = s.getOptions();
		for (WebElement order : li) {
		}
		*/

	}

}
