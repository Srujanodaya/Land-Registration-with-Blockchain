package com.megainfo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	// Date Formatter for Today's Date
	public String todayDate() {
		Date today = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
		String todayDate = ft.format(today);
		return todayDate;
	}

	// Date Formatter for any date
	public String todayDate(Date date) {
		SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
		String todayDate = ft.format(date);
		return todayDate;
	}
	
	public String todayDateWithTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String todayDateWithTime = sdf.format(date);
		return todayDateWithTime;
	}

	public static void main(String[] args) throws ParseException {
		DateFormatter df = new DateFormatter();
		Date today = new Date();
		System.out.println("Current Date: " + df.todayDateWithTime(today));
	}

}
