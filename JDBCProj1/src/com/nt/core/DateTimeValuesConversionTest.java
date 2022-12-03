package com.nt.core;

import java.text.SimpleDateFormat;

public class DateTimeValuesConversionTest {

	public static void main(String[] args)throws Exception {
		//converting string values to java.util.date class obj
		String d1="20-10-1990";//dd-mm-yyyy
		SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date ud1=sdf.parse(d1);
		System.out.println(ud1);
		//CONVERTING Java.util.Date class obj to java.sql.Date obj
		long ms=ud1.getTime();//gives no of millis that are elapsed from
								// epoach jan1st 1970 00:00 hours ud1 date and time
		java.sql.Date sqd1=new java.sql.Date(ms);
		System.out.println(sqd1);
		
		
		
		
		String d2="1980-12-10";
		java.sql.Date sqd2= java.sql.Date.valueOf(d2);
		System.out.println("sql date value::"+sqd2);
	}

}
