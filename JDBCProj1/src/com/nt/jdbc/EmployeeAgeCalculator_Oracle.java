package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeAgeCalculator_Oracle {
	
	private static final String ORACLE_AGE_CALCULATOR="SELECT(SYSDATE-DOB)/360.0 FROM EMPLOYEE WHERE ENO=?";
		
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				PreparedStatement ps=con.prepareStatement(ORACLE_AGE_CALCULATOR);
				){
			
			//read inputs
			int no=0;
			if(sc!=null) {
			System.out.println("ENTER EMPLOYEE NO::");
			no=sc.nextInt();
			}
			if(ps!=null) {
				ps.setInt(1, no);
				//execute the query
				try(ResultSet rs=ps.executeQuery()){
					if(rs.next()) {
						System.out.println("EMPLOYEE AGE::"+rs.getFloat(1));
						
					}
					else {
						System.out.println("EMPLOYEE NOT FOUND");
						
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
