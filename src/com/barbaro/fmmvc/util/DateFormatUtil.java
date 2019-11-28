package com.barbaro.fmmvc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

	public static final String FORMAT = "yyyy-MM-dd";
	
	public static Date parseDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT);
		try {
			return format.parse(date);
		}catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static java.sql.Date parseSQLDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT);
		try {
			return new java.sql.Date(format.parse(date).getTime());
		}catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
