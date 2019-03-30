package com.community.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtils {

	public static String now() {
		Calendar c = GregorianCalendar.getInstance();
		DateFormat tf = new SimpleDateFormat();
		return tf.format(c);
	}
}
