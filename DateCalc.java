package lbms;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateCalc {

	public static void main(String[] args) {
		//Date date = new Date();
		//String Borrowed_Date = date.toString();
		
		//System.out.println("Todays date: "+Borrowed_Date);
		//test addDays method
		//int i=30;
		
		//addDays(date, i);
			//String Return_Date=newDate.toString();
			
	}
	/**
	 * add days to date in java
	 * @param date
	 * @param days
	 * @return
	 */
	public static String addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		String Return_date = cal.getTime().toString();	
		return Return_date;
	}
	
	

}