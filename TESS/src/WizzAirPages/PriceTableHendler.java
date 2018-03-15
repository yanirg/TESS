package WizzAirPages;

import java.util.List;

public class PriceTableHendler {
	
	private String origin; //origin
	private String destenation;//'Destination
	private int month; //month
	private int year;
	private List<DaysPrices> priceTable;
	public PriceTableHendler(String origin, String destenation, int month, int year, List<DaysPrices> price) {
		
		this.origin = origin;
		this.destenation = destenation;
		this.month = month;
		this.year = year;
		this.priceTable = price;
	}
	
	public void setPriceTable(List<DaysPrices> priceTable) {
		this.priceTable = priceTable;
	}

	public String getOrigin() {
		return origin;
	}
	public String getDestenation() {
		return destenation;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	public List<DaysPrices> getPriceTable() {
		return priceTable;
	}
	
	

}
