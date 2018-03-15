package WizzAirPages;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FarFinedrCalendar {
	private static WebDriver _driver ;
	private   By _calTable;
	private  By _monthSelectDDL;
	
	

	//private static By departDays = By.xpath("//*[@id='app']/div[2]/main/div/div/div/div[2]/div[2]/div[1]/div/div[3]/ul//span");
	/*
	private static By calDays = By.xpath("//*[@id='app']/div[2]/main/div/div/div/div[2]/div[2]/div[1]/div/div[3]/ul//li");
	private static By rtDays = By.xpath("//*[@id='app']/div[2]/main/div/div/div/div[2]/div[2]/div[2]/div/div[3]/ul//li");
	*/
	public FarFinedrCalendar (WebDriver driver,By ctable, By monthSelect){
		_driver= driver;
		this._calTable = ctable;
		this._monthSelectDDL = monthSelect;
		
	}
	

	
	public  void set_calTable(By _calTable) {
		this._calTable = _calTable;
	}



	public  void set_monthSelect(By _monthSelectDDL) {
		this._monthSelectDDL = _monthSelectDDL;
	}

	@SuppressWarnings("null")
	public List<DaysPrices> getTablePrices(){
		DaysPrices dP ;
		WebDriverWait wait =  new  WebDriverWait(_driver, 15);

		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select")));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> ls =_driver.findElements(_calTable);
		List<DaysPrices> daysPrice =  new ArrayList<DaysPrices>();
		//List<DaysPrices> daysPrice = null;
		for (WebElement x: ls){
			String[] s = x.getText().split("\n");
			if (s.length > 2 )
			{			
				dP = new DaysPrices(Integer.parseInt(s[s.length-3]),Double.parseDouble(s[s.length-2].replace(",", "")));
				daysPrice.add(dP);		
			}
		}
		return daysPrice;

	}
	
	public void selectMonth(int month, int year){
		String m =String.valueOf(month); 
		String y= String.valueOf(year);
		if (m.length()<2){
			 m ="0"+m;
		}
		if (y.length()<4){
			 y ="20"+y;
		}
		Select dropdown = new Select(_driver.findElement(_monthSelectDDL));
		dropdown.selectByValue(y+"-"+m);
		//_driver.findElement(_monthSelectDDL).findElement(By.xpath("//option[contains(text(),'"+month+"')]")).click();

		
	}
	
	public List<monthSelect> getAllMonth() {
		Select select = new Select(_driver.findElement(_monthSelectDDL));
		String[] num = new String[2];
		List<monthSelect> val= new ArrayList<>();
		List<WebElement> ls = select.getOptions();
		int tmp;
		if (ls.size()>0) {
			for(WebElement x:ls) {			
				num= x.getAttribute("value").split("-");
				val.add(new monthSelect(Integer.parseInt(num[1]),Integer.parseInt(num[0])));
			}
		}
		return val;
	}
	
	
	
	public  class monthSelect{
		private int mm;
		private int yyyy;
		
		public monthSelect(int month, int year) {
			this.mm=month;
			this.yyyy=year;
		}
		public int getMm() {
			return mm;
		}
		public void setMm(int mm) {
			this.mm = mm;
		}
		public int getYyyy() {
			return yyyy;
		}
		public void setYyyy(int yyyy) {
			this.yyyy = yyyy;
		}
		
	}
}
