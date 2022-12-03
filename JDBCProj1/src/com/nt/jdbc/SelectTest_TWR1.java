package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest_TWR1 {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");){
			if(con!=null) {
				try(Statement st=con.createStatement()){
					if(st!=null) {
						try(ResultSet rs=st.executeQuery("SELECT COUNT(*) FROM EMP")){
							if(rs!=null) {
								rs.next();
								System.out.println("EMP DB TABLE RECORDS COUNT::"+rs.getInt(1));
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
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
