package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PS_ScrollableRSTest {
	
	private static final String GET_EMPs_QUERY="SELECT EMPNO,ENAME,JOB,SAL FROM EMP";

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				PreparedStatement ps=con.prepareStatement(GET_EMPs_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,
																		ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=ps.executeQuery();
				){
			System.out.println("Records(Top-Bottom)");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)                              );
			}
			rs.afterLast();
			System.out.println("Records (bottom-top)");
			while(rs.previous()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			}
			System.out.println("__________________________________");
			rs.first();
			System.out.println(rs.getRow()+"--"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			rs.last();
			System.out.println(rs.getRow()+"--"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			rs.relative(-3);
			System.out.println(rs.getRow()+"--"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			rs.absolute(2);
			System.out.println(rs.getRow()+"--"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			rs.absolute(-4);
			System.out.println(rs.getRow()+"--"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			rs.relative(2);
			System.out.println(rs.getRow()+"--"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
