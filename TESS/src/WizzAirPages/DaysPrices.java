package WizzAirPages;


public class DaysPrices {
	int day;
	double price;
	
	public  DaysPrices(int inDay,double inPrice) {
		day=inDay;
		price = inPrice;
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
