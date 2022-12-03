package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest5 {

	public static void main(String[] args) {
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			if(con!=null)
				st=con.createStatement();
				String query="SELECT COUNT(*) FROM STUDENT";
				if(st!=null)
					rs=st.executeQuery(query);
				if(rs!=null)
				{
					rs.next();
					int count=rs.getInt(1);
					System.out.println("records count in student db table::"+count);
					
		}
		
	}catch(SQLException se)
		{
		se.printStackTrace();
		}
	catch(ClassNotFoundException cnf)
		{
		cnf.printStackTrace();
		}
	catch(Exception e)
		{
		e.printStackTrace();
		}finally {
			//close jdbc objects
			try {
				if(rs!=null)
					rs.close();
				
			}catch(SQLException se) {
			se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
				
			}catch(SQLException se) {
			se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
				
			}catch(SQLException se) {
			se.printStackTrace();
			}
			}
		}
}
