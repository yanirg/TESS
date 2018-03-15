package WizzAirPages;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class SearchBox   {
	
	protected static WebDriver _driver;
	private static By origin = By.cssSelector("#search-departure-station");
	private static By dest = By.cssSelector("#search-arrival-station");
	
	private static By searchBtn = By.cssSelector(".fare-finder__sidebar__submit");
	
	
	public SearchBox (WebDriver driver){
		_driver = driver;
	}
	
	public void setOrigin(String org){
		_driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		WebDriverWait wait =  new  WebDriverWait(_driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(origin));

		_driver.findElement(origin).click();
		WebElement ele = _driver.findElement(By.xpath("//strong[contains(.,'"+org+"')]"));
		((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.click();
		
	}
	
	public void setDest(String destenation){
		
		WebDriverWait wait =  new  WebDriverWait(_driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(dest));
		_driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		_driver.findElement(dest).click();
	
		WebElement ele = _driver.findElement(By.xpath("//strong[contains(.,'"+destenation+"')]"));
		((JavascriptExecutor) _driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.click();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void clickSearch(){
		try {
			WebElement x =_driver.findElement(searchBtn);
			x.click();
		}catch (Exception e){
	        System.out.println("Exception is found here: " + e);

	    }
	}
	
	@SuppressWarnings("null")
	public LinkedList<String> getListOfDestenations() {
		_driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		WebDriverWait wait =  new  WebDriverWait(_driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(dest));

		_driver.findElement(dest).click();
		LinkedList<String> list = new LinkedList<String>();
		//String a;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> eles = _driver.findElements(By.xpath("//*[@id='app']/div[2]/main/div/div/div/div[2]/div[1]/div/div[1]/div[1]/div[1]/fieldset/div[3]/div//strong"));
		if(eles.size()>0) {
			for(WebElement ele:eles) {
				list.add(ele.getAttribute("innerText"));
				// System.out.println(a);
				// list.add(a.toString());
			}
		}
		return list;
	}
	

}
