package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MySql_PS_BulkInsertTest {
	private static final String INERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///NTAJ916DB","root","123456789");
				PreparedStatement ps=con.prepareStatement(INERT_STUDENT_QUERY);
						){
			int count=0;
			if(sc!=null) {
				System.out.println("ENTER STUDENT COUNT::");
				count=sc.nextInt();
			}//if
			if(sc!=null && ps!=null) {
				for(int i=1; i<=count; i++) {
					System.out.println("ENTER"+i+"STUDENT DETAILS");
					System.out.println("ENTER NO::");
					int no=sc.nextInt();
					System.out.println("ENTER Name::");
					String name=sc.next();
					System.out.println("ENTER ADDRESS::");
					String addrs=sc.next();
					System.out.println("ENTER AVG::");
					Float avg=sc.nextFloat();
					//SET THE ABOVE INPUTS TO PRE-COMPILED SQL QUERY PARAMS
					ps.setInt(1, no);
					ps.setString(2,name);
					ps.setString(3, addrs);
					ps.setFloat(4, avg);
					//EXEXUTE THE PRECOMPILED SQL QUERY
					int result=ps.executeUpdate();
					//process the results
					if(result==0)
						System.out.println(i+"STUDENT RECORDS IS NOT INSERTED");
					else
						System.out.println(i+"STUDENT RECORDS IS INSERTED");
				}//FOR
			}//IF
		}//TRY
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception s) {
			s.printStackTrace();
		}

	}

}
