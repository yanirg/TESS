import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.lang.model.util.Elements;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sun.jna.Native;
import com.sun.jna.PointerType;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;

import WizzAirPages.TimeTableAFunc;


public class mn {

//	private static By depart = By.cssSelector("#search-departure-station");
//	private static By dest = By.cssSelector("#search-arrival-station");
	public static void main(String[] args) {
		
;
		// TODO Auto-generated method stub
		ChromeOptions co = new ChromeOptions();
		//here "--start-maximized" argument is responsible to maximize chrome browser
		co.addArguments("--start-maximized");
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\Drivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(co);
		
		driver.get("https://wizzair.com/en-gb/flights/timetable#/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	//////////
		
		TimeTableAFunc tt = new TimeTableAFunc(driver);
		tt.printPriceDeptRtnMaxPeriod("Tel-Aviv","London Luton",true);
	//	tt.printDestenationPriceMonth("Tel-Aviv","London Luton",true, 2, 2018);
		//tt.printDayesUnderPrice(270, "Tel-Aviv","London Luton",true, 2, 2018);
	//	tt.printAllDestenationUnderPriceInMOnth(200,"Tel-Aviv",true, 2, 2018);
	//	tt.prntPriceToAllDestIntMonth("Tel-Aviv",true, 2, 2018);
		//tt.searchFlightCurrentMonth( "Tel-Aviv", "Craiova", true);
		//tt.getAllDestenationPriceInCurrentMonth("Tel-Aviv");
		//tt.printDepartPrice();
		//tt.printRetuenPrice();
		//System.out.println("Return\n");
		//tt.printAllDestenations();


		//driver.switchTo().alert().accept();
		driver.quit();
//		WebElement myDynamicElement = 
//				(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='nrg2015-icons nrg2015-icons_external-fb']")));
//	
//		driver.findElement(depart).click();
//		driver.findElement(By.xpath("//strong[contains(.,'Budapest')]")).click();
//		
//		driver.findElement(dest).click();
//		driver.findElement(By.xpath("//strong[contains(.,'Budapest')]")).is;
//		Assert.assertFalse(condition);
		
		//myDynamicElement.click();
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(String winHandle : driver.getWindowHandles()){
//		    driver.switchTo().window(winHandle);
//		    System.out.println(driver.getTitle());
//		}

	
		//List<WebElement> elements = driver.findElements(By.xpath("//*[@id='footer_TAC12ECC3001_Col02']/ul//a"));
		
//		for (int i = 0; i< elements.size();i++){
//			if (elements.get(i).getText().equalsIgnoreCase("timetable")){
//				System.out.println(elements.get(i).getAttribute("href"));
//				JavascriptExecutor je = (JavascriptExecutor) driver;
//				je.executeScript("arguments[0].scrollIntoView(true);",elements.get(i));
//
//				Actions ac = new Actions(driver);
//				ac.moveToElement(elements.get(i)).build().perform();
//				
//				elements.get(i).click();
//				break;
//				
//			}
//		}
//		
//		
//		System.out.println(driver.findElements(By.tagName("a")).size());
//		for(WebElement x : driver.findElements(By.tagName("a"))){
//			System.out.println(x.getText());
//		}

		driver.quit();
		
		
	}

	

}
