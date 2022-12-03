package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest_SurrogateKey_mySQL {
	private static final String INSERT_STUDENT_QUERY="INSERT INTO STUDENT1(SNAME,SADD,AVG) VALUES(?,?,?)";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj916db","root","123456789");
				PreparedStatement ps=con.prepareStatement(INSERT_STUDENT_QUERY);
				){
			//read inputs from enduser
			String name=null,add=null;
			float avg=0.0f;
			if(sc!=null) {
				System.out.println("ENTER STUDENT NAME::");
				name=sc.next();
				System.out.println("ENTER STUDENT ADDRESS");
				add=sc.next();
				System.out.println("ENTER STUDENT AVG");
				avg=sc.nextFloat();
				
				
			}
			if(ps!=null) {
				//set values to query params
				ps.setString(1, name);
				ps.setString(2, add);
				ps.setFloat(3, avg);
				
				int result=ps.executeUpdate();
				//process the result
				if(result==0)
					System.out.println("RECORED NOT INSERTED");
				else
					System.out.println("RECORD INSERTED");
				
			}
		}
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
