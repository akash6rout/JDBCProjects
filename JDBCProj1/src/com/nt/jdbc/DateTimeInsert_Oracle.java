package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeInsert_Oracle {
	private static final String INSERT_EMP_DATES_QUERY="INSERT INTO EMPLOYEE VALUES(?,?,?,?)";
	
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				PreparedStatement ps=con.prepareStatement(INSERT_EMP_DATES_QUERY);
				){
			//READ INPUTS
			int no=0;
			String name=null,dob=null,doj=null;
			if(sc!=null) {
				System.out.println("ENTER EMPLOYEE NUMBER");
				no=sc.nextInt();
				System.out.println("ENTER EMPLOYEE NUMBER");
				name=sc.next();
				System.out.println("ENTER EMPLOYEE DOB(dd-MM-yyyy)::");
				dob=sc.next();
				System.out.println("ENTER EMPLOYEE DOJ(yyyy-MM-dd hh:mm:ss)::");
				sc.nextLine();
				doj=sc.nextLine();
			}
			//convert String date value to java.lang.sql.Date class obj
			//convert String date value to java.util.Date class obj
			java.text.SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob=sdf.parse(dob);
			//CONVERT java.util.Date obj to java.sql.Date class obj
			long ms=udob.getTime();
			java.sql.Date sqdob=new java.sql.Date(ms);
			
			//convert String date time value to java.sql.Timestamp class obj
			java.sql.Timestamp sqdoj=java.sql.Timestamp.valueOf(doj);
			//set values to query params and excute the query
			if(ps!=null) {
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setDate(3, sqdob);
				ps.setTimestamp(4, sqdoj);
				
				//execute the query
				int count=ps.executeUpdate();
				//process the result
				if(count==0)
					System.out.println("Recored not inserted");
				else
					System.out.println("Recored inserted");
			}//if
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
