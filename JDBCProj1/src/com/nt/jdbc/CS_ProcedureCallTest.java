package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CS_ProcedureCallTest {
	private static final String CALL_QUERY="{CALL P_FIRST(?,?,?)}";
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				CallableStatement sc=con.prepareCall(CALL_QUERY);
				Scanner ss=new Scanner(System.in)){
			int first=0,second=0;
			if(ss!=null) {
				System.out.println("Enter first value::");
				first=ss.nextInt();
				System.out.println("Enter second value::");
				second=ss.nextInt();
			}
			if(sc!=null) {
				//register OUT params with JDBC types
				sc.registerOutParameter(3, Types.INTEGER);
				
				//set values to IN params
				sc.setInt(1, first);
				sc.setInt(2, second);
				
				//call PL/SQL procedure
				sc.execute();
				
				//gather results from OUT params
				int result=sc.getInt(3);
				System.out.println("Result(sum of"+first+","+second+"values)is::"+result);
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
