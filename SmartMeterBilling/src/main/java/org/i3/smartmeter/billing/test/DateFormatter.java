package org.i3.smartmeter.billing.test;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	public static final Format formatter = new SimpleDateFormat("MM/dd/yy hh:mm aa");
	
	public static Date getDate(String str) throws ParseException{
		return getDate(str, formatter);
	}
	
	public static Date getDate(String str, Format formatter) throws ParseException{
		return (Date)((DateFormat) formatter).parse(str);
	}
	
	/*
	 * DONT DELETE 
	 */
	public static final Format readFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String getSQLDate(Date str) throws ParseException{
		return getSQLDate(str, readFormatter);
	}
	
	public static String getSQLDate(Date str, Format formatter) throws ParseException{
		return formatter.format(str);
	}
}
