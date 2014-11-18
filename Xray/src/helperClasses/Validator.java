package helperClasses;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.dao.ModalitetDao;

public class Validator {

	/**
	 * 
	 * @param phoneNo as string
	 * @return returns true if phoneNo is only digits with optional '+' in the beginning 
	 */
	public static boolean validatePhoneNo(String phoneNo){
		return phoneNo == null ? false : phoneNo.matches("(\\+)?\\d+"); // simple validator
		}
	
	public static boolean validateSaerligeForhold(boolean ingen_saerlige_Forhold, boolean hoerehaemmet, boolean synshaemmet, boolean amputeret, boolean kan_ikke_staa, boolean dement, boolean afasi, boolean ilt, String ilt_tekst, boolean tolk, String tolk_tekst, boolean isolation, String isolation_tekst, boolean cytostatika, String cytostatika_dato){
		// if ilt is checked but tekst is empty or the opposite false is returned
		if(ilt && ilt_tekst == null || !ilt && ilt_tekst != null){
			return false;
		}
		// if tolk is checked but tekst field is empty or the opposite false is returned
		if(tolk && tolk_tekst == null || !tolk && tolk_tekst != null){
			return false;
		}
		// if isolation is checked but tekst field is empty or the opposite false is returned
		if(isolation && isolation_tekst == null || !isolation && isolation_tekst != null){
			return false;
		}
		// if cytostatika is checked but date somehow is not correct or the opposite false is returned. Also a third check if check field is not checked and tekstfield is not empty but still wrong 
		if(cytostatika && !validateDate(cytostatika_dato) || !cytostatika && validateDate(cytostatika_dato) || !cytostatika && cytostatika_dato != null){
			return false;
		}
		// checks if ingen_saerlige_Forhold is checked then none of the other fields should be checked
		if(ingen_saerlige_Forhold){
			if(hoerehaemmet || synshaemmet || amputeret || kan_ikke_staa || dement || afasi || ilt || tolk || isolation || cytostatika){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Matches phone number. Accepts numbers with area code fx: 0045, +45 and 00298 not mandatory followed by 6, 8 or 10 digit numbers
	 * @param phoneNo as string
	 * @return returns true if matching the rules explained before.
	 */
	public static boolean validatePhoneNoAdv(String phoneNo){
		return phoneNo == null ? false : phoneNo.matches("((\\+|00)\\d{2}(\\d{1})?)?(\\d{8}|\\d{6}|\\d{10})"); // advanced validator
	}
	
	/**
	 * Matching cpr. with 6 digit followed by a '-' and 4 characters not necessary numbers
	 * @param cpr
	 * @return
	 */
	public static boolean validateCpr(String cpr){
		return cpr == null ? false : cpr.matches("(\\d{6}-\\w{4})");
	}
	/**
	 * 
	 * @param date formats accepted are: yyyy-mm-dd or yyyymmdd.
	 * constraints have been set so it is not possible to hava mm > 12 and dd > 39
	 * @return true if string matches format
	 */
	public static boolean validateDate(String date){
		return date == null ? false : date.matches("(\\d{4}-[0-1][0-2]-[0-3]\\d)");
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
		Date date = null;
		try {
			date = dateFormat.parse(timestamp);
		} catch (ParseException e) {
			System.err.println("failed to parse timestamp");
		}

		return new Timestamp(date.getTime());

	}
}