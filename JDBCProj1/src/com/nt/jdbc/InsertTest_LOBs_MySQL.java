package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest_LOBs_MySQL {
	private static final String INSERT_EMPLOYEE_LOB_QUERY="INSERT INTO EMPLOYEE_LOBS(ENAME,DESG,PHOTO,RESUME) VALUES(?,?,?,?)";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj916db1","root","123456789");
				PreparedStatement ps=con.prepareStatement(INSERT_EMPLOYEE_LOB_QUERY);
				){
			//read inputs from enduser
			String name=null,desg=null,photoPath=null,resumePath=null;
			if(sc!=null) {
				System.out.println("ENTER EMPLOYEE NAME");
				name=sc.next();
				System.out.println("ENTER EMPLOYEE DESG");
				desg=sc.next();
				System.out.println("ENTER EMPLOYEE PHOTOPATH");
				photoPath=sc.next();
				System.out.println("ENTER EMPLOYEE RESUME");
				resumePath=sc.next();
				
			}
			
			
			System.out.println(photoPath+" "+resumePath);
			//create stream representing photo,resume file
			try(InputStream is=new FileInputStream(photoPath);
					Reader reader=new FileReader(resumePath);){
				if(ps!=null) {
					//set values to query params
					ps.setString(1, name);
					ps.setString(2, desg);
					ps.setBinaryStream(3, is);
					ps.setCharacterStream(4, reader);
					
					//execute the sql query
					int count=ps.executeUpdate();
					
					//process the result
					if(count==0)
						System.out.println("Record not inserted");
					else
						System.out.println("Record inserted");
					
				}
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
