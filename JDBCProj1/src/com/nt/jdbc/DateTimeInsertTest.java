package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeInsertTest {
	private static final String INSERT_EMP_DATES_QUERY="INSERT INTO EMPLOYEE VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj916db","root","123456789");
				PreparedStatement ps=con.prepareStatement(INSERT_EMP_DATES_QUERY);
				){
			//read inputs
			int no=0;
			String name=null,dob=null,tob=null,doj=null;
			if(sc!=null) {
				System.out.println("enter employee number::");
				no=sc.nextInt();
				System.out.println("enter employee name::");
				name=sc.next();
				System.out.println("enter employee DOB (DD-MM-YYYY)::");
				dob=sc.next();
				System.out.println("enter employee TOB (hh:mm:ss)::");
				tob=sc.next();
				System.out.println("enter employee DOJ (yyyy-mm-dd hh:mm:)::");
				sc.nextLine();
				doj=sc.nextLine();
			}
			//convert string date value to java.sql.Date class obj
			//Conver string date value to java.util.date class obj
			java.text.SimpleDateFormat sdf=new SimpleDateFormat("dd-mm-yyyy");
			java.util.Date udob=sdf.parse(dob);
			//convert java.util.Date obj to java.sql.Date class obj
			long ms=udob.getTime();
			java.sql.Date sqdob=new java.sql.Date(ms);
			//convert string time value to java.sql.time class obj
			java.sql.Time sqtob=java.sql.Time.valueOf(tob);
			//convert string date time value to java.sql.Timestamp class obj
			java.sql.Timestamp sqdoj=java.sql.Timestamp.valueOf(doj);
			//set values to query params and execute the query
			if(ps!=null) {
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setDate(3, sqdob);
				ps.setTime(4, sqtob);
				ps.setTimestamp(5, sqdoj);
				
				//execute the query
				int count=ps.executeUpdate();
				//process the result
				if(count==0)
					System.out.println("Record not inseted");
				else
					System.out.println("Record inserted");
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main

}//class
