package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class SelectTest5TWR {

	public static void main(String[] args) {
		
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT COUNT(*) FROM emp");
			){
			if(rs!=null) {
				rs.next();
				System.out.println("EMP db table records count::"+rs.getInt(1));
				
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}//main
}//class
		