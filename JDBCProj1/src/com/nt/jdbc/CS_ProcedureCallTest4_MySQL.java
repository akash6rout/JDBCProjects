package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CS_ProcedureCallTest4_MySQL {
	private static final String CALL_PROCEDURE_QUERY="{CALL GET_PROD_DETAILS_BY_PID(?,?,?,?)}";
	
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj916db1","root","123456789");
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE_QUERY);
				Scanner sc=new Scanner(System.in);){
			
			int id=0;
			if(sc!=null) {
				System.out.println("ENTER PRODUCT ID::");
				id=sc.nextInt();
			}
			if(cs!=null) {
				//register OUT params with JDBC types
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.FLOAT);
				cs.registerOutParameter(4, Types.FLOAT);
				
				//set values to IN params
				cs.setInt(1, id);
				//call PL/SQL procedure
				cs.execute();
				
				//gather results from OUT params
				String name=cs.getString(2);
				float price=cs.getFloat(3);
				float qty=cs.getFloat(4);
				System.out.println("PRODUCT NAME::"+name);
				System.out.println("PRODUCT PRICE::"+price);
				System.out.println("PRODUCT QTY::"+qty);
			}
			
		}//
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
