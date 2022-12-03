package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_BulkInsertTest {
	
	private static final String INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				PreparedStatement ps=con.prepareStatement(INSERT_STUDENT_QUERY)
				){
			int count=0;
			if(sc!=null) {
				System.out.println("ENTER STUDENT COUNT::");
				count=sc.nextInt();
			}//if
			
			if(sc!=null && ps!=null) {
				for(int i=1; i<=count; ++i) {
					System.out.println("ENTER"+i+"STUDENT DETAILS");
					System.out.println("ENTER NO::");
					int no=sc.nextInt();
					System.out.println("ENTER NAME::");
					String name=sc.next();
					System.out.println("ENTER ADDRESS::");
					String addrs=sc.next();
					System.out.println("ENTER AVG::");
					float avg=sc.nextFloat();
					//set the above inputs to pre-compiled SQL Query params
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setString(3, addrs);
					ps.setFloat(4, avg);
					//execute the pre-compiled SQL Query
					int result=ps.executeUpdate();
					//process the results
					if(result==0)
						System.out.println(i+"STUDENT RECORD IS NOT INSERTED");
					else
						System.out.println(i+"STUDENT RECORD IS INSERTED");
				}//FOR
			}//IF
			
		}//TRY
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}//MAIN METHOD

}//CLASS
