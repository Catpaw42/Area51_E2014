package helperClasses;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.dao.ModalitetDao;

public class Validator {

	public static boolean validateCpr(String cpr){
		return cpr.matches("(\\d{6}-\\w{4})");
	}
	/**
	 * 
	 * @param date formats accepted are: yyyy-mm-dd or yyyymmdd.
	 * constraints have been set so it is not possible to hava mm > 12 and dd > 39
	 * @return true if string matches format
	 */
	public static boolean validateDate(String date){
		return date.matches("(\\d{4}-[0-1][0-2]-[0-3]\\d)");
//		return date.matches("([0-3]\\d-[0-1][0-2]-\\d{4})|(\\d{8})");
	}
	/**
	 * 
	 * @param timestamp String which can be transformed to timestamp. Should pass the validator method to make sure it passes 
	 * @return if format is not correct null will be returned
	 * Date can have two formats therefore check is made to find out which one
	 */
	public static Timestamp stringToTimestamp(String timestamp){
		if(!validateDate(timestamp)) return null;

		DateFormat dateFormat = new SimpleDateFormat((timestamp.contains("-") ? "yyyy-MM-dd" : "yyyyMMdd"));

		//		if(!timestamp.contains("-")){
		//			dateFormat = new SimpleDateFormat("ddMMyyyy");
		//		}
		Date date = null;
		try {
			date = dateFormat.parse(timestamp);
		} catch (ParseException e) {
			System.err.println("failed to parse timestamp");
		}

		return new Timestamp(date.getTime());

	}
}