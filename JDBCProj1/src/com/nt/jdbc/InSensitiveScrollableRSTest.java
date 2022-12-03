package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InSensitiveScrollableRSTest {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
				Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=st.executeQuery("SELECT EMPNO,ENAME,JOB,SAL FROM EMP");
				){
			System.out.println("Records(top-bottom)");
			
			int count=0;
			while(rs.next()) {
				if(count==0)
					Thread.sleep(20000);//during this sleep time,modify the records of bd table
				
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				count++;
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
