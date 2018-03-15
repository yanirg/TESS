package WizzAirPages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import Tools.Tools;
import WizzAirPages.FarFinedrCalendar.monthSelect;

public class TimeTableAFunc {
	private static WebDriver _driver;
	private  TimeTablePage ttp;
	private List<PriceTableHendler> pth = new ArrayList<PriceTableHendler>();
	public TimeTableAFunc(WebDriver driver){
		_driver = driver;
		ttp =  new TimeTablePage(_driver);
		
	}
	
	private List<PriceTableHendler> getPriceDeptRtninMonth(String orgn,String dest, boolean discount, int month, int year) {
		List<DaysPrices> price;
		ttp.searchFlight(orgn, dest, discount);
		price =ttp.getDepartureTablePrice(month,2018);
		pth.add( new PriceTableHendler( orgn,dest, month, year, price));
		price =ttp.getReturnTablePrice(month,2018);
		pth.add( new PriceTableHendler( dest,orgn, month, year, price));
		return pth;
	}
	
	private List<PriceTableHendler> getPriceDeptRtnMaxPeriod(String orgn,String dest, boolean discount){
		List<DaysPrices> price;
		ttp.searchFlight(orgn, dest, discount);
		ListIterator<monthSelect> dpM = ttp.ffCalDepart.getAllMonth().listIterator();
		monthSelect mn;
		while(dpM.hasNext()) {
			mn=dpM.next();
			price =ttp.getDepartureTablePrice(mn.getMm(),mn.getYyyy());
			pth.add( new PriceTableHendler( orgn,dest,mn.getMm(), mn.getYyyy(), price));
			price =ttp.getReturnTablePrice(mn.getMm(),mn.getYyyy());
			pth.add( new PriceTableHendler( dest,orgn, mn.getMm(), mn.getYyyy(), price));
			
			
		}
		
		return pth;
		
	}
	
	public void printPriceDeptRtnMaxPeriod(String orgn,String dest, boolean discount) {
		getPriceDeptRtnMaxPeriod( orgn, dest,  discount);
		printPth();
		
	}
	
	
	
	private List<PriceTableHendler> getPriceToAllDestIntMonth(String orgn, boolean discount, int month, int year){
		if (discount){
			ttp.sbTT.activateDiscount();
		}
		int bMonth=month;
		ttp.sbTT.setOrigin(orgn);
		Iterator<String> destenations =ttp.getAllDestenation().listIterator();
		if(destenations.hasNext()){	
			String destName; 
			List<DaysPrices> price;
			while(destenations.hasNext()){	
				destName = destenations.next();
				ttp.updateDestenation(destName);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				price =ttp.getDepartureTablePrice(month,2018);
				pth.add( new PriceTableHendler(orgn, destName, month, year, price));
				price =ttp.getReturnTablePrice(month,2018);
				pth.add( new PriceTableHendler( destName,orgn, month, year, price));
				
			
			}	
		}
		return pth;
	}
	
	public void printAllDestenationUnderPriceInMOnth(double price, String orgn, boolean discount, int month, int year) {
		getPriceToAllDestIntMonth( orgn,  discount,  month,  year);
		if(!pth.isEmpty()){
			for(int i=0; i<pth.size();i++){
				pth.get(i).setPriceTable( Tools.filterFlightUnderPrice(price,pth.get(i).getPriceTable()));
//				System.out.println("Departe From: "+pth.get(i).getOrigin()+" Destenation: "+pth.get(i).getDestenation());
//				System.out.println("Month: "+pth.get(i).getMonth()+"/"+pth.get(i).getYear());
//				printPrice(pth.get(i).getPriceTable());
//				System.out.println("\n");
			}
		}
		printPth();
	}
	
	public void printDayesUnderPriceInMonth(double price, String orgn,String dest, boolean discount, int month, int year) {
		pth=getPriceDeptRtninMonth( orgn, dest,  discount,  month,  year);
		if(!pth.isEmpty()){
			for(int i=0; i<pth.size();i++){
				pth.get(i).setPriceTable( Tools.filterFlightUnderPrice(price,pth.get(i).getPriceTable()));
				System.out.println("Departe From: "+pth.get(i).getOrigin()+" Destenation: "+pth.get(i).getDestenation());
				System.out.println("Month: "+pth.get(i).getMonth()+"/"+pth.get(i).getYear());
				printPrice(pth.get(i).getPriceTable());
				System.out.println("\n");
			}
		}
	}
	
	
	
	public void printDestenationPriceMonth(String orgn,String dest, boolean discount, int month, int year) {
		pth=getPriceDeptRtninMonth( orgn, dest,  discount,  month,  year);
		if(!pth.isEmpty()){
			for(int i=0; i<pth.size();i++){
				System.out.println("Departe From: "+pth.get(i).getOrigin()+" Destenation: "+pth.get(i).getDestenation());
				System.out.println("Month: "+pth.get(i).getMonth()+"/"+pth.get(i).getYear());
				printPrice(pth.get(i).getPriceTable());
				System.out.println("\n");
			}
		}
	}
	
	
	
	public void getYearllyDetentionLowestPrice(String dest){
		//ttp.searchFlight(org, dest, dsc);
	}
	
	public void prntPriceToAllDestIntMonth(String orgn, boolean discount, int month, int year){
		pth=getPriceToAllDestIntMonth( orgn, discount,  month,  year);
		if(!printPth()) {
			System.out.println("There are no flight from" + orgn);
		}

	}
	
	
	private boolean printPth() {
		if(!pth.isEmpty()){
			for(int i=0; i<pth.size();i++){
				System.out.println("Departe From: "+pth.get(i).getOrigin()+" Destenation: "+pth.get(i).getDestenation());
				System.out.println("Month: "+pth.get(i).getMonth()+"/"+pth.get(i).getYear());
				printPrice(pth.get(i).getPriceTable());
				System.out.println("\n");
			}
		}
		else{
			System.out.println("there are No Flights in list ");
			return false;

		}
		return true;
		
	}
	
	
	
	public void printPrice(List<DaysPrices> price){
		if (!price.isEmpty()) {
			for(DaysPrices x: price){
				System.out.println("day: "+x.getDay()+" price: "+x.getPrice() );
			}
		}
		else {
			System.out.println("There are no flight this month");
		}
	}
	
	public void printAllDestenations() {
		ListIterator<String> i = ttp.sbTT.getListOfDestenations().listIterator();
		if(i.hasNext()) {
			while(i.hasNext()) {
				System.out.println(i.next());
			}
			
		}
		else {
			System.out.println("No Destenetion in List");
		}
	}
	

//	public void printDepPrice() {
//		List<DaysPrices> s=ttp.ffCalDepart.getTablePrices();
//		if (!s.isEmpty()) {
//			for(DaysPrices x: s){
//				System.out.println("day: "+x.getDay()+" price: "+x.getPrice() );
//			}
//		}
//		else {
//			System.out.println("No Departure Filghts this mounth");
//			
//		}	
//	}
	
//	public void printRetuenPrice() {
//		List<String> s=ttp.ffCalReturn.getTablePrices();
//		if (!s.isEmpty()) {
//			Iterator<String> i =s.iterator();
//			while(i.hasNext()) {
//				System.out.println(i.next());			
//			}
//		}
//		else {
//			System.out.println("No Return Filghts this mounth");
//			
//		}	
//	}
	
	

}
