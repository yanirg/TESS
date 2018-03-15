package Tools;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import WizzAirPages.DaysPrices;

public class Tools {
	@SuppressWarnings("null")
	public static List<DaysPrices> filterFlightUnderPrice(double price, List<DaysPrices> dp){
		List<DaysPrices> rs = new ArrayList<>();
		ListIterator<DaysPrices> ls = dp.listIterator();
		DaysPrices st;
		
		while(ls.hasNext()) {
			st=ls.next();
			if (st.getPrice()<=price) {
				rs.add( st);
				
			}
		}
		return rs;
	}

}
