package WizzAirPages;

import java.util.List;

public interface FlightRequests {
	public void prntDeprtRtunLowestPriceForDestenationInMonth(String orign, String retn,int month, int filterNum);
	public void prntDeprtRtunAllPriceForDestenationInMonth(String orign, String retn,int month);
	public void prntDeprtRtunLowestPriceForDestenationInAYear(String orign, String retn, int filterNum);
	public void prntDeprtRtunAllPriceForDestenationInAYear(String orign, String retn);
	public void prntDeprtLowestPriceFromAllDestenationInAMonth(String orign, int month, int filterNum);
	public void prntReturnLowestPriceFromAllDestenationInAMonth(String orign, int month);
	
	List<PriceTableHendler> getPriceDeptRtninMonth(String orgn,String dest, boolean discount, int month, int year);
	List<PriceTableHendler> getPriceDeptRtnMaxPeriod(String orgn,String dest, boolean discount);
	List<PriceTableHendler> getPriceToAllDestIntMonth(String orgn, boolean discount, int month, int year);
	public void printAllDestenationUnderPriceInMOnth(double price, String orgn, boolean discount, int month, int year);
	public void printDayesUnderPriceInMonth(double price, String orgn,String dest, boolean discount, int month, int year);
	public void printDestenationPriceMonth(String orgn,String dest, boolean discount, int month, int year);
	public void prntPriceToAllDestIntMonth(String orgn, boolean discount, int month, int year);
	

}
