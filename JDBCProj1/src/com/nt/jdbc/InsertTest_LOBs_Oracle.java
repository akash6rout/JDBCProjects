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

public class InsertTest_LOBs_Oracle {
	private static final String INSERT_EMPLOYEE_LOB_QUERY="INSERT INTO EMPLOYEE VALUES(EID_SWQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				PreparedStatement ps=con.prepareStatement(INSERT_EMPLOYEE_LOB_QUERY);
				){
			//read inputs from enduser
			String name=null,desg=null,photoPath=null,resumePath=null;
			if(sc!=null) {
				System.out.println("ENTER EMPLOYEE NAME::");
				name=sc.next();
				System.out.println("ENTER EMPLOYEE DESG::");
				desg=sc.next();
				System.out.println("ENTER EMPLOYEE PHOTO PATH::");
				photoPath=sc.next();
				System.out.println("ENTER EMPLOYEE RESUME PATH::");
				resumePath=sc.next();
				
			}
			
			System.out.print(photoPath+" "+resumePath);
			
			
			
			try (InputStream is=new FileInputStream(photoPath);
					Reader reader=new FileReader(resumePath);
					){
				
			
			if(ps!=null) {
				//set values to query params
				ps.setString(1, name);
				ps.setString(2, desg);
				ps.setBinaryStream(3, is);
				ps.setCharacterStream(4, reader);
				
				int count=ps.executeUpdate();
				//process the result
				if(count==0)
					System.out.println("RECORED NOT INSERTED");
				else
					System.out.println("RECORD INSERTED");
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
