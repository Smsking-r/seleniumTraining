package testng.train;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SmsBaseClass{

	public static WebDriver driver;
	public static Robot r;

	public static void openChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();   
	}
	public static void openFirefoxBrowser() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	public static void openEdgeBrowser() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}

	public static  void getUrl(String url) {
		driver.get(url);
	}

	public static  void closeCurrentTab() {
		driver.close();
	}

	public static  void quitBrowser() {
		driver.quit();
	}

	public static  void applyImplicitlyWait(int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public static   void fillTextBoxUsingJs(WebElement element,String input) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].setAttribute('value','"+input+"')", element);
		}
	public static void fillTextbox(WebElement ele,String input) {
	ele.sendKeys("input");	
	}
	public static String withDateAndTime() {
		Date dt = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy hh-mm");
		String simpleDateAndTime = sim.format(dt);
		return simpleDateAndTime;

	}
	
	public static  void takeSnap(String simpleDateAndTime) throws IOException {

		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File("E:\\SMS\\eclipse workspace\\testng.train\\Screenshot of TestNG\\TestNG snap "+simpleDateAndTime+".png");
		FileUtils.copyFile(src, des);

	}

	public static String fetchTitle() {
		String titlename = driver.getTitle();
		System.out.println(titlename);
		return titlename;

	}

	public static  String fetchUrl() {
		String titleUrl = driver.getCurrentUrl();
		System.out.println(titleUrl);
		return titleUrl;
	}
	
	public static  void copy() throws AWTException {
	r = new Robot();
	r.keyPress(KeyEvent.VK_CONTROL);	
	r.keyPress(KeyEvent.VK_C);	
	r.keyRelease(KeyEvent.VK_CONTROL);	
	r.keyRelease(KeyEvent.VK_C);	
	}

	public static  void paste() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);	
		r.keyPress(KeyEvent.VK_V);	
		r.keyRelease(KeyEvent.VK_CONTROL);	
		r.keyRelease(KeyEvent.VK_V);	
		}
	
	public static String checkCelltypevalue(Cell c) {
		String stringvalue="";
		if(c.getCellType()==1) {
			 stringvalue = c.getStringCellValue();
		} else if(DateUtil.isCellDateFormatted(c)){
			Date d = c.getDateCellValue();	
			SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yy");
			 stringvalue = sdf.format(d);
		}else {
//			double numericCellValue = c.getNumericCellValue();
			long doublefromcell = (long) c.getNumericCellValue();
			System.out.println(doublefromcell);
			 stringvalue = String.valueOf(doublefromcell);
		}
		return stringvalue;		
	}
	
	public static String checkCelltypevalue(Cell c , String stringvalue) {
		String cellValueIsA="";
		if (c.getCellType()==1) {
			 stringvalue = c.getStringCellValue();
			 cellValueIsA =  stringvalue +" Is  A String";
		} else if(DateUtil.isCellDateFormatted(c)){
			Date d = c.getDateCellValue();	
			SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yy");
			 stringvalue = sdf.format(d);
			 cellValueIsA = stringvalue +" Is  A  Date";
		}else {
//			double numericCellValue = c.getNumericCellValue();
			long doublefromcell = (long) c.getNumericCellValue();
			System.out.println(doublefromcell);
			 stringvalue = String.valueOf(doublefromcell);
			 cellValueIsA = stringvalue +" Is  A  Numeric";
		}
		return cellValueIsA;		
	}
	
	public static String readDatafromExcel(int rownum, int cellnum) throws IOException {
//		File f = new File("E:\\SMS\\eclipse workspace\\org.Mave\\Data Maintain\\sms.xlsx");
//		FileInputStream fis = new FileInputStream(f);
		
		FileInputStream fis = new FileInputStream("E:\\SMS\\eclipse workspace\\testng.train\\DataMaintance\\basexl.xlsx");
		Workbook book = new XSSFWorkbook(fis);
		Sheet sheetFromExcel = book.getSheetAt(0);
		Row row = sheetFromExcel.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		String cellValue = checkCelltypevalue(cell);
		return cellValue;
	}

	public static Cell readDatafromExcel(int rownum, double fl ) throws IOException {
//		File f = new File("E:\\SMS\\eclipse workspace\\org.Mave\\Data Maintain\\sms.xlsx");
//		FileInputStream fis = new FileInputStream(f);
		int  cellnum = (int)fl;
		FileInputStream fis = new FileInputStream("E:\\SMS\\eclipse workspace\\testng.train\\DataMaintance\\basexl.xlsx");
		Workbook book = new XSSFWorkbook(fis);
		Sheet sheetFromExcel = book.getSheetAt(0);
		Row row = sheetFromExcel.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		String cellValue = checkCelltypevalue(cell);
		return cell;
	}
	
	public static void writeDataToExcel(int rownum,int cellnum,String value) throws IOException {
		FileOutputStream fos = new FileOutputStream("E:\\SMS\\eclipse workspace\\testng.train\\DataMaintance\\basexl.xlsx");
		Workbook book = new XSSFWorkbook();
		Sheet sheetFromExcel = book.getSheetAt(0);
		Row row = sheetFromExcel.getRow(rownum);
		Cell createdNewCell = row.createCell(cellnum);
		createdNewCell.setCellValue(value);
		book.write(fos);
		fos.close();
		
	}
	
	public void buttonClickOnElement(WebElement ele) {
		ele.click();
	}
	
	


}


