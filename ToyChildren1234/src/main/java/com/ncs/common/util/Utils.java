package com.ncs.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	private static final String FORMAT_DATE = "dd/MM/yyyy";
	
	public static Date convertStringToDate(String date) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			throw new Exception("Ngày không đúng định dạng");
		}
	}
}
