package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CS_ProcedureCallTest3_MySQL {
	
	private static final String CALL_PROCEDURE_QUERY="{CALL GET_PRODS_BY_PRICE_RANGE(?,?)}";

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj916db1","root","123456789");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE_QUERY);
				){
			float start=0.0f,end=0.0f;
			if(cs!=null) {
				//read inputs
				System.out.println("ENTER START PRICE::");
				start=sc.nextFloat();
				System.out.println("ENTER END PRICE::");
				end=sc.nextFloat();
			}
			if(cs!=null) {
				//set values to IN params
				cs.setFloat(1, start);
				cs.setFloat(2, end);
				//call the PL/SQL procedure
				cs.execute();
				//get the results from OUT params
				try(ResultSet rs=cs.getResultSet()){
					//process the ResultsSet
					if(rs!=null) {
						System.out.println("PRODUCT DETAILS OF PRICE RANGE::"+start+" "+end);
						while(rs.next()) {
							System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
						}
					}
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
