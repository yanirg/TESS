package WizzAirPages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimeTablePage {
	private static WebDriver _driver;
	public  FarFinedrCalendar ffCalDepart ;
	public  FarFinedrCalendar ffCalReturn ;
	public  SearchBoxTimeTable sbTT ;
	private static boolean cookieFrame=true;
	private static By finderTitle = By.cssSelector(".heading.heading--1.fare-finder-title");
	private static By cookieDiv = By.cssSelector(".cookie-policy.toggle-transition"); //cookie-policy__button
	
	public TimeTablePage(WebDriver driver){
		_driver = driver;
		ffCalDepart = new FarFinedrCalendar(_driver,By.xpath("//*[@id='app']/div[2]/main/div/div/div/div[2]/div[2]/div[1]/div/div[3]/ul//li"),By.xpath("//*[@id='app']/div[2]/main/div/div/div/div[2]/div[2]/div[1]//select"));
		ffCalReturn = new FarFinedrCalendar(_driver,By.xpath("//*[@id='app']/div[2]/main/div/div/div/div[2]/div[2]/div[2]/div/div[3]/ul//li"),By.xpath("//*[@id='app']/div[2]/main/div/div/div/div[2]/div[2]/div[2]//select"));
		sbTT = new SearchBoxTimeTable(_driver);

	
	}
	
	private void closeCookieDiv() {
		WebDriverWait drv= new WebDriverWait(_driver, 20);
		drv.until(ExpectedConditions.presenceOfElementLocated(cookieDiv));		
		if (_driver.findElements(cookieDiv).size()>0) {
			_driver.findElement(By.cssSelector(".cookie-policy__button")).click();
			cookieFrame = false;
		}
	}
	
	
	
	public LinkedList<String> getAllDestenation(){
		return sbTT.getListOfDestenations();
	}
	
	public List<DaysPrices> getDepartureTablePrice(int month , int year){
		if(month>0){
			ffCalDepart.selectMonth(month, year);
		}
		return ffCalDepart.getTablePrices();
	}
	
	public List<DaysPrices> getReturnTablePrice(int month , int year){
		if(month>0){
			ffCalReturn.selectMonth(month, year);
		}
		return ffCalReturn.getTablePrices();
		
		
		
	}
	
	public void searchFlight( String org, String dest, boolean dsc){

		sbTT.setOrigin(org);
		//check if origin have destenation
		if(!(getAllDestenation().size()>0)){
			System.out.println("No return flight if Depart from: "+org);
			return;
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cookieFrame) {
			closeCookieDiv();
		}
		sbTT.setDest(dest);
		if (dsc){
			sbTT.activateDiscount();
		}		
		sbTT.clickSearch();
	}
	public void searchFlight( String org, String dest, boolean dsc,int month, int year, int rtnMonth){

		sbTT.setOrigin(org);
		//check if origin have destenation
		if(getAllDestenation().size()>0){
			System.out.println("No return flight if Depart from: "+org);
			return;
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sbTT.setDest(dest);
		if (dsc){
			sbTT.activateDiscount();
		}	
		ffCalDepart.selectMonth(month, year);
		ffCalReturn.selectMonth(month, year);
		sbTT.clickSearch();
	}
	
	
	
	public void updateDeparturenMonth(int month, int year){
		ffCalDepart.selectMonth(month , year);
		
	}
	
	public void updateReturnMonth(int month, int year){
		ffCalReturn.selectMonth(month, year);
	}
	
	public void updateDestenation(String dest){
		sbTT.setDest(dest);
		sbTT.clickSearch();
	}
	
	
	

	

}
