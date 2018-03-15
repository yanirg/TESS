package WizzAirPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchBoxTimeTable extends  SearchBox {
	
	private static By discountChk = By.cssSelector("label[for='fare-finder-wdc']");
	public SearchBoxTimeTable(WebDriver driver) {
		super(driver);
		 
		// TODO Auto-generated constructor stub
	}
	
	public void activateDiscount(){
		WebDriverWait a = new WebDriverWait(_driver,10);
		a.until(ExpectedConditions.elementToBeClickable(discountChk));
		if(!_driver.findElement(discountChk).isSelected()){
			_driver.findElement(discountChk).click();
		}
	}

}
